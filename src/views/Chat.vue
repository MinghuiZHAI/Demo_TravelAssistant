<template>
  <div class="page-container chat-page">
    <div class="page-header">
      <van-nav-bar title="AI旅游助手"
                   flexed
                   left-arrow left-text="返回" @click-left="onBack"/>

    </div>
    <div class="chat-container">
<!--      还没有进行对话时：-->
      <div class="chat-empty" v-if="messages.length === 0">
        <van-empty description="开始和 AI旅游助手 对话"/>
        <div class="quick-questions">
          <div class="quick-title">常见问题</div>
          <van-tag class="quick-tag"
                   v-for="question in quickQuestions"
                   :key="question"
                   mark size="large">{{ question }}</van-tag>
        </div>
      </div>
<!--      有对话内容时： -->
<!--      !!! ai对话需要用到SSE流式接口!!!   -->
      <div class="message-list" v-else="">

      </div>

    </div>
    <div class="chat-input-area">
      <van-field
          v-model="inputMessage"
          placeholder="请输入您的问题"
          :disabled="isStreaming"
      >
        <template #button>
          <van-button @click="sendMessage"
                      :disabled="!inputMessage.trim()"
                      size="small" type="primary">发送</van-button>
        </template>
      </van-field>
    </div>

  </div>
</template>

<script setup>
import {useRoute, useRouter} from "vue-router";
import {ref} from "vue";
import router from "../router/index";             //??
import {fetchStream} from "../utils/request";

const route = useRoute()
const onBack = () => {
  router.back()
}

//用户输入 输入框
const inputMessage = ref("");
//是否正在流式传输
const isStreaming = ref(false);
//发送消息
const sendMessage = () => {
  fetchStream("chat", { message: inputMessage.value }, (chunk) =>{
    console.log(chunk)
  }, () => {

  }, () => {

  })
}
//对话消息
const messages = ref([])

//常见问题
const quickQuestions = ref([
  '北京有哪些必去的景点？',
  '上海美食推荐',
  '成都三日游攻略',
  '如何选择旅行保险？'
])


</script>

<style scoped>
.chat-page {
  display: flex;
  flex-direction: column;
  height: 100vh;
  padding-bottom: 50px;
}

.chat-container {
  flex: 1;
  overflow-y: auto;
  padding: 16px;
  padding-bottom: 60px;
}

.chat-empty {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 100%;
}

.quick-questions {
  margin-top: 32px;
  text-align: center;
}

.quick-title {
  font-size: 14px;
  color: #999;
  margin-bottom: 16px;
}

.quick-tag {
  margin: 8px;
  cursor: pointer;
}

.message-list {
  display: flex;
  flex-direction: column;
  gap: 16px;
}

.streaming-indicator {
  display: flex;
  align-items: center;
  gap: 8px;
  padding: 8px 16px;
  color: #999;
  font-size: 14px;
}

.chat-input-area {
  position: fixed;
  bottom: 50px;
  left: 0;
  right: 0;
  background: #fff;
  padding: 8px 16px;
  box-shadow: 0 -2px 8px rgba(0, 0, 0, 0.05);
  max-width: 750px;
  margin: 0 auto;
}

.chat-input-area :deep(.van-field) {
  background: #f7f8fa;
  border-radius: 20px;
  padding: 8px 16px;
}
</style>