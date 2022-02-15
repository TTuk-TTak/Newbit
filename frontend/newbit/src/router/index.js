import Vue from 'vue'
import VueRouter from 'vue-router'

import Home from '@/views/Home.vue'
import NotFound from '@/views/Error/NotFound.vue'

import Signup from '@/views/User/Signup.vue'
import SignupComplete from '@/views/User/SignupComplete.vue'
import Login from '@/views/User/Login.vue'

import ProfileDetail from '@/views/Profile/ProfileDetail.vue'
import ProfileEdit from '@/views/Profile/ProfileEdit.vue'

import ContentFeed from '@/views/Feed/ContentFeed.vue'
import SocialFeed from '@/views/Feed/SocialFeed.vue'
import SearchFeed from '@/views/Feed/SearchFeed.vue'
import Archive from '@/views/Feed/Archive.vue'

import PostDetail from '@/views/PostDetail/PostDetail.vue'


Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home,
    children: [
      // feed
      {
        path: 'social',
        name: 'Social',
        component: SocialFeed
      },
      {
        path: 'social/:id',
        component: PostDetail
      },
      {
        path: 'content',
        name: 'Curation',
        component: ContentFeed
      },
      {
        path: 'archive',
        name: 'Archiving',
        component: Archive
      },
      {
        path: '',
        redirect: 'social' // default 
      },
      // 윤수 made Search Feed here
      {
        path: 'search',
        name: 'Search',
        component: SearchFeed,
        //props: true,
      },



      //Profile  
      {
        path: 'profile/detail',
        name: 'ProfileDetail',
        component: ProfileDetail
      },
      {
        path: 'profile/edit',
        name: 'ProfileEdit',
        component: ProfileEdit
      },
    ]
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
