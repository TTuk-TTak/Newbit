<template>
  <v-container>
    <login-modal></login-modal>
    <first-login-modal></first-login-modal>
    <v-row justify="center">
      <v-col
        cols="3"
        class="pr-5 mr-5"
      >
        <post-create-modal></post-create-modal>
        <the-profile-bar></the-profile-bar>
      </v-col>
      <v-col cols="6">
        <router-view :key="$route.fullPath"></router-view>
      </v-col>
      <v-col
        cols='2'
        class="ml-5 mr-10"
      >
        <the-keyword-bar></the-keyword-bar>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import TheProfileBar from '@/views/Bars/TheProfileBar.vue'
import TheKeywordBar from '@/views/Bars/TheKeywordBar.vue'

import PostCreateModal from '@/components/Modals/PostCreateModal.vue'
import LoginModal from '@/components/Modals/LoginModal/LoginModal.vue'
import FirstLoginModal from '@/components/Modals/FirstLoginModal.vue'


export default {
  name: 'Home',
  components: {
    TheProfileBar,
    TheKeywordBar,

    LoginModal,
    FirstLoginModal,
    PostCreateModal,
  },
  methods: {
  }
}
</script>


<style>
/* 포스트 - 텍스트 */
.post-text {
  display: -webkit-box;
  -webkit-line-clamp: 3;
  -webkit-box-orient: vertical;
  overflow: hidden;
}
/* 포스트-날짜 */
.date{
  color : #919191;
  font-family: 'KoPub Dotum';
  font-weight: 100;
  font-size : 0.9em;
}
/* 포스트 - 작성자 */
.writer{
  font-size : 1.1em;
}


/* 본문 글씨체 */
.theme--light.v-card > .v-card__text {
  font-family: 'KoPub Dotum';
  font-weight: 400;
  color : #272727;
}
</style>