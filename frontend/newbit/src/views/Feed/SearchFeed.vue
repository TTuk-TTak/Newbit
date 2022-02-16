<template>
  <v-card
    class="pa-2"
    color="feedBackground"
  >
    <!-- 검색창  -->
    <v-card
      outlined
      class="pa-5 my-2 mx-2"
    >
      <v-row
        align='center'
      >
        <v-col
          cols=auto
        >
          <v-icon
            class="align-self-center"
            large
            @click="searchbtn()"   
          >mdi-magnify</v-icon>
        </v-col>
        <v-col>
          <v-text-field
            placeholder="게시글, 컨텐츠, 사용자 검색이 가능합니다."
            v-model="searchString"      
            class="mb-2 mx-2"
            hide-details
            type="String"
          ></v-text-field>  
        </v-col>
      </v-row>
    </v-card>
    <!-- 탭전환 -->
    <div class="mx-2" style="border-bottom:1px solid lightgray">
      <v-tabs
        class="ml-1 mr-3"
        slider-color='#0d0e23'
      >
        <v-tab class="contentTab" @click="[changeType('post')]">게시글</v-tab>
        <v-tab class="contentTab" @click="[changeType('content')]">컨텐츠</v-tab>
        <v-tab class="contentTab" @click="[changeType('user')]">사용자</v-tab>
        <hr>
      </v-tabs>
    </div>

  <!-- 1. 소셜피드 - 결과-->
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
        class="pa-1 pb-1"
        cols=12
      >
        <post-card :post='post'></post-card>
      </v-col>
    </v-row>
  </v-row>
  <!-- 2. 컨텐츠: 결과 -->
    <v-row
      v-if='contents'
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
  <!-- 3. 유저: 결과 -->
  <v-list
    v-if="users" 
    two-line>
  
    <user-card
      v-for="(userInfo, index) in users"
      :key="`userCard` + index"
      :userInfo = userInfo
    ></user-card>
    <!-- <user-card
      v-for="(userInfo, index) in users"
      :key="`userCard` + index"
      :userInfo = userInfo
    ></user-card> -->
  </v-list>

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
import _ from 'lodash'
import axios from 'axios'
import InfiniteLoading from 'vue-infinite-loading'

// Vue
import { mapState } from 'vuex'

// Local
import PostCard from '@/components/Cards/PostCard.vue'
import ContentCard from '@/components/Cards/ContentCard.vue'
import UserCard from '@/components/Cards/UserCard.vue'

export default {
  name: 'Search',       
  components: {
    InfiniteLoading,
    PostCard,
    ContentCard,
    UserCard,
  },
  data: function() {
    return {
      queryURL: '',
      searchString: '',
      infinityHandlerRendered: true,
      lastPostCode: 0,
      lastContentCode: 0,
      posts: [],
      contents: [],
      users: [],
      searchType: 'post',
    }
  },
  computed: {
    ...mapState([
      'user',
      'searchModal',
    ]),
  },

  methods: {
    changeType (type) {
      this.searchType = type
    },
    clearSearchResult () {
      this.lastPostCode =  0
      this.lastContentCode =  0,
      this.lastUserCode = 0,
      this.posts= []
      this.contents= []
      this.users= []   
    },
    setQueryUrl () {
      let queryString = `${this.$serverURL}/${this.searchType}/search?search=${this.searchString}`
      
      if (this.searchType === 'post') {
        const size = 8
        queryString += `&uid=${this.user.userCode}`
          + `&lastpostcode=${this.lastPostCode}`
          + `&size=${size}`

      } else if (this.searchType === 'content') {
        const size = 8
        queryString += `&uid=${this.user.userCode}`
          + `&lastcontentcode=${this.lastContentCode}`
          + `&size=${size}`
          + `&keyword=null`
      
      } else if (this.searchType === 'user') {
        const size = 15
        // lastPostCode로 되어있지만 오타가 아닙니다..
        queryString += `&lastpostcode=${this.lastUserCode}`
                      + `&size=${size}` 
                      + `&uid=${this.user.userCode}`
      }
      this.queryURL = queryString
    },
    async infiniteHandler ($state) {
      await this.setQueryUrl()
      console.log('dmdkdkdkdk', this.queryURL)

      axios({
        method: 'get',
        url: this.queryURL,
      })
      .then(res => {
        console.log('도착~~!!', res.data)
        console.log(this.queryURL)
        if (res.data.length !== 0) {
          // 1. 포스트 검색
          if (this.searchType === 'post') {
            this.lastPostCode = _.last(res.data).postCode
            console.log('포코포코포코팡', this.lastPostCode)
            for (let key in res.data) {
              this.posts.push(res.data[key])
            }
          // 2. 컨텐츠 검색
          } else if (this.searchType === 'content') {
            this.lastContentCode = _.last(res.data).contentCode
            for (let key in res.data) {
              this.contents.push(res.data[key])
            }
          } else if (this.searchType === 'user') {
            // 오타같지만 오타 아닙니다..
            console.log(_.last(res.data).userCode)
            this.lastPostCode = _.last(res.data).userCode
            for (let key in res.data) {
              this.users.push(res.data[key])
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
    searchModal: {
      deep: true,
      // immediate: true,
      handler () {
        this.infinityHandlerRendered = true
        if (this.searchModal && this.searchModal.input) {
          this.searchInput = this.searchModal.input
          console.log(this.searchInput)
        }
      }
    },
    searchString: {
      async handler () {
        await this.setQueryUrl()
        this.clearSearchResult()
        this.infiniteHandler()
      }
    },
    searchType: {
      async handler () {
        await this.setQueryUrl()
        await this.clearSearchResult()
        this.infiniteHandler()
      }
    }
    
  },


}
</script>

<style>
</style>