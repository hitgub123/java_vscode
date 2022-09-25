import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

import axios from 'axios'
import './assets/css/reset.css'

import { createI18n } from 'vue-i18n'

import { messages } from '../lang/vue-lang'


// 以下两个注释用来解决Could not find a declaration file for module 
// eslint-disable-next-line @typescript-eslint/ban-ts-comment
// @ts-ignore
import installElementPlus from './plugins/element'

const i18n = createI18n({
    locale: 'ja', // set locale/
    messages, // set locale messages
})

const app = createApp(App)
installElementPlus(app)
app.config.globalProperties.$axios = axios;
app.use(i18n).use(store).use(router).mount('#app')