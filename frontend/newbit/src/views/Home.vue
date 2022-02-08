<template>
  <v-container>
    <login-modal :isLogged='true'></login-modal>
    <first-login-modal :isLogged='true'></first-login-modal>
    <v-row justify="center">
      <v-col cols="3" class="pr-5 mr-5">
        <post-create-modal></post-create-modal>
        <the-profile-bar></the-profile-bar>
      </v-col>
      <v-col cols="6">
        <router-view></router-view>
      </v-col>
      <v-col cols='2' class="ml-5 mr-10">
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
