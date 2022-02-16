<template >
  <v-card
    outlined
    class="pa-4 pt-2 cardMargin"
    >
    <div class="mx-2" style="border-bottom:1px solid lightgray">
      <v-tabs
        class="ml-1 mr-3"
        slider-color='#0d0e23'
      >
        <v-tab class="contentTab" @click="[setHot(), changeType(), ]">인기 컨텐츠</v-tab>
        <v-tab class="contentTab" @click="[setNew(), changeType()]">최신 컨텐츠</v-tab>
        <hr>
      </v-tabs>
    </div>
    <keyword-toggler class="px-1 mt-3"
    @query-string-changed = 'changeType'></keyword-toggler>
    <v-row
      class='pa-2 pt-3'
    >
      <v-col
        class="pa-2"
        cols="6"
        v-for="(content, index) in contents"
        :key="index"
      >
        <content-card
        :content="content"
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
import _ from 'lodash'
import axios from 'axios'
import InfiniteLoading from 'vue-infinite-loading'

import { mapState } from 'vuex'

import KeywordToggler from '@/components/Keyword/KeywordToggler.vue'
import ContentCard from '@/components/Cards/ContentCard.vue'


export default {
  name: 'ContentFeed',
  components: {
    InfiniteLoading,
    KeywordToggler,
    ContentCard,
  },
  data: () => ({
    page: 1,
    contents: [],
    lastContentCode: 0,
    sortingType : "hot",
    infinityHandlerRendered: true,
    keywordString : null,
    recentResponse: null,
  }),
  // created () {
  //   this.getContentsHot()
  // },
  computed: {
    ...mapState([
      // 'curationFeed'
      'user',
    ])
  },
  methods: {
    setHot () {
      this.sortingType = "hot"
      this.lastContentCode= 0
      console.log(this.sortingType)
    },
    setNew () {
      this.sortingType = "new"
      this.lastContentCode= 0
      console.log(this.sortingType)
    },
    changeType (queryString) {
      queryString ? this.keywordString = queryString : this.keywordString = null
      console.log("changeType")
      this.contents= [],
      this.lastContentCode= 0
      this.infinityHandlerRendered = false
      this.infiniteHandler()
      setTimeout(this.infinityHandlerRendered = true, 300)
    },
    infiniteHandler ($state) {
      const size = 8
      axios({
        method: 'get',
        url: `${this.$serverURL}/content/list?`
          + `sorting=${this.sortingType}`
          + `&uid=${this.user.userCode}`
          + `&lastcontentcode=${this.lastContentCode}`
          + `&size=${size}`
          + `&keyword=${this.keywordString}`,
      })
      .then(res => {
        if (res.data.length !== 0 && res.data !== this.recentResponse) {
          this.lastContentCode = _.last(res.data).contentCode
          this.recentResponse = res.data
          // console.log(this.lastContentCode)
          console.log('핸들러', res.data)
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
    }
  },
}
</script>
<style scope>
.cardMargin{
  padding-left: 1000px; 
}
.v-tab.v-tab.contentTab {
  font-size: 1.2em;
  font-weight: 500;
  font-family: 'KoPub Dotum';
  color : #818181;
}
  .v-tab.v-tab--active.contentTab{
    color : #0d0e23;
    font-weight: 700;
  }
</style>