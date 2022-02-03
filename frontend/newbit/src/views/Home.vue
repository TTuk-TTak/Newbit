<template>
  <v-container>
    <login-modal :isLogged='true'></login-modal>
    <first-login-modal :isLogged='true'></first-login-modal>
    <v-row>
      <v-col cols="3">
        <post-create-modal :isOpened='false'></post-create-modal>
        <the-profile-bar></the-profile-bar>
      </v-col>
      <v-col>
        <content-feed class="p-0"></content-feed>
      </v-col>
      <v-col cols='2'>
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

import PostDetail from '@/views/PostDetail/PostDetail.vue'

import PostCard from '@/components/Cards/PostCard.vue'
// import ContentFeed from '@/views/Feed/ContentFeed.vue'

export default {
  name: 'Home',
  components: {
    TheProfileBar,
    TheKeywordBar,
    ContentFeed,
    PostCreateModal,
    LoginModal,
    FirstLoginModal,
  },
}
</script>
