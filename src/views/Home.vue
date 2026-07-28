

<template>
  <div class="page-container">
    <van-nav-bar title="智能旅游助手" />
  </div>
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
            @click="showCityPicker = true"
            v-model="formData.city" label="文本" placeholder="请输入用户名" />
      </van-cell-group>

    </div>

    <div class="card">
      <div class="section-title">
        快捷入口
      </div>
    </div>

    <div class="card">
      <div class="section-title">
        热门目的地
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
  import { ref, reactive } from 'vue';

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
  const columns = allCities.map(city =>({
    text: city,
    value: city
  }))
  const onConfirm = () => { }
  const onCancel = () => { }

</script>

<style scoped>
  .page-content {
    padding: 16px;
  }/*内容样式*/

  .card {
    background-color: #fff;
    padding: 16px;
    border-radius: 8px;
    margin-bottom: 12px;
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
  }/*卡片样式*/

  .section-title {
    font-size: 18px;
    font-weight: 600;
    color: #323233;
    margin-bottom: 12px;

  }/*卡片标题样式*/

</style>