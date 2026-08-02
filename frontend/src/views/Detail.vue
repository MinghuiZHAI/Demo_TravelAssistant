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
  <div class="page-container">
    <van-nav-bar title="推荐结果" left-arrow @click="goBack"/>
    <div class="page-content">

<!--      loading-->
      <div class="loading-container" v-if="loading">
        <van-loading size="48px" type="spinner">正在加载中...</van-loading>
      </div>

<!--      接口调用失败时，Empty-->
      <div v-else-if="error">
        <van-empty description="描述文字">
          <van-button @click="fetchRecommend" type="primary" class="bottom-button">按钮</van-button>
        </van-empty>
      </div>

<!--      当状态非loading非empty，且有值和success时，才显示页面-->
      <template v-else-if="tripData && tripData.success === true">
        <div class="card overview-card">    <!--flex布局-->
          <h2>{{tripData.city}} · {{tripData.days}}天行程</h2>
        </div>
        <van-collapse v-model="activeDays">
<!--          v-for遍历一定要要key  ,  name唯一值 -->
          <van-collapse-item
              v-for="day in tripData.dailyItinerary"
              :key="day.day"
              :title="'第' + day.day + '天'"
              :name="day.day"
              @click="handleItemClick(day)">

            <div class="day-schedule">
              <div class="schedule-section">
                <div class="section-label morning">上午</div>
                <SpotItem :data="day.morning"/>
              </div>

              <div class="schedule-section">
                <div class="section-label afternoon">下午</div>
                <SpotItem :data="day.afternoon"/>
              </div>

              <div class="schedule-section">
                <div class="section-label evening">晚上</div>
                <SpotItem :data="day.evening"/>
              </div>
            </div>

          </van-collapse-item>

        </van-collapse>

        <div class="card budget-card">
          <div class="section-title">预算明细</div>
          <BudgetTable :data="tripData.budgetBreakdown" :total="tripData.totalBudget" />
        </div>
        <div class="card tips-card">
          <div class="section-title">温馨提示</div>
          <ul class="tips-list">
            <li v-for="tip in tripData.tips" :key="tip">{{tip}}</li>
          </ul>
        </div>
        <div class="card warnings-card">
          <div class="section-title">注意事项</div>
          <ul class="warning-list">
            <li v-for="warning in tripData.warnings" :key="warning">{{warning}}</li>
          </ul>
        </div>

      </template>

    </div>

<!--    底部按钮-->
    <div class="detail-footer" v-if="tripData && tripData.success !== false">
      <van-button class="primary-button"
                  @click="goToChat"
                  type="primary" size="large" round>
        咨询AI助手</van-button>
    </div>

  </div>
</template>

<script setup>
import {onMounted, reactive, ref, watch} from "vue"
/*useRoute和useRouter不一样，获取当前路由对象要用useRoute*/
import {useRoute, useRouter} from 'vue-router'
import { post } from '../utils/request'
import SpotItem from "../components/SpotItem.vue";
import BudgetTable from "../components/BudgetTable.vue";

const route = useRoute()
const router = useRouter()
const loading = ref(false)    /*定义loading状态*/
const error = ref(true)
// 旅游推荐详情数据
const tripData = ref(null)


/*问题排查*/
// 当前展开的天数
/*
console.log('days 类型:', tripData.value?.dailyItinerary?.[0]?.days) // 看是 number 还是 string

watch(activeDays, (newVal) => {
  console.log('activeDays 变化:', newVal)
})
// 观察点击后 activeDays 是否发生了变化
const handleItemClick = (day) => {
  console.log('点击了第', day.day, '天，当前 activeDays:', activeDays.value)
}*/
/*问题排查*/

const activeDays = ref([])

const formData = reactive({
  city: '',
  budget: '',
  days: ''
})

onMounted(() => {
  /*console.log(route.query)
  formData.city = route.query.city
  formData.budget = route.query.budget
  formData.days = route.query.days*/
  fetchRecommend()
})

const goBack = () => {
  router.back()
}

const fetchRecommend =async () => {
  loading.value = true /*调用接口时显示loading*/
  error.value = false

  try{
    const params = {
      city: route.query.city || '北京',
      budget: parseInt(route.query.budget) || 5000,
      days: parseInt(route.query.days) || 2
    }
    /*获取参数后调用post方法*/
    const response = await post('/api/travel/recommend', params)
    /*拿到数据后进行显示*/
    tripData.value = response.data || {}
  }catch (err) {
    error.value = true
    console.error('获取推荐失败',err)
  }finally{
    loading.value = false
  }
}

const goToChat = () => {
  router.push({name: '/chat'})
}

</script>

<style scoped>
.page-container {
  min-height: 100vh;
  background-color: #f5f5f5;
  padding-bottom: 70px;
}

.card {
  background-color: #fff;
  border-radius: 8px;
  padding: 16px;
  margin-bottom: 12px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
}

.section-title {
  font-size: 18px;
  font-weight: 600;
  color: #323233;
  margin-bottom: 12px;
}

.page-content {
  padding: 16px;
}

.overview-card {
  margin-bottom: 16px;
}

.trip-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.trip-header h2 {
  font-size: 20px;
  color: #323233;
  margin: 0;
}

.trip-budget {
  font-size: 16px;
  color: #ee0a24;
  font-weight: 600;
}

.trip-collapse {
  padding: 16px;
}

.day-schedule {
  padding: 8px 0;
}

.schedule-section {
  margin-bottom: 16px;
}

.schedule-section:last-child {
  margin-bottom: 0;
}

.section-label {
  font-size: 14px;
  font-weight: 600;
  padding: 4px 8px;
  border-radius: 4px;
  display: inline-block;
  margin-bottom: 8px;
}

.section-label.morning {
  background: #fff7e6;
  color: #fa8c16;
}

.section-label.afternoon {
  background: #e6f7ff;
  color: #1890ff;
}

.section-label.evening {
  background: #fff7ed;
  color: #52c41a;
}

.budget-card,
.tips-card,
.warnings-card {
  margin-bottom: 16px;
}

.tips-list,
.warning-list {
  list-style: none;
  padding: 0;
  margin: 0;
}

.tips-list li,
.warning-list li {
  padding: 8px 0;
  color: #666;
  font-size: 14px;
  border-bottom: 1px solid #f5f5f5;
}

.tips-list li:last-child,
.warning-list li:last-child {
  border-bottom: none;
}

.detail.footer {
  position: fixed;
  bottom: 0;
  left: 0;
  right: 0;
  padding: 12px 16px;
  background: #fff;
  box-shadow: 0 -2px 8px rgba(0,0,0,0.05);
  max-width: 750px;
  margin: 0 auto;
}

.error-card {
  text-align: center;
  padding: 40px 16px;
}

</style>