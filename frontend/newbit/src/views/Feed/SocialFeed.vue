<template>
  <v-row
    class=" pa-2 px-4"
  >
  {{ $store.getters.categorizedKeyword }}
    <v-row
      class="pt-2"
    >
      <v-col
        v-for="i in 10"
        :key="`archived` + i"    
        class="pa-1 pb-1"
        cols=12
      >
        <post-card></post-card>
      </v-col>
    </v-row>
  </v-row>

</template>

<script>
import { mapState } from 'vuex'
import PostCard from '@/components/Cards/PostCard.vue'

export default {
  name: 'SocialFeed',
  components: {
    PostCard,
  },
  created () {

  },
  computed: {
    ...mapState([
      'socialFeedLoadedAt',
      'socialFeed',
    ])
  },
  methods: {
    loadPost () {
      if (!this.socialFeed.isAtLast)
      this.$store.dispatch('loadPosts')
    },
  },
  watch: {
    socialFeedLoadedAt: {
      handler () {
          console.log('렌더링하는 함수 구현')
      }
    },
  },
}
</script>

<style>
</style>