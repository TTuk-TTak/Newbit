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
  }),

  computed: {
    ...mapState([
      'user',
      'socialFeedLoadedAt',
    ])
  },
  methods: {
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
    }
  },
  // mounted () {    
  //   // this.isINFLoaderRendered = true
  // },
  watch: {
    socialFeedLoadedAt: {
      handler () {
        console.log(this.socialFeedLoadedAt)
        this.posts = []
        this.lastPostCode = 0
        this.infiniteHandler()
      }
    },
    // user: {
    //   handler() {
    //     this.loadPost()
    //   }
    // }
  },

}
</script>

<style>
</style>