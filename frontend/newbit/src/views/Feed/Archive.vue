<template>
  <v-card
    outlined
    class="pa-4 pt-2 cardMargin"
  >
    <div class="mx-2" style="border-bottom:1px solid lightgray">
      <v-tabs
        class="ml-1 mr-3"
        slider-color='#0d0e23'
      >
        <v-tab 
          class="contentTab"
          @click="setContent()"
          >컨텐츠</v-tab>
        <v-tab 
          class="contentTab"
          @click="setPost()"
          >게시글</v-tab>
        <v-row
          class="pt-2 pr-4"
          justify='end'
        >
          <v-switch
            v-show="searchType==='content'"
            v-model="unreadOnly"
            label="읽지 않은 글만 보기"
          ></v-switch>
        </v-row>
        <hr>
      </v-tabs>
    </div>
    <keyword-toggler
      v-show="searchType==='content'"
      class="px-1 mt-3"
      @query-string-changed = 'resetFeed'
      ></keyword-toggler>
    <!-- 컨텐츠 카드 -->
    <v-row
      class="pa-2 pt-3"
    >
      <v-col
        class="pa-2"
        v-for="content in contents"
        :key="`archived` + content.contentCode"    
        cols=6
      >
        <content-card
          v-if="!unreadOnly || content.read === false"
          :content='content'
        ></content-card>
      </v-col>
    </v-row>
    <!-- 게시글 카드 -->
    <v-row
      class=" pa-2 px-4"
    >
      <v-row
        v-if='posts'
        class="pt-2"
        justify='center'
        align='start'
        cols=12
      >
        <!-- id='socialFeed' -->
        <v-col
          v-for="(post, index) in posts"
          :key="`social` + index" 
          class="pa-1 pb-1 bottomBorder"
          cols=12
        >
          <post-card :post='post'></post-card>
        </v-col>
      </v-row>
    </v-row>

    <!-- 무한 스크롤 -->
    <v-row
      class="mt-5 pt-5 justify-self-center align-self-end"
    >
      <v-spacer></v-spacer>
      <infinite-loading
        v-if='user && infinityHandlerRendered'
        class="mt-5 pt-5 justify-self-center align-self-center"
        @infinite="infiniteHandler" 
        >
        <template slot="no-more">
          2022 - Newbit
        </template>
        </infinite-loading>
        <v-spacer></v-spacer>
    </v-row>

  </v-card>
</template>

<script>
// 3rd party
import InfiniteLoading from 'vue-infinite-loading'
import _ from 'lodash'
import axios from 'axios'
// Vue
import { mapState } from 'vuex'
// local
import KeywordToggler from '@/components/Keyword/KeywordToggler.vue'
import ContentCard from '@/components/Cards/ContentCard.vue'
import PostCard from '@/components/Cards/PostCard.vue'

export default {
  name: 'Archive',
  components: {
    InfiniteLoading,
    KeywordToggler,
    ContentCard,
    PostCard,
  },
  data () {
    return {
      posts: [],
      contents: [],
      lastContentCode: 0,
      lastPostCode: 0,

      searchType: 'content',
      showKeywordToggler: false,
      unreadOnly: false,  
      infinityHandlerRendered: true,
      keywordString: null,

      query: ''
    }
  },
  computed: {
    ...mapState([
      'user',
      'archivingFeedLoadedAt',
    ])
  },
  methods: {
    resetFeed (queryString) {
      queryString ? this.keywordString = queryString : this.keywordString = null
      this.lastContentCode = 0
      this.lastPostCode = 0
      this.posts = []
      this.contents = []
    },

    async setPost () {
      this.searchType = 'post'
      await this.resetFeed()
      this.infiniteHandler()
    },

    async setContent() {
      this.searchType = 'content'
      await this.resetFeed()
      this.infiniteHandler()
    },
    
    makeQueryString () {
      let queryString = ''
      const size = 8
      if (this.searchType === 'content') {
        queryString = `${this.$serverURL}/scrap/content`
            + (this.unreadOnly ? `/unread?` : `?`)
            + `uid=${this.user.userCode}`
            + `&lastcontentcode=${this.lastContentCode}`
            + `&size=${size}`
            + `&keyword=${this.keywordString}`
      } else {
        // /scrap/post?uid={user_code}&lastpostcode={lastpostcode}&size={size}&keyword={keywordchip}
        queryString = `${this.$serverURL}/scrap/post?`
            + `uid=${this.user.userCode}`
            + `&lastpostcode=${this.lastPostCode}`
            + `&size=${size}`
            + `&keyword=null`
      }
      this.query = queryString
    },
    infiniteHandler ($state) {
      this.makeQueryString()
      console.log(this.query)
      axios({
        method: 'get',
        url: this.query
      })
        .then(res => {
          if (res.data.length !== 0) {
            console.log(res.data)
            if (this.searchType === 'post') {
              this.lastPostCode = _.last(res.data).postCode
              for (let key in res.data) {
                this.posts.push(res.data[key])
              }
            } else {
              this.lastContentCode = _.last(res.data).contentCode
              for (let key in res.data) {
                this.contents.push(res.data[key])
              }
            }
            $state.loaded();
          } else {
            $state.complete();
          }
        })
        .catch((err) => {
          console.log(err)
        })
    },
  },
  watch: {
    unreadOnly: {
      async handler() {
        await this.resetFeed()
        this.infiniteHandler()
      }
    },
    archivingFeedLoadedAt: {
      async handler() {
        await this.resetFeed()      
        this.infiniteHandler()
      }
    },

  },
}
</script>

<style scope >
.cardMargin{
  padding-left: 1000px; 
}

.bottomBorder {
  border-bottom: 1px solid rgba(0, 0, 0, 0.1);
}

</style>