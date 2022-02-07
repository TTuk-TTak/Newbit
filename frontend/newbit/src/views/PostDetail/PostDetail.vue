<template>
  <v-card
    v-if="post"
    class=" pt-2 pb-5"
  >
    <!-- 1. 카드 상단부 -->
    <v-row
      class='pa-4 pb-0 justify-space-between'
    >
      <v-col class="pa-2">
        <v-btn icon>
          <v-icon>mdi-arrow-left</v-icon>
        </v-btn>
        <v-avatar
          size='32'
        >
          <img
            src="https://cdn.vuetifyjs.com/images/john.jpg"
            alt="John"
          >
        </v-avatar>  
        <span class="ml-2">{{ post.userCode }}</span>
        <span class="ml-2">{{ this.$createdAt(this.post.postDate) }}</span>
        <span v-if="post.postEdit"> (수정됨)</span>  
      </v-col>
      <v-col class="pa-2" cols=1>
        <!-- 1-1. 카드 상단부-팝업메뉴 -->
        <v-menu
          v-if="isLoggedIn === true"
          left
          bottom
        >
          <template v-slot:activator="{ on, attrs }">
            <v-btn
              icon
              v-bind="attrs"
              v-on="on"
            >
              <v-icon>mdi-dots-horizontal</v-icon>
            </v-btn>
          </template>
          <v-list>
            <!-- 1) 수정 -->
            <v-list-item>
              <v-icon>mdi-cog</v-icon>
              <v-list-item-content class="ml-2 mr-1">
                <v-list-item-subtitle>게시글 수정</v-list-item-subtitle>
              </v-list-item-content>
            </v-list-item>
            <!-- 2) 삭제 -->
            <v-list-item>
              <v-icon>mdi-delete</v-icon>
              <v-list-item-content class="ml-2 mr-1">
                <v-list-item-subtitle>게시글 삭제</v-list-item-subtitle>
              </v-list-item-content>
            </v-list-item>
          </v-list>
        </v-menu>
      </v-col>
    </v-row>
    <!-- 2-1. (임베드 된 경우) 임베드된 컨텐츠 -->
    <embedded-content-card
      v-if="post.contentCode"
      :content='content'
      class="mt-5 mx-3"
    ></embedded-content-card>
    <!-- 2-2. 본문 -->
    <v-card-text
      class="mb-0 pb-0"
    >
      {{ post.postText }}
    </v-card-text>
    <!-- 3. 카드 하단부 -->
    <v-row
      class="container justify-between mt-1 py-0"
    >
      <!-- 3-1. 카드 조작 -->
      <v-col class="py-1">
        <v-card-actions
          class="ml-2 "
        >
          <v-btn icon>
            <v-icon>mdi-cards-heart-outline</v-icon>
            <span>{{ post.postLike }}</span>
          </v-btn>
          <v-btn icon>
            <v-icon>mdi-message-outline</v-icon>
            <span>{{ post.postComment }}</span>
          </v-btn>
          <v-btn 
            icon
            @click="copyLink()"
            >
            <v-icon>mdi-share</v-icon>
          </v-btn>
        </v-card-actions>
      </v-col>
      <!-- 3-2. 생성 시간 -->
      <v-col class="py-1 ma-0">
        <v-card-text
          class="text-end"
        >{{ post.postDate + (post.postEdit ? '(수정됨)' : '') }}</v-card-text>
      </v-col>
    </v-row>

    <!-- 댓글 작성창 -->
    <v-divider
      class="mx-4"
    ></v-divider>

    <v-card-text
      v-if="isLoggedIn"
      class="pb-0"
    >
      <v-row 
        class="ml-3 my-2">
        <user-profile-icon
          :imgUrl="`https://avatars0.githubusercontent.com/u/9064066?v=4&s=460`"
        ></user-profile-icon>
        <v-textarea 
          class="ml-2 py-0"
          placeholder="댓글을 작성해주세요."
          rows=1
          counter='100'
          maxlength='100'
          no-resize
          auto-grow
          @keydown.enter="writeComment()"
          ></v-textarea>
        <v-btn 
          @click="writeComment()"
          icon
          >
          <v-icon>mdi-pencil</v-icon>
        </v-btn>
      </v-row>
      <v-divider></v-divider>
    </v-card-text>
    <post-detail-comment
      class="ml-2"
      v-for="i in 3"
      :key="i"
    ></post-detail-comment>

    <!-- 작성 완료 팝업 -->
    <v-snackbar
      v-model="snackbar.show"
      :timeout="snackbar.timeout"
    >
      {{ snackbar.message }}
      <template v-slot:action="{ attrs }">
        <v-btn
          color="blue"
          text
          v-bind="attrs"
          @click="snackbar = false"
        >
          Close
        </v-btn>
      </template>
    </v-snackbar>
  </v-card>
</template>

<script>
import axios from 'axios'
import _ from 'lodash'

import EmbeddedContentCard from '@/components/Cards/EmbeddedContentCard.vue'
import PostDetailComment from '@/components/PostDetail/PostDetailComment.vue'
import UserProfileIcon from '@/components/Commons/UserProfileIcon.vue'



export default {
  name: 'PostDetail',
  
  components: {
    EmbeddedContentCard,
    PostDetailComment,
    UserProfileIcon,
  },

  data: () => {
    return {
      isLoggedIn: true,
      post: null,
      content: null,
      snackbar: {
        show: false,
        message: '',
        timeout: '1000'
      },
    }
  },

  methods: {

    getPostDetail () {
      const postId = _.split(this.$route.path, '/')[2]
      
      axios.get(`${this.$serverURL}/post/${postId}`)
        .then(response => {
          this.post = response.data
          console.log('포스트 정보', response.data)

          if (this.post.contentCode) {
            this.getContentDetail(this.post.contentCode)
          }
        })
        .catch((err) => {
          console.log(err)
      })
    },

    getContentDetail (contentCode) {
      axios.get(`${this.$serverURL}/content?cid=${contentCode}`)
        .then(response => {
          this.content = response.data
          console.log('컨텐츠 정보', response.data)
        })
        .catch((err) => {
          console.log(err)
      })
    },
    writeComment () {
      this.snackbar.message = '댓글을 달았습니다.'
      this.snackbar.show = true
    },
    // 02/07 서버 다운으로 테스트 실패. 테스트 필요.
    // https://bestofvue.com/repo/Inndy-vue-clipboard2-vuejs-miscellaneous
    // https://github.com/Inndy/vue-clipboard2
    // $ npm install --save vue-clipboard2
    copyLink () {
      const link = this.$clientURL + this.$route.path
      this.$copyText(link)
      this.snackbar.message = '게시물 주소를 클립보드에 복사했습니다.'
      this.snackbar.show = true
    },

  },
  created () {
    // 생성할 때 Post의 Detail 을 Server에 요청
    this.getPostDetail()
  },
  computed: {

  },
}
</script>

<style>

</style>