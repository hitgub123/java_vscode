import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

import axios from 'axios'
 

// 以下两个注释用来解决Could not find a declaration file for module 
// eslint-disable-next-line @typescript-eslint/ban-ts-comment
// @ts-ignore
import installElementPlus from './plugins/element'

const app = createApp(App)
installElementPlus(app)
app.config.globalProperties.$axios = axios;
app.use(store).use(router).mount('#app')