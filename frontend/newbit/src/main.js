// Vue
import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import vuetify from './plugins/vuetify'

// local
import CommonMethodsPlugin from './plugins/CommonMethodsPlugins'

// 3rd party
import VueApexCharts from "vue-apexcharts";

Vue.use(CommonMethodsPlugin);
Vue.use(VueApexCharts);
Vue.component("apexchart", VueApexCharts);

Vue.config.productionTip = false
Vue.prototype.$serverURL = process.env.VUE_APP_SERVER_URL

// 키워드 목록
Vue.prototype.$KEYWORDS = {
  'Languages': {
    'C': 'clanguage',
    'Java': 'Java',
    'Python': 'Python',
    'Javascript': 'javascript',
    'html': 'html',
    'Kotlin': 'Kotlin',    
  },
  'Front-end': {
    'React': 'React',
    'Vue.js': 'Vue.js',
    'Angular': 'Angular',
    'Android': 'Android',
    'iOS': 'iOS',
    'UI/UX': 'UI/UX',
  },
  'Back-end': {
    'Node.js': 'Node.js',
    'PHP': 'PHP', 
    'Database': 'Database',
    'Dev/Ops': '데브옵스',
    'SpringBoot': 'SpringBoot',    
  },
  '일반': {
    'Git': 'Git',
    '테스팅' : '테스팅',
    'AI/머신러닝': 'AI/머신러닝',
    '클라우드': '클라우드',
    '블록체인': '블록체인',
  },
}

new Vue({
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount('#app')
