import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import vuetify from './plugins/vuetify'

import VueApexCharts from "vue-apexcharts";
Vue.use(VueApexCharts);
Vue.component("apexchart", VueApexCharts);

Vue.config.productionTip = false
Vue.prototype.$serverURL = process.env.VUE_APP_SERVER_URL

new Vue({
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount('#app')
