// Vue
import Vue from 'vue'
import App from './App.vue'
import router from './router'
import { store } from './store/store.js'
import vuetify from './plugins/vuetify'


// local
import CommonMethodsPlugin from './plugins/CommonMethodsPlugin.js'
import { keywords } from './plugins/Keywords'

// 3rd party
import VueApexCharts from "vue-apexcharts"
import VueClipboard from 'vue-clipboard2'

Vue.use(CommonMethodsPlugin)
Vue.use(VueApexCharts)
Vue.use(VueClipboard)

Vue.component("apexchart", VueApexCharts)


Vue.config.productionTip = false

// 서버 URL
Vue.prototype.$serverURL = process.env.VUE_APP_SERVER_URL
// 클라이언트 URL
Vue.prototype.$clientURL = process.env.VUE_APP_CLIENT_URL

Vue.prototype.$KEYWORDS = keywords

new Vue({
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount('#app')
