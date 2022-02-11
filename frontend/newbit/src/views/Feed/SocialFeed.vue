<template>
  <v-row
    class=" pa-2 px-4 fill-height"
    align='start'
  >
    <v-row
      class="pt-2"
      justify='center'
      align='start'
      id='socialFeed'
    >
      <v-col
        v-for="(key, value) in posts"
        :key="`archived` + value"    
        class="pa-1 pb-1"
        cols=12
      >
        <post-card></post-card>
      </v-col>
    </v-row>
    <v-row
      class="justify-self-center align-self-end"
    >
      <v-spacer></v-spacer>
      <infinite-loading
        class="mt-5 pt-5 justify-self-center align-self-center"
        @infinite="infiniteHandler" 
        >
        </infinite-loading>
        <v-spacer></v-spacer>
    </v-row>
  </v-row>

</template>

<script>
import InfiniteLoading from 'vue-infinite-loading'

import { mapState } from 'vuex'
import PostCard from '@/components/Cards/PostCard.vue'

export default {
  name: 'SocialFeed',
  components: {
    InfiniteLoading,
    PostCard,
  },  
  data: () => ({
    posts: [],
  }),
  created () {
    const lastPage = this.socialFeed.pageNum
    for (let pageNum = 0; pageNum <= lastPage; pageNum ++) {
      this.renderPost(pageNum)
    }
  },
  computed: {
    ...mapState([
      'socialFeedLoadedAt',
      'socialFeed',
    ])
  },
  methods: {
    loadPost () {
      if (!this.socialFeed.isAtLast){
        this.$store.dispatch('loadPosts')
      }
      return true
    },
    renderPost (page) {
      for (let postKey in this.socialFeed.posts[page]) {
        const post = this.socialFeed.posts[page][postKey]
        this.posts.push(post)
      }
    },
    async infiniteHandler ($state) {
      console.log('InfiniteHandler')
      if (!this.socialFeed.isAtLast){
        await this.loadPost()
        console.log(1111)
      } else {
        $state.complete()
      }
    }
  },
  watch: {
    socialFeedLoadedAt: {
      handler () {
        const newPage = this.socialFeed.pageNum
        this.renderPost(newPage)
      }
    },
  },

}
</script>

<style>
</style>