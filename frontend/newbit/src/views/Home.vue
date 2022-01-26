<template>
  <v-container>
    <v-row>
      <v-col cols="3">
        <the-profile-bar></the-profile-bar>
      </v-col>
      <v-col>
        <not-found></not-found>
        <content-feed></content-feed>
      </v-col>
      <v-col cols='2'>
        <h1>키워드 바</h1>
      </v-col>
    </v-row>

  </v-container>
</template>

<script>

import ContentFeed from '@/views/Feed/ContentFeed.vue'
import TheProfileBar from '@/views/Bars/TheProfileBar.vue'

export default {
  name: 'Home',
  components: {
    ContentFeed,
    TheProfileBar,
  },
}
</script>
