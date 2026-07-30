<template>
  <div class="page-container">
    <van-nav-bar title="智能旅游助手" />

      <div class="page-content">
      <van-notice-bar
          left-icon="volume-o"
          text="基于AI的智能景点介绍与形成规划系统"
      />

      <div class="card">
        <div class="section-title">
          规划你的旅程
        </div>
        <!-- 可以使用 CellGroup 作为容器 -->
        <van-cell-group inset>
          <van-field
              is-link
              readonly
              label="目的地"
              @click="showCityPicker = true"
              v-model="formData.city"
              placeholder="请选择城市"
              style="background-color: #f7f7f7; border-radius: 8px; margin-bottom: 8px"
          />
          <van-field
              label="预算（元）"
              v-model="formData.budget"
              placeholder="请输入预算"
              style="background-color: #f7f7f7; border-radius: 8px; margin-bottom: 8px"
          />
          <van-field
              label="天数"
              v-model="formData.days"
              placeholder="请输入天数"
              style="background-color: #f7f7f7; border-radius: 8px; margin-bottom: 8px"
          />
          <van-button type="primary" size="large"
                      style="margin-top: 8px"
                      round @click="handleSubmit">规划行程</van-button>

        </van-cell-group>

      </div>

      <div class="card">
        <div class="section-title">
          快捷入口
        </div>
        <van-cell-group>
          <van-grid :gutter="10" :column-num="2">
            <van-grid-item @click="goPage('/chat')" icon="chat-o" text="AI对话" />
            <van-grid-item @click="goPage('/profile')" icon="user-o" text="我的" />
          </van-grid>
        </van-cell-group>


      </div>

      <div class="card">
        <div class="section-title">
          热门目的地
        </div>
<!--        <van-cell-group>-->
          <van-grid :gutter="12"  column-num="4">
            <!--用v-for循环输出-->
            <van-grid-item @click="selectCity(city)" v-for="city in hotDestination" :key="city">
              <div class="city-tag" :class="{ active: formData.city === city }">
                {{ city }}
              </div>
            </van-grid-item>
          </van-grid>
<!--        </van-cell-group>-->


      </div>

    </div>
  </div>
<!--  弹出层-->
  <van-popup
      round="bottom"
      v-model:show="showCityPicker" position="bottom">
    <van-picker
        title="标题"
        :columns="columns"
        @confirm="onConfirm"
        @cancel="onCancel"
    />
  </van-popup>

</template>

<script setup>
import {ref, reactive} from 'vue';
import { useRouter } from 'vue-router';
import { showToast } from 'vant';  /*引入轻提示，使用showToast函数*/

  const router = useRouter();

  const showCityPicker = ref(false);

  const formData = reactive({
    "city": "",
    "budget": null,
    "days": null
  })

  const allCities = [
    '北京', '上海', '广州', '深圳', '杭州', '成都', '重庆', '西安',
    '武汉', '南京', '长沙', '青岛', '厦门', '三亚', '昆明', '大理',
    '丽江', '桂林', '苏州', '扬州', '天津', '郑州', '沈阳', '哈尔滨',
    '长春', '大连', '济南', '太原', '兰州', '西宁', '拉萨', '乌鲁木齐',
    '呼和浩特', '银川', '贵阳', '南宁', '海口', '福州', '南昌', '合肥',
    '石家庄', '珠海', '烟台', '泉州', '张家界', '黄山', '敦煌', '秦皇岛'
  ]
  const columns = allCities.map(city =>({ text: city, value: city }))

  const hotDestination = ['北京', '上海', '广州', '深圳', '杭州', '成都', '重庆', '西安']

  const onConfirm = ({ selectedOptions }) => {
    console.log(selectedOptions[0])
    formData.city = selectedOptions[0].value
    showCityPicker.value = false     /*选择完成过后关闭弹出层*/
  }
  const onCancel = () => {
    showCityPicker.value = false   /*直接关闭弹出层*/
  }

  //旅游规划提交
  const handleSubmit = () => {
    console.log(formData)
    // 校验
    if(!formData.city) {
      showToast('请选择目的地')
    }
    if(!formData.budget || formData.budget < 100) {
      showToast('预算不能低于100元')
    }
    if(!formData.days || formData.days <= 1 || formData.days >= 30) {
      showToast('天数必须在1到30天之间')
    }
    router.push({
      path: '/detail',
      query: {
        city: formData.city,
        budget: formData.budget,
        days: formData.days
      }
    })
  }

  //快捷入口页面跳转
  const goPage = (path) => {
    router.push(path)
  }

  //热门城市选择
  const selectCity = (city) => {
    formData.city = city;
  }

</script>

<style scoped>   /*想改子组件内部样式 必须用 :deep() 深度选择器才能生效 */

  .page-container {
    min-height: 100vh;
    background-color: #f5f5f5;
    padding-bottom: 70px;
  }

  .page-content {
    padding: 16px;
  }/*内容样式*/

  .card {
    background-color: #fff;
    padding:16px;
    border-radius: 8px;
    margin-top: 12px;
    margin-bottom: 12px;
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
  }/*卡片样式*/

  .section-title {
    font-size: 18px;
    font-weight: 600;
    color: #323233;
    margin-bottom: 12px;

  }/*卡片标题样式*/

  .city-tag {
    padding: 8px 12px;
    border-radius: 16px;
    font-size: 14px;
    color: #666;
    background-color: #f7f8fa;
    transition: all 0.3s;
  }
/*  .city-tag:active {
    opacity: 0.8;
  }*/
  .city-tag.active {        /*如果是.city-tag:active就是伪类，只用来做轻微的按压反馈*/
    background-color: #007aff;
    color: #fff;
  }

</style>