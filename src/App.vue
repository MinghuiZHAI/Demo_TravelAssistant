<script setup>
import { ref } from 'vue';
const active = ref(false);
</script>

<template>
  <div id="app">
<!--    <nav>
      &lt;!&ndash; 使用 <RouterLink> 进行导航 &ndash;&gt;
      <RouterLink to="/">首页</RouterLink>  |
      <RouterLink to="/chat">对话</RouterLink>  |
      <RouterLink to="/about">关于</RouterLink>  |
      <RouterLink to="/profile">我的</RouterLink>
    </nav>-->
    <!-- 路由匹配到的组件将渲染在这里 -->

    <van-tabbar v-model="active">
      <van-tabbar-item to='/' icon="home-o">首页</van-tabbar-item>
      <van-tabbar-item to='/chat' icon="chat-o">对话</van-tabbar-item>
      <van-tabbar-item to='/service' icon="hot-o">服务</van-tabbar-item>
      <van-tabbar-item to='/profile' icon="user-o">我的</van-tabbar-item>
    </van-tabbar>


    <RouterView />
  </div>
</template>

