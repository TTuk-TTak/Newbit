<template>
  <v-card
    v-if="post"
    class="pt-1 px-8 pb-5"
  >
    <!-- 1. 카드 상단부 -->
    <v-row
      class='pa-4 pb-0 justify-space-between'
    >
      <v-col class="pa-2">
        <v-btn 
          @click="$goToSocialFeed()"
          icon>
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
        <span class="ml-2 writer">{{ post.userNick }}</span>
        <span class="ml-2 date">{{ this.$createdAt(this.post.postDate) }}</span>
        <span class="ml-2 date" v-if="post.postEdit"> (수정됨)</span>  
      </v-col>
      <v-col class="pa-2" cols=1>
        <!-- 1-1. 카드 상단부-팝업메뉴 -->
        <v-menu
          v-if="user.userCode === post.userCode"
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
            <v-list-item @click="clickEdit()">
              <v-icon>mdi-cog</v-icon>
              <v-list-item-content class="ml-2 mr-1">
                <v-list-item-subtitle>게시글 수정</v-list-item-subtitle>
              </v-list-item-content>
            </v-list-item>
            <!-- 2) 삭제 -->
            <!-- <v-dialog
              v-model="dialog"
              width="500"
            >
              <template v-slot:activator="{ on, attrs }">
                <v-btn
                  color="red lighten-2"
                  dark
                  v-bind="attrs"
                  v-on="on"
                >
                  Click Me
                </v-btn> -->
                <v-list-item @click="clickDelete()">
                  <v-icon>mdi-delete</v-icon>
                  <v-list-item-content class="ml-2 mr-1">
                    <v-list-item-subtitle>게시글 삭제</v-list-item-subtitle>
                  </v-list-item-content>
                </v-list-item>
              <!-- </template>
              
              <delete-warning-modal></delete-warning-modal>

              </v-dialog> -->
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
      class="post-text mb-0 pb-0"
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
          <!-- 1) 좋아요 버튼 -->
          <v-btn 
            @click="toggleLike()"
            icon>
            <v-icon v-if="post.liked === true">mdi-cards-heart</v-icon>
            <v-icon v-else>mdi-cards-heart-outline</v-icon>
          </v-btn>
          <span class='post-btn-nums'>{{ post.postLike }}</span>
          <!-- 2) 댓글 버튼  -->
          <v-btn
            class="ml-2"
            icon>
            <v-icon>mdi-message-outline</v-icon>
          </v-btn>
          <span class="post-btn-nums">{{ post.postComment }}</span>
          <v-btn 
            class="ml-2"
            @click="copyLink()"
            icon
            >
            <v-icon>mdi-share</v-icon>
          </v-btn>
        </v-card-actions>
      </v-col>
      <!-- 3-2. 생성 시간 -->
      <v-col class="py-1 ma-0">
        <v-card-text
          class="text-end date"
        >{{ post.postDate + (post.postEdit ? '(수정됨)' : '') }}</v-card-text>
      </v-col>
    </v-row>

    <!-- 댓글 작성창 -->
    <v-divider
      class="mx-4"
    ></v-divider>

    <v-card-text
      class="pb-0"
    >
      <v-row 
        class="ml-3 my-2">
        <user-profile-icon
          :imgUrl="`https://avatars0.githubusercontent.com/u/9064066?v=4&s=460`"
        ></user-profile-icon>
        <v-textarea
          v-model="commentText"
          class="ml-2 py-0"
          placeholder="댓글을 작성해주세요."
          rows=1
          counter='100'
          maxlength='100'
          no-resize
          auto-grow
          @keydown.enter.prevent="writeComment()"
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
    <div
      v-if='comments'
    >
      <post-detail-comment
        class="ml-2"
        v-for="(comment, index) in comments"
        :key="`comment` + index"
        :comment="comment"
      ></post-detail-comment>
    </div>

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
import { mapState } from 'vuex'
import axios from 'axios'
import _ from 'lodash'

import EmbeddedContentCard from '@/components/Cards/EmbeddedContentCard.vue'
import PostDetailComment from '@/components/PostDetail/PostDetailComment.vue'
import UserProfileIcon from '@/components/Commons/UserProfileIcon.vue'
// import DeleteWarningModal from '@/components/Modals/DeleteWarningModal.vue'

