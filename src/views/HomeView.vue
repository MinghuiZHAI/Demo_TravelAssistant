<template>
  <div class="home">
    <!-- 顶部导航栏 -->
    <van-nav-bar
        title="首页"
        left-text="返回"
        right-text="设置"
        left-arrow
    />

    <!-- 轮播图 -->
    <van-swipe class="my-swipe" :autoplay="3000" indicator-color="white">
      <van-swipe-item>
        <div class="slide-content" style="background: #39a9ed;">
          <h2>欢迎来到首页</h2>
          <p>Vite + Vue 3 + Vant</p>
        </div>
      </van-swipe-item>
      <van-swipe-item>
        <div class="slide-content" style="background: #66c6f2;">
          <h2>路由已配置</h2>
          <p>点击底部导航切换页面</p>
        </div>
      </van-swipe-item>
      <van-swipe-item>
        <div class="slide-content" style="background: #aa80f0;">
          <h2>开始编码吧</h2>
          <p>🚀 加油！</p>
        </div>
      </van-swipe-item>
    </van-swipe>

    <!-- 商品卡片列表 -->
    <div class="product-list">
      <h3 class="section-title">热门商品</h3>
      <van-row gutter="12">
        <van-col span="12" v-for="item in products" :key="item.id">
          <van-card
              :title="item.title"
              :price="item.price"
              :thumb="item.thumb"
          />
        </van-col>
      </van-row>
    </div>

    <!-- 底部导航栏 -->
    <van-tabbar v-model="activeTab">
      <van-tabbar-item icon="home-o" to="/">首页</van-tabbar-item>
      <van-tabbar-item icon="search" to="/search">搜索</van-tabbar-item>
      <van-tabbar-item icon="cart-o" to="/cart">购物车</van-tabbar-item>
      <van-tabbar-item icon="user-o" to="/profile">我的</van-tabbar-item>
    </van-tabbar>
  </div>
</template>

<script setup>
import { ref } from 'vue'

// 底部导航的当前选中项（默认选中首页）
const activeTab = ref(0)

// 模拟商品数据
const products = ref([
  {
    id: 1,
    title: 'Vant 官方 T恤',
    price: '99.00',
    thumb: 'https://fastly.jsdelivr.net/npm/@vant/assets/card-1.png'
  },
  {
    id: 2,
    title: 'Vant 帆布包',
    price: '59.00',
    thumb: 'https://fastly.jsdelivr.net/npm/@vant/assets/card-2.png'
  },
  {
    id: 3,
    title: 'Vant 鼠标垫',
    price: '39.00',
    thumb: 'https://fastly.jsdelivr.net/npm/@vant/assets/card-3.png'
  },
  {
    id: 4,
    title: 'Vant 保温杯',
    price: '79.00',
    thumb: 'https://fastly.jsdelivr.net/npm/@vant/assets/card-4.png'
  }
])
</script>

<style scoped>
.home {
  padding-bottom: 50px; /* 为底部导航留出空间 */
  background-color: #f7f8fa;
  min-height: 100vh;
}

.my-swipe {
  height: 180px;
  margin: 12px 16px;
  border-radius: 12px;
  overflow: hidden;
}

.slide-content {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 100%;
  color: white;
}

.slide-content h2 {
  margin: 0 0 8px 0;
  font-size: 20px;
}

.slide-content p {
  margin: 0;
  font-size: 14px;
  opacity: 0.9;
}

.product-list {
  padding: 0 16px;
}

.section-title {
  margin: 16px 0 12px 0;
  font-size: 16px;
  font-weight: 600;
  color: #323233;
}

/* Vant 卡片在列中的间距调整 */
:deep(.van-card) {
  margin-bottom: 12px;
  background-color: white;
}
</style>