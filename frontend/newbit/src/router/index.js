import Vue from 'vue'
import VueRouter from 'vue-router'

import Home from '../views/Home.vue'
import NotFound from '@/views/Error/NotFound.vue'

import Signup from '@/views/User/Signup.vue'
import SignupComplete from '@/views/User/SignupComplete.vue'
import Login from '@/views/User/Login.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },

  //Authentication
  {
    path: '/signup',
    name: 'Signup',
    component: Signup
  },

  {
    path: '/signup/complete',
    name: 'SignupComplete',
    component: SignupComplete
  },

  {
    path: '/login',
    name: 'Login',
    component: Login
  },


  // 404 Error
  {
    path: '*',
    name: 'NotFound',
    component: NotFound
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
