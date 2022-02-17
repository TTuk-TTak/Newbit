<template>
  <v-card
    outlined
    class="pa-4 pt-2 cardMargin"
  >
    <!-- color="feedBackground" -->
    <div class="mx-2" style="border-bottom:1px solid lightgray">
      <v-tabs
        class="ml-1 mr-3"
        slider-color='#0d0e23'
      >
        <v-tab class="contentTab">컨텐츠</v-tab>
        <!-- <v-tab class="contentTab">게시글</v-tab> -->
        <v-row
          class="pt-2 pr-4"
          justify='end'
        >
          <v-switch
            v-model="unreadOnly"
            label="읽지 않은 글만 보기"
          ></v-switch>
        </v-row>
        <hr>
      </v-tabs>
    </div>
    <keyword-toggler 
      class="px-1 mt-3"
      @query-string-changed = 'resetContents'
      ></keyword-toggler>
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

export default {
  name: 'Archive',
  components: {
    InfiniteLoading,
    KeywordToggler,
    ContentCard,
  },
  data () {
    return {
      posts: [],
      contents: [],
      lastContentCode: 0,
      lastPostCode: 0,
      unreadOnly: false,
      infinityHandlerRendered: true,
      keywordString: null
    }
  },
  computed: {
    ...mapState([
      'user',
      'archivingFeedLoadedAt',
    ])
  },
  methods: {
    resetContents (queryString) {
      queryString ? this.keywordString = queryString : this.keywordString = null
      this.contents = []
      this.lastContentCode = 0
      this.infinityHandlerRendered = false
      this.infiniteHandler()
      setTimeout(this.infinityHandlerRendered = true, 300)
    },
    infiniteHandler ($state) {
      const size = 8
      axios({
        method: 'get',
        url: `${this.$serverURL}/scrap/content`
          + (this.unreadOnly ? `/unread?` : `?`)
          + `uid=${this.user.userCode}`
          + `&lastcontentcode=${this.lastContentCode}`
          + `&size=${size}`
          + `&keyword=${this.keywordString}`
      })
        .then(res => {
          if (res.data.length !== 0) {
            console.log(res.data)
            this.lastContentCode = _.last(res.data).contentCode
            for (let key in res.data) {
              this.contents.push(res.data[key])
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
    // archiveAll() {
    //   console.log(11111)
    //   for (let i=0; i <=700; i++) {
    //     console.log(i)
    //     this.archiveContent(i)
    //   }
    // },
    // archiveContent(contentCode) {
    //   console.log(contentCode)
    //   axios({
    //     method: 'POST',
    //     url: `${this.$serverURL}/content/scrap`,
    //     data: {
    //       'uid': this.user.userCode,
    //       'cid': contentCode,
    //     },
    //   })
    //   .then((res) => {
    //     console.log('archived', res)
    //     if (res.data === 'success') {
    //       this.content.scrapped = true
    //     }
    //   })
    //   .catch((err) => {
    //     console.log(err)
    //   })  
    // },

  },
  watch: {
    unreadOnly: {
      handler() {
        this.lastContentCode = 0
        this.lastPostCode = 0
        this.posts = []
        this.contents = []
        this.infiniteHandler()
      }
    },
    archivingFeedLoadedAt: {
      handler() {
      this.lastContentCode = 0
      this.lastPostCode = 0
      this.posts = []
      this.contents = []
      this.infiniteHandler()
      }
    }
  },
}
</script>

<style scope >
.cardMargin{
  padding-left: 1000px; 
}


</style>