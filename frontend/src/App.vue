<script setup>
import { ref } from 'vue';
import { useRoute } from 'vue-router';

const active = ref(false);
const route = useRoute();

</script>

<template>
  <div id="app">


    <van-tabbar v-if="['Home', 'Chat', 'Service', 'Profile'].includes(route.name)" v-model="active">
      <van-tabbar-item to='/' icon="home-o">首页</van-tabbar-item>
      <van-tabbar-item to='/chat' icon="chat-o">对话</van-tabbar-item>
      <van-tabbar-item to='/service' icon="hot-o">服务</van-tabbar-item>
      <van-tabbar-item to='/profile' icon="user-o">我的</van-tabbar-item>
    </van-tabbar>


    <RouterView />
  </div>
</template>

