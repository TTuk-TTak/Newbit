// Vue
import Vue from 'vue'
import App from './App.vue'
import router from './router'
import { store } from './store/store.js'
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
  'clanguage': {category: 'Languages', shownName: 'C', defaultImg: '',},
  'java': {category: 'Languages', shownName: 'Java', defaultImg: '',},
  'python' : {category: 'Languages', shownName: 'Python', defaultImg: '',},
  'csharp' : {category: 'Languages', shownName: 'C#', defaultImg: '',},
  'javascript' : {category: 'Languages', shownName: 'JavaScript', defaultImg: '',},
  'html': {category: 'Languages', shownName: 'html', defaultImg: '',},
  'kotlin': {category: 'Languages', shownName: 'Kotlin', defaultImg: '',},
  // 프론트엔드
  'react' : {category: 'Front-end', shownName: 'React', defaultImg: '',},
  'vue' : {category: 'Front-end', shownName: 'Vue.js', defaultImg: '',},
  'angular' : {category: 'Front-end', shownName: 'Angular', defaultImg: '',},
  'android' : {category: 'Front-end', shownName: 'Android', defaultImg: '',},
  'ios' : {category: 'Front-end', shownName: 'iOS', defaultImg: '',},
  'uiux' : {category: 'Front-end', shownName: 'UI/UX', defaultImg: '',},
  // 백엔드
  'node' : {category: 'Back-end', shownName: 'Node.js', defaultImg: '',},
  'php' : {category: 'Back-end', shownName: 'PHP', defaultImg: '',}, 
  'database' : {category: 'Back-end', shownName: 'Database', defaultImg: '',},
  'devops' : {category: 'Back-end', shownName: 'DevOps', defaultImg: '',},
  'springboot' : {category: 'Back-end', shownName: 'SpringBoot', defaultImg: '',},
  // 일반
  'git': {category: '일반', shownName: 'Git', defaultImg: '',},
  'testing': {category: '일반', shownName: '테스팅', defaultImg: '',},
  'aimi': {category: '일반', shownName: 'AI/머신러닝', defaultImg: '',},
  'cloud': {category: '일반', shownName: '클라우드', defaultImg: '',},
  'blockchain': {category: '일반', shownName: '블록체인', defaultImg: '',},
}

new Vue({
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount('#app')
