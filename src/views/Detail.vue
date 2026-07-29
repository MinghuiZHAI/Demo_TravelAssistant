<!--
如果使用vibe coding，提示词：
生成使用axios接口代码，遵循以下规则：
1.接口请求前缀： http://159.75.169.224:1236
2.接口路径： /api/travel/recommend
3.请求的类型为post，请求的参数为{
      "city": "北京",
      "budget": 5000,
      "days": 2
    }
4.对axios进行二次封装，创建utils目录，指并且在Detail页面中进行调用

=>结果：
1.安装axios, npm install axios
2.创建utils/request.js ，创建axios二次封装和Detail页面
3.更新路由配置，添加Detail页面，查看并更新Home页面（handleSubmit方法）

-->

<template>
  <div class="detail">
    <h1>详情</h1>
  </div>
</template>

<script setup>
import {onMounted, reactive} from "vue"
/*useRoute和useRouter不一样，获取当前路由对象要用useRoute*/
import { useRoute } from 'vue-router'

const route = useRoute()

const formData = reactive({
  city: '',
  budget: '',
  days: ''
})

onMounted(() => {
  console.log(route.query)
  formData.city = route.query.city
  formData.budget = route.query.budget
  formData.days = route.query.days
})


</script>

<style scoped>
.detail {
  padding: 20px;
}
</style>