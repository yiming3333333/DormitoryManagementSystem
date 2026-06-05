<<template>
  <div class="container">
    <div class="page-header">
      <h1>学生宿舍管理系统</h1>
      <div class="user-bar">
        <el-tag type="info" effect="plain">{{ userInfo.username }}</el-tag>
        <el-tag :type="userInfo.role === 1 ? 'danger' : 'success'" size="small" style="margin: 0 8px">
          {{ userInfo.role === 1 ? '超级管理员' : '管理员' }}
        </el-tag>
        <el-button type="danger" size="small" plain @click="logout">退出登录</el-button>
      </div>
    </div>

    <!-- 楼栋管理 -->
    <el-card v-loading="loading.building" class="box-card" shadow="hover">
      <template #header>
        <div class="card-header">
          <span class="title">🏢 楼栋管理</span>
          <el-button type="primary" @click="openBuildingDialog()">+ 新增楼栋</el-button>
        </div>
      </template>
      <el-table :data="buildingList" style="width: 100%" border>
        <el-table-column prop="buildingId" label="ID" width="70" />
        <el-table-column prop="buildingName" label="楼栋名称" />
        <el-table-column prop="floorCount" label="楼层数" width="90" />
        <el-table-column prop="roomCount" label="房间数" width="90" />
        <el-table-column label="操作" width="180" fixed="right">
          <template #default="scope">
            <el-button size="small" @click="openBuildingDialog(scope.row)">编辑</el-button>
            <el-button size="small" type="danger" @click="deleteBuilding(scope.row.buildingId)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <!-- 宿舍管理 -->
    <el-card v-loading="loading.dormitory" class="box-card" shadow="hover">
      <template #header>
        <div class="card-header">
          <span class="title">🚪 宿舍管理</span>
          <el-button type="primary" @click="openDormitoryDialog()">+ 新增宿舍</el-button>
        </div>
      </template>
      <el-table :data="dormitoryList" style="width: 100%" border>
        <el-table-column prop="dormId" label="ID" width="70" />
        <el-table-column prop="buildingName" label="所属楼栋" />
        <el-table-column prop="dormNumber" label="宿舍号" />
        <el-table-column prop="capacity" label="容量" width="80" />
        <el-table-column prop="currentCount" label="已住" width="80" />
        <el-table-column label="状态" width="100">
          <template #default="scope">
            <el-tag :type="scope.row.status === 0 ? 'success' : scope.row.status === 1 ? 'danger' : 'warning'" size="small">
              {{ scope.row.status === 0 ? '空闲' : scope.row.status === 1 ? '满员' : '维修中' }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="180" fixed="right">
          <template #default="scope">
            <el-button size="small" @click="openDormitoryDialog(scope.row)">编辑</el-button>
            <el-button size="small" type="danger" @click="deleteDormitory(scope.row.dormId)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <!-- 学生管理 -->
    <el-card v-loading="loading.student" class="box-card" shadow="hover">
      <template #header>
        <div class="card-header">
          <span class="title">🎓 学生管理</span>
          <div class="header-actions">
            <el-input v-model="searchName" placeholder="搜索姓名" clearable @clear="handleSearch" @keyup.enter="handleSearch" style="width: 180px" />
            <el-select v-model="searchStatus" placeholder="状态" clearable @change="handleSearch" style="width: 120px; margin: 0 10px">
              <el-option label="未入住" :value="0" />
              <el-option label="已入住" :value="1" />
              <el-option label="退宿" :value="2" />
            </el-select>
            <el-button @click="handleSearch">搜索</el-button>
            <el-button type="primary" @click="openStudentDialog()">+ 新增学生</el-button>
            <el-button @click="exportStudents">导出 CSV</el-button>
          </div>
        </div>
      </template>
      <el-table :data="studentList" style="width: 100%" border>
        <el-table-column prop="studentId" label="ID" width="70" />
        <el-table-column prop="studentNo" label="学号" />
        <el-table-column prop="name" label="姓名" />
        <el-table-column label="性别" width="80">
          <template #default="scope">{{ scope.row.gender === 0 ? '女' : '男' }}</template>
        </el-table-column>
        <el-table-column prop="phone" label="手机号" />
        <el-table-column label="状态" width="90">
          <template #default="scope">
            <el-tag :type="scope.row.status === 0 ? 'info' : scope.row.status === 1 ? 'success' : 'warning'" size="small">
              {{ scope.row.status === 0 ? '未入住' : scope.row.status === 1 ? '已入住' : '退宿' }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="280" fixed="right">
          <template #default="scope">
            <el-button size="small" @click="openStudentDialog(scope.row)">编辑</el-button>
            <el-button v-if="scope.row.status !== 1" size="small" type="success" @click="openCheckInDialog(scope.row)">入住</el-button>
            <el-button v-if="scope.row.status === 1" size="small" type="warning" @click="handleCheckOut(scope.row)">退宿</el-button>
            <el-button size="small" type="danger" @click="deleteStudent(scope.row.studentId)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <div class="pagination-bar">
        <el-pagination
          v-model:current-page="pageNum"
          v-model:page-size="pageSize"
          :total="total"
          :page-sizes="[10, 20, 50]"
          layout="total, sizes, prev, pager, next"
          @size-change="handleSearch"
          @current-change="loadStudents"
        />
      </div>
    </el-card>

    <!-- 报修管理 -->
    <el-card v-loading="loading.repair" class="box-card" shadow="hover">
      <template #header>
        <div class="card-header">
          <span class="title">🔧 报修管理</span>
          <el-button type="primary" @click="openRepairDialog()">+ 新增报修</el-button>
        </div>
      </template>
      <el-table :data="repairList" style="width: 100%" border>
        <el-table-column prop="repairId" label="ID" width="70" />
        <el-table-column prop="dormId" label="宿舍ID" width="90" />
        <el-table-column prop="description" label="问题描述" show-overflow-tooltip />
        <el-table-column label="状态" width="100">
          <template #default="scope">
            <el-tag :type="scope.row.status === 0 ? 'danger' : scope.row.status === 1 ? 'warning' : 'success'" size="small">
              {{ scope.row.status === 0 ? '待处理' : scope.row.status === 1 ? '处理中' : '已完成' }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="createTime" label="报修时间" />
        <el-table-column label="操作" width="200" fixed="right">
          <template #default="scope">
            <el-button v-if="scope.row.status !== 2" size="small" type="primary" @click="updateRepairStatus(scope.row)">更新进度</el-button>
            <el-button size="small" type="danger" @click="deleteRepair(scope.row.repairId)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <!-- 管理员管理：仅超级管理员可见 -->
    <el-card v-if="userInfo.role === 1" v-loading="loading.admin" class="box-card" shadow="hover">
      <template #header>
        <div class="card-header">
          <span class="title">🔐 管理员管理</span>
          <el-button type="primary" @click="openAdminDialog()">+ 新增管理员</el-button>
        </div>
      </template>
      <el-table :data="adminList" style="width: 100%" border>
        <el-table-column prop="adminId" label="ID" width="70" />
        <el-table-column prop="username" label="用户名" />
        <el-table-column label="角色" width="120">
          <template #default="scope">
            <el-tag :type="scope.row.role === 1 ? 'danger' : 'success'" size="small">
              {{ scope.row.role === 1 ? '超级管理员' : '普通管理员' }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="180" fixed="right">
          <template #default="scope">
            <el-button size="small" @click="openAdminDialog(scope.row)">编辑</el-button>
            <el-button size="small" type="danger" @click="deleteAdmin(scope.row.adminId)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <!-- ===== 弹窗区域 ===== -->

    <!-- 楼栋弹窗 -->
    <el-dialog v-model="buildingDialog.visible" :title="buildingDialog.title" width="420px" destroy-on-close>
      <el-form :model="buildingForm" :rules="buildingRules" ref="buildingRef" label-width="90px">
        <el-form-item label="楼栋名称" prop="buildingName">
          <el-input v-model="buildingForm.buildingName" maxlength="20" show-word-limit />
        </el-form-item>
        <el-form-item label="楼层数" prop="floorCount">
          <el-input-number v-model="buildingForm.floorCount" :min="1" :max="50" style="width: 100%" />
        </el-form-item>
        <el-form-item label="房间数" prop="roomCount">
          <el-input-number v-model="buildingForm.roomCount" :min="1" :max="500" style="width: 100%" />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="buildingDialog.visible = false">取消</el-button>
        <el-button type="primary" @click="submitBuilding">确定</el-button>
      </template>
    </el-dialog>

    <!-- 宿舍弹窗 -->
    <el-dialog v-model="dormitoryDialog.visible" :title="dormitoryDialog.title" width="420px" destroy-on-close>
      <el-form :model="dormitoryForm" :rules="dormitoryRules" ref="dormitoryRef" label-width="90px">
        <el-form-item label="所属楼栋" prop="buildingId">
          <el-select v-model="dormitoryForm.buildingId" placeholder="请选择楼栋" style="width: 100%">
            <el-option v-for="item in buildingList" :key="item.buildingId" :label="item.buildingName" :value="item.buildingId" />
          </el-select>
        </el-form-item>
        <el-form-item label="宿舍号" prop="dormNumber">
          <el-input v-model="dormitoryForm.dormNumber" maxlength="10" />
        </el-form-item>
        <el-form-item label="容量" prop="capacity">
          <el-input-number v-model="dormitoryForm.capacity" :min="1" :max="12" style="width: 100%" />
        </el-form-item>
        <el-form-item label="已住人数">
          <el-input-number v-model="dormitoryForm.currentCount" :min="0" style="width: 100%" />
        </el-form-item>
        <el-form-item label="状态">
          <el-select v-model="dormitoryForm.status" style="width: 100%">
            <el-option label="空闲" :value="0" />
            <el-option label="满员" :value="1" />
            <el-option label="维修中" :value="2" />
          </el-select>
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="dormitoryDialog.visible = false">取消</el-button>
        <el-button type="primary" @click="submitDormitory">确定</el-button>
      </template>
    </el-dialog>

    <!-- 学生弹窗 -->
    <el-dialog v-model="studentDialog.visible" :title="studentDialog.title" width="420px" destroy-on-close>
      <el-form :model="studentForm" :rules="studentRules" ref="studentRef" label-width="90px">
        <el-form-item label="学号" prop="studentNo">
          <el-input v-model="studentForm.studentNo" maxlength="20" />
        </el-form-item>
        <el-form-item label="姓名" prop="name">
          <el-input v-model="studentForm.name" maxlength="20" />
        </el-form-item>
        <el-form-item label="性别" prop="gender">
          <el-select v-model="studentForm.gender" placeholder="请选择" style="width: 100%">
            <el-option label="女" :value="0" />
            <el-option label="男" :value="1" />
          </el-select>
        </el-form-item>
        <el-form-item label="手机号">
          <el-input v-model="studentForm.phone" maxlength="11" />
        </el-form-item>
        <el-form-item label="密码" prop="password" v-if="!studentDialog.isEdit">
          <el-input v-model="studentForm.password" type="password" show-password />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="studentDialog.visible = false">取消</el-button>
        <el-button type="primary" @click="submitStudent">确定</el-button>
      </template>
    </el-dialog>

    <!-- 入住弹窗 -->
    <el-dialog v-model="checkInDialog.visible" title="办理入住" width="480px" destroy-on-close>
      <el-form :model="checkInForm" :rules="checkInRules" ref="checkInRef" label-width="90px">
        <el-form-item label="学生">
          <el-tag size="large">{{ checkInDialog.studentName }}</el-tag>
        </el-form-item>
        <el-form-item label="选择宿舍" prop="dormId">
          <el-select v-model="checkInForm.dormId" placeholder="请选择宿舍" style="width: 100%">
            <el-option
              v-for="item in availableDormitoryList"
              :key="item.dormId"
              :label="item.buildingName + '-' + item.dormNumber + ' (剩余' + (item.capacity - item.currentCount) + '人)' + (dormGenderMap[item.dormId] !== undefined ? (dormGenderMap[item.dormId] === 0 ? ' [女寝]' : ' [男寝]') : '')"
              :value="item.dormId"
            />
          </el-select>
          <div class="form-tip">系统将在后端自动校验性别一致性</div>
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="checkInDialog.visible = false">取消</el-button>
        <el-button type="primary" @click="submitCheckIn">确定入住</el-button>
      </template>
    </el-dialog>

    
    <!-- 报修弹窗 -->
    <el-dialog v-model="repairDialog.visible" :title="repairDialog.title" width="420px" destroy-on-close>
      <el-form :model="repairForm" :rules="repairRules" ref="repairRef" label-width="90px">
        <el-form-item label="宿舍" prop="dormId">
          <el-select 
            v-model="repairForm.dormId" 
            placeholder="请选择宿舍" 
            style="width: 100%"
            @change="repairForm.studentId = null"
          >
            <el-option 
              v-for="item in repairDormList" 
              :key="item.dormId" 
              :label="item.buildingName + '-' + item.dormNumber + ' (在住' + item.currentCount + '人)'" 
              :value="item.dormId" 
            />
          </el-select>
        </el-form-item>
        <el-form-item label="学生" prop="studentId">
          <el-select 
            v-model="repairForm.studentId" 
            placeholder="请先选择宿舍" 
            style="width: 100%"
            :disabled="!repairForm.dormId"
          >
            <el-option 
              v-for="item in repairStudentList" 
              :key="item.studentId" 
              :label="item.name + ' (' + item.studentNo + ')'" 
              :value="item.studentId" 
            />
          </el-select>
        </el-form-item>
        <el-form-item label="问题描述" prop="description">
          <el-input v-model="repairForm.description" type="textarea" :rows="3" maxlength="500" show-word-limit />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="repairDialog.visible = false">取消</el-button>
        <el-button type="primary" @click="submitRepair">确定</el-button>
      </template>
    </el-dialog>
    <!-- 管理员弹窗 -->
    <el-dialog v-model="adminDialog.visible" :title="adminDialog.title" width="420px" destroy-on-close>
      <el-form :model="adminForm" :rules="adminRules" ref="adminRef" label-width="90px">
        <el-form-item label="用户名" prop="username">
          <el-input v-model="adminForm.username" maxlength="20" />
        </el-form-item>
        <el-form-item label="密码" prop="password" v-if="!adminDialog.isEdit">
          <el-input v-model="adminForm.password" type="password" show-password />
        </el-form-item>
        <el-form-item label="角色" prop="role">
          <el-select v-model="adminForm.role" placeholder="请选择" style="width: 100%">
            <el-option label="普通管理员" :value="0" />
            <el-option label="超级管理员" :value="1" />
          </el-select>
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="adminDialog.visible = false">取消</el-button>
        <el-button type="primary" @click="submitAdmin">确定</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, reactive, computed, onMounted, nextTick,watch } from 'vue'
import { useRouter } from 'vue-router'
import { ElMessage, ElMessageBox } from 'element-plus'
import { buildingApi, dormitoryApi, studentApi, repairApi, adminApi } from '../api/api'

const router = useRouter()

/* ========== 用户信息 & 权限 ========== */
const userInfo = reactive({ username: '', role: 0 })

/* ========== Loading 状态 ========== */
const loading = reactive({ building: false, dormitory: false, student: false, repair: false, admin: false })

/* ========== 数据列表 ========== */
const buildingList = ref([])
const dormitoryList = ref([])
const studentList = ref([])
const repairList = ref([])
const adminList = ref([])
const checkInList = ref([])

/* ========== 学生分页 & 搜索 ========== */
const pageNum = ref(1)
const pageSize = ref(10)
const total = ref(0)
const searchName = ref('')
const searchStatus = ref('')

/* ========== 弹窗状态 ========== */
const buildingDialog = reactive({ visible: false, title: '新增楼栋', isEdit: false })
const dormitoryDialog = reactive({ visible: false, title: '新增宿舍', isEdit: false })
const studentDialog = reactive({ visible: false, title: '新增学生', isEdit: false })
const checkInDialog = reactive({ visible: false, studentName: '' })
const repairDialog = reactive({ visible: false, title: '新增报修', isEdit: false })
const adminDialog = reactive({ visible: false, title: '新增管理员', isEdit: false })

/* ========== 表单数据 ========== */
const buildingForm = reactive({ buildingId: null, buildingName: '', floorCount: 1, roomCount: 1 })
const dormitoryForm = reactive({ dormId: null, buildingId: null, dormNumber: '', capacity: 4, currentCount: 0, status: 0 })
const studentForm = reactive({ studentId: null, studentNo: '', name: '', gender: 0, phone: '', password: '', status: 0 })
const checkInForm = reactive({ studentId: null, dormId: null })
const repairForm = reactive({ repairId: null, dormId: null, studentId: null, description: '', status: 0 })
const adminForm = reactive({ adminId: null, username: '', password: '', role: 0 })

/* ========== 表单 Ref ========== */
const buildingRef = ref(null)
const dormitoryRef = ref(null)
const studentRef = ref(null)
const checkInRef = ref(null)
const repairRef = ref(null)
const adminRef = ref(null)

/* ========== 校验规则 ========== */
const buildingRules = {
  buildingName: [{ required: true, message: '请输入楼栋名称', trigger: 'blur' }],
  floorCount: [{ required: true, message: '请输入楼层数', trigger: 'blur' }],
  roomCount: [{ required: true, message: '请输入房间数', trigger: 'blur' }]
}
const dormitoryRules = {
  buildingId: [{ required: true, message: '请选择所属楼栋', trigger: 'change' }],
  dormNumber: [{ required: true, message: '请输入宿舍号', trigger: 'blur' }],
  capacity: [{ required: true, message: '请输入容量', trigger: 'blur' }]
}
const studentRules = {
  studentNo: [{ required: true, message: '请输入学号', trigger: 'blur' }],
  name: [{ required: true, message: '请输入姓名', trigger: 'blur' }],
  gender: [{ required: true, message: '请选择性别', trigger: 'change' }],
  password: [{ required: true, message: '请输入密码', trigger: 'blur' }]
}
const checkInRules = {
  dormId: [{ required: true, message: '请选择宿舍', trigger: 'change' }]
}
const repairRules = {
  dormId: [{ required: true, message: '请选择宿舍', trigger: 'change' }],
  studentId: [{ required: true, message: '请输入学生ID', trigger: 'blur' }],
  description: [{ required: true, message: '请输入问题描述', trigger: 'blur' }]
}
const adminRules = {
  username: [{ required: true, message: '请输入用户名', trigger: 'blur' }],
  password: [{ required: true, message: '请输入密码', trigger: 'blur' }],
  role: [{ required: true, message: '请选择角色', trigger: 'change' }]
}

/* ========== 计算属性 ========== */
const availableDormitoryList = computed(() => dormitoryList.value.filter(d => d.status === 0 && d.currentCount < d.capacity))

// 宿舍性别提示映射：根据在住记录推断宿舍性别
const dormGenderMap = computed(() => {
  const map = {}
  checkInList.value.forEach(record => {
    if (record.status === 0) {
      const stu = studentList.value.find(s => s.studentId === record.studentId)
      if (stu) map[record.dormId] = stu.gender
    }
  })
  return map
})

/* ========== 数据加载 ========== */
const loadData = async () => {
  loading.building = true
  loading.dormitory = true
  loading.repair = true
  loading.admin = true
  try {
    const [bRes, dRes, rRes, cRes] = await Promise.all([
      buildingApi.list(),
      dormitoryApi.list(),
      repairApi.list(),
      studentApi.checkInList()
    ])
    buildingList.value = bRes.data.data
    dormitoryList.value = dRes.data.data
    repairList.value = rRes.data.data
    checkInList.value = cRes.data.data

    if (userInfo.role === 1) {
      const aRes = await adminApi.list()
      adminList.value = aRes.data.data
    }
  } finally {
    loading.building = false
    loading.dormitory = false
    loading.repair = false
    loading.admin = false
  }
  await loadStudents()
}

const loadStudents = async () => {
  loading.student = true
  try {
    const params = { pageNum: pageNum.value, pageSize: pageSize.value }
    if (searchName.value) params.name = searchName.value
    if (searchStatus.value !== '') params.status = searchStatus.value
    const res = await studentApi.list(params)
    studentList.value = res.data.data.records || []
    total.value = res.data.data.total || 0
  } finally {
    loading.student = false
  }
}

const handleSearch = () => {
  pageNum.value = 1
  loadStudents()
}

/* ========== 楼栋 ========== */
const openBuildingDialog = (row = null) => {
  buildingDialog.isEdit = !!row
  buildingDialog.title = row ? '编辑楼栋' : '新增楼栋'
  Object.assign(buildingForm, row || { buildingId: null, buildingName: '', floorCount: 1, roomCount: 1 })
  buildingDialog.visible = true
  nextTick(() => buildingRef.value?.clearValidate())
}

const submitBuilding = async () => {
  const valid = await buildingRef.value.validate().catch(() => false)
  if (!valid) return
  if (buildingDialog.isEdit) {
    await buildingApi.update(buildingForm)
    ElMessage.success('修改成功')
  } else {
    await buildingApi.add(buildingForm)
    ElMessage.success('新增成功')
  }
  buildingDialog.visible = false
  loadData()
}

const deleteBuilding = async (id) => {
  try {
    await ElMessageBox.confirm('确认删除该楼栋？', '提示', { type: 'warning' })
    await buildingApi.delete(id)
    ElMessage.success('删除成功')
    loadData()
  } catch (e) { if (e !== 'cancel') console.error(e) }
}

/* ========== 宿舍 ========== */
const openDormitoryDialog = (row = null) => {
  dormitoryDialog.isEdit = !!row
  dormitoryDialog.title = row ? '编辑宿舍' : '新增宿舍'
  Object.assign(dormitoryForm, row || { dormId: null, buildingId: null, dormNumber: '', capacity: 4, currentCount: 0, status: 0 })
  dormitoryDialog.visible = true
  nextTick(() => dormitoryRef.value?.clearValidate())
}

const submitDormitory = async () => {
  const valid = await dormitoryRef.value.validate().catch(() => false)
  if (!valid) return
  if (dormitoryDialog.isEdit) {
    await dormitoryApi.update(dormitoryForm)
    ElMessage.success('修改成功')
  } else {
    await dormitoryApi.add(dormitoryForm)
    ElMessage.success('新增成功')
  }
  dormitoryDialog.visible = false
  loadData()
}

const deleteDormitory = async (id) => {
  try {
    await ElMessageBox.confirm('确认删除该宿舍？', '提示', { type: 'warning' })
    await dormitoryApi.delete(id)
    ElMessage.success('删除成功')
    loadData()
  } catch (e) { if (e !== 'cancel') console.error(e) }
}

/* ========== 学生 ========== */
const openStudentDialog = (row = null) => {
  studentDialog.isEdit = !!row
  studentDialog.title = row ? '编辑学生' : '新增学生'
  Object.assign(studentForm, row || { studentId: null, studentNo: '', name: '', gender: 0, phone: '', password: '', status: 0 })
  studentDialog.visible = true
  nextTick(() => studentRef.value?.clearValidate())
}

const submitStudent = async () => {
  const valid = await studentRef.value.validate().catch(() => false)
  if (!valid) return
  if (studentDialog.isEdit) {
    await studentApi.update(studentForm)
    ElMessage.success('修改成功')
  } else {
    await studentApi.add(studentForm)
    ElMessage.success('新增成功')
  }
  studentDialog.visible = false
  loadData()
}

const deleteStudent = async (id) => {
  try {
    await ElMessageBox.confirm('确认删除该学生？', '提示', { type: 'warning' })
    await studentApi.delete(id)
    ElMessage.success('删除成功')
    loadData()
  } catch (e) { if (e !== 'cancel') console.error(e) }
}

/* ========== 入住 & 退宿 ========== */
const openCheckInDialog = (row) => {
  checkInDialog.studentName = row.name
  Object.assign(checkInForm, { studentId: row.studentId, dormId: null })
  checkInDialog.visible = true
  nextTick(() => checkInRef.value?.clearValidate())
}

const submitCheckIn = async () => {
  const valid = await checkInRef.value.validate().catch(() => false)
  if (!valid) return
  await studentApi.checkIn(checkInForm)
  ElMessage.success('入住办理成功')
  checkInDialog.visible = false
  loadData()
}

const handleCheckOut = async (row) => {
  const record = checkInList.value.find(c => c.studentId === row.studentId && c.status === 0)
  if (!record) {
    ElMessage.error('未找到该学生的在住记录')
    return
  }
  try {
    await ElMessageBox.confirm(`确认给学生 ${row.name} 办理退宿？`, '提示', { type: 'warning' })
    await studentApi.checkOut(record.recordId)
    ElMessage.success('退宿成功')
    loadData()
  } catch (e) { if (e !== 'cancel') console.error(e) }
}

/* ========== 报修 ========== */
const openRepairDialog = (row = null) => {
  repairDialog.isEdit = !!row
  repairDialog.title = row ? '编辑报修' : '新增报修'
  Object.assign(repairForm, row || { repairId: null, dormId: null, studentId: null, description: '', status: 0 })
  repairDialog.visible = true
  nextTick(() => repairRef.value?.clearValidate())
}

const submitRepair = async () => {
  const valid = await repairRef.value.validate().catch(() => false)
  if (!valid) return
  if (repairDialog.isEdit) {
    await repairApi.update(repairForm)
    ElMessage.success('修改成功')
  } else {
    await repairApi.add(repairForm)
    ElMessage.success('新增成功')
  }
  repairDialog.visible = false
  loadData()
}

const updateRepairStatus = async (row) => {
  const newStatus = row.status + 1
  if (newStatus > 2) return
  await repairApi.update({ ...row, status: newStatus })
  ElMessage.success('状态更新成功')
  loadData()
}

const deleteRepair = async (id) => {
  try {
    await ElMessageBox.confirm('确认删除该报修记录？', '提示', { type: 'warning' })
    await repairApi.delete(id)
    ElMessage.success('删除成功')
    loadData()
  } catch (e) { if (e !== 'cancel') console.error(e) }
}

/* ========== 管理员 ========== */
const openAdminDialog = (row = null) => {
  adminDialog.isEdit = !!row
  adminDialog.title = row ? '编辑管理员' : '新增管理员'
  Object.assign(adminForm, row || { adminId: null, username: '', password: '', role: 0 })
  adminDialog.visible = true
  nextTick(() => adminRef.value?.clearValidate())
}

const submitAdmin = async () => {
  const valid = await adminRef.value.validate().catch(() => false)
  if (!valid) return
  if (adminDialog.isEdit) {
    await adminApi.update(adminForm)
    ElMessage.success('修改成功')
  } else {
    await adminApi.add(adminForm)
    ElMessage.success('新增成功')
  }
  adminDialog.visible = false
  loadData()
}

const deleteAdmin = async (id) => {
  try {
    await ElMessageBox.confirm('确认删除该管理员？', '提示', { type: 'warning' })
    await adminApi.delete(id)
    ElMessage.success('删除成功')
    loadData()
  } catch (e) { if (e !== 'cancel') console.error(e) }
}

/* ========== 导出 CSV ========== */
const exportStudents = () => {
  const headers = ['ID', '学号', '姓名', '性别', '手机号', '状态']
  const rows = studentList.value.map(s => [
    s.studentId,
    s.studentNo,
    s.name,
    s.gender === 0 ? '女' : '男',
    s.phone || '',
    s.status === 0 ? '未入住' : s.status === 1 ? '已入住' : '退宿'
  ])
  const csv = [headers.join(','), ...rows.map(r => r.join(','))].join('\n')
  const blob = new Blob(['\ufeff' + csv], { type: 'text/csv;charset=utf-8;' })
  const link = document.createElement('a')
  link.href = URL.createObjectURL(blob)
  link.download = `学生列表_${new Date().toLocaleDateString()}.csv`
  link.click()
}

/* ========== 退出登录 ========== */
const logout = () => {
  localStorage.removeItem('token')
  localStorage.removeItem('userInfo')
  window.location.href = '/login'
}

/* ========== 计算属性：报修联动 ========== */
const repairDormList = computed(() => dormitoryList.value.filter(d => d.currentCount > 0))

const repairStudentList = computed(() => {
  if (!repairForm.dormId) return []
  // 找出该宿舍下所有在住记录的学生ID
  const livingStudentIds = checkInList.value
    .filter(c => c.dormId === repairForm.dormId && c.status === 0)
    .map(c => c.studentId)
  // 从学生列表中匹配出完整信息
  let students = studentList.value.filter(s => livingStudentIds.includes(s.studentId))
  
  // 编辑时：如果当前选中的学生已退宿不在列表里，也临时加入显示，避免回显丢失
  if (repairDialog.isEdit && repairForm.studentId) {
    const exists = students.find(s => s.studentId === repairForm.studentId)
    if (!exists) {
      const current = studentList.value.find(s => s.studentId === repairForm.studentId)
      if (current) students = [...students, current]
    }
  }
  return students
})

/* ========== 初始化 ========== */
onMounted(() => {
  const stored = localStorage.getItem('userInfo')
  if (stored) Object.assign(userInfo, JSON.parse(stored))
  loadData()
})

</script>

<style scoped>
.container {
  max-width: 1280px;
  margin: 0 auto;
  padding: 20px;
}
.page-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 24px;
}
.page-header h1 {
  margin: 0;
  font-size: 24px;
  color: #333;
}
.user-bar {
  display: flex;
  align-items: center;
  gap: 8px;
}
.box-card {
  margin-bottom: 24px;
}
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.card-header .title {
  font-size: 16px;
  font-weight: 600;
}
.header-actions {
  display: flex;
  align-items: center;
}
.pagination-bar {
  margin-top: 16px;
  display: flex;
  justify-content: flex-end;
}
.form-tip {
  font-size: 12px;
  color: #909399;
  margin-top: 4px;
}
</style>