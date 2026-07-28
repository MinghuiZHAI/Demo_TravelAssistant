import { createApp } from 'vue'
import 'vant/lib/index.css'
// import './style.css'
import App from './App.vue'
import {     /*引入vant组件元素*/
    Button,
    Card,
    Tabbar,
    TabbarItem,
    Icon, NavBar,
    NoticeBar,
    Field,
    Popup,
    Picker,
} from "vant";
import router from './router'


const app = createApp(App);
app.use(Button)
app.use(Card)
app.use(Tabbar)
app.use(TabbarItem)
app.use(Icon)
app.use(NavBar)
app.use(NoticeBar)
app.use(Field)
app.use(Popup)
app.use(Picker)
app.use(router)
app.mount('#app')
