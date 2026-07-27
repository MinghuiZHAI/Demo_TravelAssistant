import { createApp } from 'vue'
import 'vant/lib/index.css'
import './style.css'
import App from './App.vue'
import { Button, Card } from "vant";
import router from './router'


const app = createApp(App);
app.use(Button)
app.use(Card)
app.use(router)
app.mount('#app')
