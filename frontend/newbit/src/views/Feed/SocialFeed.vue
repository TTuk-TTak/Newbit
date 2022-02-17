<template>
  <v-row
    class="pa-2 px-4 fill-height"
    align='start'
  >
    <v-row
      class="pt-2"
      justify='center'
      align='start'
      id='socialFeed'
      cols=12
    >
      <v-col
        v-for="(post, index) in posts"
        :key="`social` + index"    
        class="pa-1 pb-1 py-2"
        cols=12
      >
        <post-card :post='post'></post-card>
      </v-col>
    </v-row>
    <!-- 무한 스크롤 -->
    <v-col
      class="mt-5 pt-5 justify-self-center align-self-end"
      cols=12
    >

      <infinite-loading
        v-if='user'
        class="mt-5 pt-5 justify-self-center align-self-center"
        @infinite="infiniteHandler" 
        >
        <template slot="no-more">
          2022 - Newbit
        </template>
        </infinite-loading>

    </v-col>
  </v-row>

</template>

<script>
import _ from 'lodash'
import axios from 'axios'
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
    page: 1,
    posts: [],
    lastPostCode: 0,
    // isINFLoaderRendered: false,
  }),
  // created () {
  //   const lastPage = this.socialFeed.pageNum
  //   if (this.socialFeed.pageNum !== -1) {
  //     for (let pageNum = 0; pageNum <= lastPage; pageNum ++) {
  //       this.renderPost(pageNum)
  //     } 
  //   } else {
  //     console.log('ㅋㅋㅋ')
  //     // this.loadPost()
  //   }
  // },
  computed: {
    ...mapState([
      // 'socialFeedLoadedAt',
      // 'socialFeed',
      'user',
    ])
  },
  methods: {
    // loadPost () {
    //   if (!this.socialFeed.isAtLast){
    //     this.$store.dispatch('loadPosts')
    //     return true
    //   }
    //   return false
    // },
    // renderPost (page) {
    //   for (let postKey in this.socialFeed.posts[page]) {
    //     const post = this.socialFeed.posts[page][postKey]
    //     this.posts.push(post)
    //   }
    // },
    infiniteHandler ($state) {
      const size = 8
      axios({
        method: 'get',
        url: `${this.$serverURL}/post/list?`
          + `uid=${this.user.userCode}`
          + `&lastpostcode=${this.lastPostCode}`
          + `&size=${size}`,
      })
        .then(res => {
          if (res.data.length !== 0) {
            this.page += 1;
            this.lastPostCode = _.last(res.data).postCode
            console.log(res.data)
            for (let key in res.data) {
              this.posts.push(res.data[key])
            }
            
            $state.loaded();
          } else {
            $state.complete();
          }
        })
        .catch((err) => {
          console.log(err)
        })
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
  mounted () {    
    // this.isINFLoaderRendered = true
  }
  // watch: {
  //   socialFeedLoadedAt: {
  //     handler () {
  //       const newPage = this.socialFeed.pageNum
  //       this.renderPost(newPage)
  //     }
  //   },
  //   user: {
  //     handler() {
  //       this.loadPost()
  //     }
  //   }
  // },

}
</script>

<style>
</style>