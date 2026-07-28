// 从 vue-router 导入所需函数
import { createRouter, createWebHistory } from 'vue-router'
// 导入你的页面组件
import Home from '../views/Home.vue'
import Chat from '../views/Chat.vue'
import Service from '../views/Service.vue'
import Profile from '../views/Profile.vue'

//  定义路由规则
const routes = [
    { path: '/', name: Home, component: Home },
    { path: '/chat', name: Chat, component: Chat },
    { path: '/Service', name: Service, component: Service },
    { path: '/profile', name: Profile, component: Profile }
]

// 创建路由实例
const router = createRouter({
    history: createWebHistory(), // 使用 HTML5 模式
    routes,
})

export default router