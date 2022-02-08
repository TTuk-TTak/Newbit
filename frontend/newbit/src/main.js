// Vue
import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import vuetify from './plugins/vuetify'

// Import the functions you need from the SDKs you need
import { initializeApp } from "firebase/app";
import { getAnalytics } from "firebase/analytics";
// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// local
import CommonMethodsPlugin from './plugins/CommonMethodsPlugin.js'

// 3rd party
import VueApexCharts from "vue-apexcharts";

Vue.use(CommonMethodsPlugin);
Vue.use(VueApexCharts);
Vue.component("apexchart", VueApexCharts);

Vue.config.productionTip = false

// 서버 URL
Vue.prototype.$serverURL = process.env.VUE_APP_SERVER_URL
// 클라이언트 URL
Vue.prototype.$clientURL = process.env.VUE_APP_CLIENT_URL

// 키워드 목록
// 1. 대분류: Languages, Frontend, Backend, 일반
// 2. 소분류: Key==보여질 이름 Value == queryString에 사용할 이름
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
    'DevOps': '데브옵스',
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

// Your web app's Firebase configuration
// For Firebase JS SDK v7.20.0 and later, measurementId is optional
const firebaseConfig = {
  apiKey: "AIzaSyC67xnI9S9rah1WpcHAlmI0SkYRvovQqXM",
  authDomain: "newbit-f8fb2.firebaseapp.com",
  projectId: "newbit-f8fb2",
  storageBucket: "newbit-f8fb2.appspot.com",
  messagingSenderId: "81764698708",
  appId: "1:81764698708:web:630329c7ed1e90d3d1fbc1",
  measurementId: "G-6RFNFZSSBQ"
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);
const analytics = getAnalytics(app);

new Vue({
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount('#app')
