# 智能旅游助手（前端）

> 基于 AI 技术的智能旅游规划平台前端项目，为用户提供个性化的旅游行程推荐和实时旅游咨询服务。

## 项目概述

智能旅游助手系统是一个基于 AI 技术的旅游规划平台，旨在为用户提供个性化的旅游行程推荐和实时旅游咨询服务。系统通过结合大语言模型的智能分析能力，为用户生成符合预算和时间要求的详细旅游规划，并提供实时的旅游问答服务。

### 主要功能

- **智能旅游规划**：根据用户输入的目的地、预算和天数，生成详细的行程安排
- **AI 旅游咨询**：提供实时的旅游相关问答服务
- **流式响应**：实时显示 AI 生成内容，提升用户体验
- **移动端适配**：响应式设计，完美适配手机屏幕

## 技术栈

| 类别 | 技术 | 说明 |
|------|------|------|
| 框架 | Vue 3 | 渐进式 JavaScript 框架 |
| 构建工具 | Vite 8 | 下一代前端工具链 |
| 路由 | Vue Router 5 | Vue 官方路由管理器 |
| UI 组件库 | Vant 4 | 移动端 Vue 组件库 |
| HTTP 客户端 | Axios | 基于 Promise 的 HTTP 客户端 |

## 项目结构

```
frontend/
├── src/
│   ├── views/                 # 页面组件
│   │   ├── Home.vue          # 首页（城市选择、预算、天数）
│   │   ├── Detail.vue        # 详情页（行程展示）
│   │   ├── Chat.vue          # AI 对话页面
│   │   ├── Service.vue       # 服务页面（预留）
│   │   └── Profile.vue       # 我的页面
│   ├── components/           # 业务组件
│   │   ├── SpotItem.vue      # 景点信息组件
│   │   ├── BudgetTable.vue   # 预算明细组件
│   │   └── ChatBubble.vue    # 聊天气泡组件
│   ├── router/
│   │   └── index.js          # 路由配置
│   ├── utils/
│   │   └── request.js        # HTTP 请求工具（含流式响应）
│   ├── assets/               # 静态资源
│   │   ├── hero.png          # 首页图片
│   │   ├── vite.svg          # Vite 图标
│   │   └── vue.svg           # Vue 图标
│   ├── App.vue               # 根组件
│   ├── main.js               # 入口文件
│   └── style.css             # 全局样式
├── public/                    # 公共资源
│   ├── favicon.svg           # 网站图标
│   └── icons.svg             # 图标资源
├── index.html                # HTML 模板
├── vite.config.js            # Vite 配置
├── package.json              # 依赖配置
├── package-lock.json         # 依赖锁定
├── .gitignore                # Git 忽略规则
└── README.md                 # 项目说明
```

## 前端功能

### 首页

- **功能**：城市选择、预算输入、天数选择、热门目的地推荐
- **组件**：`van-picker`（城市选择器）、`van-field`（数字输入框）、`van-button`（按钮）、`van-grid`（网格布局）
- **交互流程**：填写表单 → 点击"规划行程" → 跳转详情页

### 详情页

- **功能**：行程概览、每日行程展示、预算明细、温馨提示
- **组件**：`van-nav-bar`（导航栏）、`van-collapse`（折叠面板）、`van-cell-group`（列表）、`BudgetTable`（预算表格）、`van-button`（按钮）
- **交互流程**：查看行程 → 点击"咨询 AI" → 跳转对话页

### 对话页

- **功能**：AI 聊天、流式响应、快捷问题
- **组件**：`van-nav-bar`（导航栏）、`ChatBubble`（聊天气泡）、`van-field`（输入框）、`van-button`（按钮）、`van-tag`（标签）
- **交互流程**：输入消息 → 发送 → 实时显示 AI 回复

## 本地开发

```bash
# 安装依赖
npm install

# 启动开发服务器
npm run dev

# 构建生产版本
npm run build

# 预览生产版本
npm run preview
```

## 开发说明

> 本项目目前仅实现了前端部分，后端正在开发中。

### 路由配置

| 路径 | 名称 | 说明 |
|------|------|------|
| `/` | Home | 首页（行程规划） |
| `/detail` | Detail | 行程详情页 |
| `/chat` | Chat | AI 对话页 |
| `/service` | Service | 服务页（预留） |
| `/profile` | Profile | 个人中心 |

### API 接口

- 基础地址：`http://159.75.169.224:1236`
- 行程推荐：`POST /api/travel/recommend`
- AI 对话（流式）：`POST /api/travel/chat`