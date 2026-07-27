// 从 vue-router 导入所需函数
import { createRouter, createWebHistory } from 'vue-router'
// 导入你的页面组件
import HomeView from '../views/HomeView.vue'
import AboutView from '../views/AboutView.vue'

//  定义路由规则
const routes = [
    { path: '/', component: HomeView },
    { path: '/about', component: AboutView },
]

// 创建路由实例
const router = createRouter({
    history: createWebHistory(), // 使用 HTML5 模式[reference:21]
    routes,
})

export default router