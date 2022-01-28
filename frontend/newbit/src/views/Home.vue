<template>
  <v-container>
    <v-row>
      <v-col cols="3">
        <post-create-modal
          :isOpened='true'
        ></post-create-modal>
        <the-profile-bar></the-profile-bar>
      </v-col>
      <v-col>
        <content-feed
          class="p-0"
        ></content-feed>
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
import ContentFeed from '@/views/Feed/ContentFeed.vue'

import PostCreateModal from '@/components/Modals/PostCreateModal.vue'


export default {
  name: 'Home',
  components: {
    TheProfileBar,
    TheKeywordBar,
    ContentFeed,
    PostCreateModal,
  },
}
</script>