export default {
  name: 'PostDetail',
  
  components: {
    EmbeddedContentCard,
    PostDetailComment,
    UserProfileIcon,
    // DeleteWarningModal,
  },

  data: () => {
    return {
      post: null,
      content: null,
      comments: [],
      isEditing: false,
      snackbar: {
        show: false,
        message: '',
        timeout: '1000'
      },
      commentText: '',
      dialog: false,
    }
  },
  methods: {
    getPostDetail () {
      const userCode = this.$store.state.user ? this.$store.state.user.userCode : 0
      const postId = _.split(this.$route.path, '/')[2]

      axios.get(`${this.$serverURL}/post?uid=${userCode}&pid=${postId}`)
        .then(response => {
          this.post = response.data

          if (this.post.contentCode) {
            this.getContentDetail(this.post.contentCode)
          }
        })
        .catch((err) => {
          console.log(err)
      })
    },
    getComments () {
      const postId = _.split(this.$route.path, '/')[2]
      axios({
        method: 'GET',
        // headers: this.$setToken(),
        url: `${this.$serverURL}/comment?pid=${postId}`,
      })
        .then((res) => {
          const commentObject = {}
          let parentComments = 0
          this.comments.length = 0
          for (let key in res.data) {

            if (res.data[key].commentDepth) {
              let parent = res.data[key].commentParent

              for (let index in commentObject) {
                if (commentObject[index]['commentCode'] === parent){
                  commentObject[index]['replies'].push(res.data[key])
                  break
                }
              }
            } else {
              commentObject[key] = res.data[key]
              commentObject[key]['replies'] = []
              parentComments++
            }            
          }

          for (let i = parentComments; i--; i >= 0) {
            this.comments.push(commentObject[i])
          }
        })
        .catch((err) => {
          console.log(err)
        })
    },
    getContentDetail (contentCode) {
      axios.get(`${this.$serverURL}/content?uid=${this.user.userCode}&cid=${contentCode}`)
        .then(response => {
          this.content = response.data
          // console.log('컨텐츠 정보', response.data)
        })
        .catch((err) => {
          console.log(err)
      })
    },
    clickEdit() {
      this.isEditing = true
    },
    clickDelete () {
      if (this.post.userCode === this.user.userCode) {
        this.deletePost()
      }
    },
    clickWriteComment() {
      if (this.user && 0 < this.commentText <= 100 ) {
        this.writeComment()
      } else {
        this.snackbar.message = '댓글을 작성해주세요.'
        this.snackbar.show = true
      }
    },
    writeComment () {
      const writtenComment = this.commentText
      this.commentText = ''
      this.snackbar.message = '댓글을 달았습니다.'
      this.snackbar.show = true
      console.log(
          {
          'userCode': this.user.userCode,
          'postCode': this.post.postCode,
          'commentText': writtenComment,
          'commentDepth': false,
          'commentParent': 0,
        },
      )
      axios({
        method: 'POST',
        url: `${this.$serverURL}/comment/`,
        data: {
          'userCode': this.user.userCode,
          'postCode': this.post.postCode,
          'commentText': writtenComment,
          'commentDepth': 0,
          'commentParent': 0,
        },
      })
        .then(res => {
          this.getComments()
          console.log(res)
        })
        .catch((err) => {
          console.log(err)
        })

    },
    // 게시물 링크 복사
    copyLink () {
      const link = this.$clientURL + this.$route.path
      this.$copyText(link)
      this.snackbar.message = '게시물 주소를 클립보드에 복사했습니다.'
      this.snackbar.show = true
    },
    writePost: function () {
      axios({
        method: 'POST',
        // headers: this.$setToken(),
        url: `${this.$serverURL}/post/${this.post.postCode}`,
        data: {
          'userCode': this.user.userCode,
          'postText': this.postText,
          'contentCode': this.content ? this.content.contentCode : 0,
          'techblogCode': this.content ? this.content.techblogCode : 0,
        },
      })
        .then((res) => {
          console.log(res, 'success')
        })
        .catch((err) => {
          console.log(err)
        })
    },
    deletePost: function () {
      this.snackbar.message = '게시글을 삭제했습니다.'
      this.snackbar.show = true
      axios({
        method: 'DELETE',
        // headers: this.$setToken(),
        url: `${this.$serverURL}/post/${this.post.postCode}`,
      })
        .then((res) => {
          console.log(res, 'deleteSuccess')
          console.log(this.$goToSocialFeed())
        })
        .catch((err) => {
          console.log(err)
        })
    },
    // 좋아요!!!
    toggleLike() {
      if (!this.post.liked) {
        this.likePost()
      } else {
        this.unlikePost()
      }
    },
    likePost() {      
      axios({
        method: 'POST',
        url: `${this.$serverURL}/post/like`,
        data: {
          'uid': this.user.userCode,
          'pid': this.post.postCode,
        },
      })
      .then((res) => {
        console.log('liked', res)
        if (res.data === 'success') {
          this.post.postLike++
          this.post.liked = !this.post.liked
        }
      })
      .catch((err) => {
        console.log(err)
      })  
    },
    unlikePost() {
      axios({
        method: 'DELETE',
        url: `${this.$serverURL}/post/like?uid=${this.user.userCode}&pid=${this.post.postCode}`,
      })
      .then((res) => {
        console.log('unliked', res)
        if (res.data === 'success') {
          this.post.liked = !this.post.liked
          this.post.postLike--
        }
      })
      .catch((err) => {
        console.log(err)
      })  
    },
  },
  computed: {
    ...mapState([
      'user',
    ]),
  },
  watch: {
    user: {
      deep: true,
      immediate: true,
      handler() {
        if (this.post === null) {
          this.getPostDetail()
        }
      }
    }
  },
  mounted () {
    this.getPostDetail()
    this.getComments()
  },
}
</script>

<style scoped>
.v-list-item{
  background-color: white;
  width:150px; 
}
/* 게시글: 좋아요 및 댓글 갯수 */
.post-btn-nums {
  color : #272727;
  font-family: 'KoPub Dotum';
  font-weight: 100;
  font-size : 0.9em;
}

</style>