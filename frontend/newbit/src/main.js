// Vue
import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import vuetify from './plugins/vuetify'

// local
import CommonMethodsPlugin from './plugins/CommonMethodsPlugin.js'

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

// 키워드 목록
// key: 변수명, value: object
Vue.prototype.$KEYWORDS = {
  // 프로그래밍 언어
  'clanguage': {category: 'Languages', shownName: 'C', },
  'java': {category: 'Languages', shownName: 'Java',},
  'python' : {category: 'Languages', shownName: 'Python',},
  'csharp' : {category: 'Languages', shownName: 'C#',},
  'javascript' : {category: 'Languages', shownName: 'JavaScript',},
  'html': {category: 'Languages', shownName: 'html',},
  'kotlin': {category: 'Languages', shownName: 'Kotlin',},
  // 프론트엔드
  'react' : {category: 'Front-end', shownName: 'React',},
  'vue' : {category: 'Front-end', shownName: 'Vue.js',},
  'angular' : {category: 'Front-end', shownName: 'Angular',},
  'android' : {category: 'Front-end', shownName: 'Android',},
  'ios' : {category: 'Front-end', shownName: 'iOS',},
  'uiux' : {category: 'Front-end', shownName: 'UI/UX',},
  // 백엔드
  'node' : {category: 'Back-end', shownName: 'Node.js',},
  'php' : {category: 'Back-end', shownName: 'PHP',}, 
  'database' : {category: 'Back-end', shownName: 'Database',},
  'devops' : {category: 'Back-end', shownName: 'DevOps',},
  'springboot' : {category: 'Back-end', shownName: 'SpringBoot',},
  // 일반
  'git': {category: '일반', shownName: 'Git',},
  'testing': {category: '일반', shownName: '테스팅',},
  'aimi': {category: '일반', shownName: 'AI/머신러닝',},
  'cloud': {category: '일반', shownName: '클라우드',},
  'blockchain': {category: '일반', shownName: '블록체인',},
}

new Vue({
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount('#app')
