import Vue from 'vue'
import VueRouter from 'vue-router'
import Signup from '../views/user/Signup.vue'
import Login from '../views/user/Login.vue'
import Home from '../views/Home.vue'
import SignupComplete from '../views/user/SignupComplete.vue'
import ProfileOwnInfo from '../components/ProfileBar/ProfileOwnInfo.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/signup',
    name: 'Signup',
    component: Signup
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/signup-complete',
    name: 'SignupComplete',
    component: SignupComplete
  },
  {
    path: '/profile-own-info',
    name: 'ProfileOwnInfo',
    component: ProfileOwnInfo
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
