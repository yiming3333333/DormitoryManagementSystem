import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

export default defineConfig({
  plugins: [vue()],
  server: {
     port: 5174,  // 换端口
    proxy: {
      '/api': {
        //target: 'http://localhost:8080',
        target: 'https://your-backend-service.up.railway.app',
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/api/, '')
      }
    }
  }
})