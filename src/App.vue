<script setup>
import HelloWorld from './components/HelloWorld.vue'
</script>

<template>
  <div id="app">
    <nav>
      <!-- 使用 <RouterLink> 进行导航 -->
      <RouterLink to="/">Home</RouterLink> |
      <RouterLink to="/about">About</RouterLink>
    </nav>
    <!-- 路由匹配到的组件将渲染在这里 -->
    <RouterView />
  </div>
</template>

