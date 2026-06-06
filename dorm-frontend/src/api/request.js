import axios from 'axios'
import { ElMessage, ElLoading } from 'element-plus'

const request = axios.create({
  //baseURL: '/api',
  baseURL: 'https://your-backend-service.up.railway.app',
  timeout: 10000
})

let loadingInstance = null
let requestCount = 0

const showLoading = () => {
  if (requestCount === 0) {
    loadingInstance = ElLoading.service({ text: '加载中...', background: 'rgba(0,0,0,0.05)' })
  }
  requestCount++
}

const hideLoading = () => {
  requestCount--
  if (requestCount === 0 && loadingInstance) {
    loadingInstance.close()
    loadingInstance = null
  }
}

// 请求拦截：自动加 Token
request.interceptors.request.use(
  config => {
    showLoading()
    const token = localStorage.getItem('token')
    if (token) config.headers.Authorization = 'Bearer ' + token
    return config
  },
  err => {
    hideLoading()
    return Promise.reject(err)
  }
)

// 响应拦截：统一错误处理 + 401 跳转
request.interceptors.response.use(
  res => {
    hideLoading()
    if (res.data.code !== 200) {
      ElMessage.error(res.data.msg || '请求失败')
      return Promise.reject(res.data)
    }
    return res
  },
  err => {
    hideLoading()
    if (err.response?.status === 401) {
      localStorage.removeItem('token')
      localStorage.removeItem('userInfo')
      window.location.href = '/login'
      ElMessage.error('登录已过期，请重新登录')
    } else {
      ElMessage.error(err.response?.data?.msg || '网络错误，请稍后重试')
    }
    return Promise.reject(err)
  }
)

export default request