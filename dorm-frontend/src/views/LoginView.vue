<<template>
  <div class="login-container">
    <el-card class="login-box" shadow="hover">
      <h2>学生宿舍管理系统</h2>
      <el-form :model="form" :rules="rules" ref="formRef" label-width="0">
        <el-form-item prop="username">
          <el-input v-model="form.username" placeholder="用户名" :prefix-icon="User" size="large" />
        </el-form-item>
        <el-form-item prop="password">
          <el-input v-model="form.password" type="password" placeholder="密码" :prefix-icon="Lock" size="large" @keyup.enter="handleLogin" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="handleLogin" :loading="loading" size="large" style="width: 100%">登 录</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script setup>
import { ref, reactive } from 'vue'
import { useRouter } from 'vue-router'
import { User, Lock } from '@element-plus/icons-vue'
import { adminApi } from '../api/api'
import { ElMessage } from 'element-plus'

const router = useRouter()
const formRef = ref(null)
const loading = ref(false)

const form = reactive({ username: '', password: '' })

const rules = {
  username: [{ required: true, message: '请输入用户名', trigger: 'blur' }],
  password: [{ required: true, message: '请输入密码', trigger: 'blur' }]
}

const handleLogin = async () => {
  const valid = await formRef.value.validate().catch(() => false)
  if (!valid) return
  loading.value = true
  try {
    const res = await adminApi.login(form)
    const { token, adminId, username, role } = res.data.data
    localStorage.setItem('token', token)
    localStorage.setItem('userInfo', JSON.stringify({ adminId, username, role }))
    ElMessage.success('登录成功')
    router.push('/')
  } catch (err) {
    // 错误已被 request.js 拦截器提示，这里只消控制台红字
    console.log('登录失败:', err?.msg || err)
  } finally {
    loading.value = false
  }
}
</script>

<style scoped>
.login-container {
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
}
.login-box {
  width: 420px;
  border-radius: 12px;
}
.login-box h2 {
  text-align: center;
  margin-bottom: 28px;
  color: #333;
  font-weight: 500;
}
</style>