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
      class="mt-5 pt-5 justify-self-center align-self-end"
    >
    <p>111</p>
      <v-spacer></v-spacer>
      <infinite-loading
        v-if="socialFeedLoadedAt !== null"
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
    if (this.socialFeed.pageNum !== -1) {
      for (let pageNum = 0; pageNum <= lastPage; pageNum ++) {
        this.renderPost(pageNum)
      } 
    } else {
      console.log('ㅋㅋㅋ')
      // this.loadPost()
    }
  },
  computed: {
    ...mapState([
      'socialFeedLoadedAt',
      'socialFeed',
      'user',
    ])
  },
  methods: {
    loadPost () {
      if (!this.socialFeed.isAtLast){
        this.$store.dispatch('loadPosts')
        return true
      }
      return false
    },
    renderPost (page) {
      for (let postKey in this.socialFeed.posts[page]) {
        const post = this.socialFeed.posts[page][postKey]
        this.posts.push(post)
      }
    },
    infiniteHandler ($state) {
      console.log('finite')
      $state.complete()
      // setTimeout($state.loaded(), 1000)
      // $state.complete()
      // console.log('InfiniteHandler')
      // if (this.socialFeed.isAtLast) {
      //   $state.complete()
      // } else {
      //   this.loadPost()
      //   setTimeout($state.loaded(), 1000)

      
      // if (this.socialFeed.isAtLast) {
      //   $state.complete()
      // } else {
      //   if (this.loadPost()) {
      //     setTimeout($state.loaded(), 1000)
      //     // $state.loaded()
      //   } else {
      //     $state.complete()
      //   }
      // }
      // if (!this.socialFeed.isAtLast){
      //   // 로딩 구현 완료시 활성화 할 것.
      //   if (!this.socialFeed.isAtLast) {
      //   }
      //   console.log(1111)
      // } else {
      //   $state.complete()
      // }
    }
  },
  watch: {
    socialFeedLoadedAt: {
      handler () {
        const newPage = this.socialFeed.pageNum
        this.renderPost(newPage)
      }
    },
    user: {
      handler() {
        this.loadPost()
      }
    }
  },

}
</script>

<style>
</style>