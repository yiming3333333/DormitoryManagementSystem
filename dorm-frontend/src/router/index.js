import { createRouter, createWebHistory } from 'vue-router'
import LoginView from '../views/LoginView.vue'
import HomeView from '../views/HomeView.vue'

const router = createRouter({
  history: createWebHistory(),
  routes: [
    { path: '/login', component: LoginView, meta: { public: true } },
    { path: '/', component: HomeView, meta: { public: false } }
  ]
})

router.beforeEach((to, from) => {
  const token = localStorage.getItem('token')
  if (!to.meta.public && !token) {
    return '/login'   // 直接 return 路径，不用 next()
  }
  return true         // 允许放行
})

export default router