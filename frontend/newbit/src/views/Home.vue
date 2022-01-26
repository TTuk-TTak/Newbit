<template>
  <v-container>
    <v-row>
      <v-col
        cols="3"
      >
        <h1>프로필 바</h1>
      </v-col>
      <v-col>
        <not-found></not-found>
        <content-feed></content-feed>
      </v-col>
      <v-col
        cols='2'
      >
        <h1>키워드 바</h1>
      </v-col>
    </v-row>

  </v-container>
</template>

<script>
import NotFound from '@/views/Error/NotFound.vue'
import ContentFeed from '@/views/Feed/ContentFeed.vue'

  export default {
    name: 'Home',
    components: {
      ContentFeed,
      NotFound,
    },
  }
</script>
