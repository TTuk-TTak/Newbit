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
            :src="post.userImg"
          >
        </v-avatar>  
        <span class="ml-2 writer">{{ post.userNick }}</span>
        <span class="ml-2 date">@{{ post.userId }}</span>
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
            <v-list-item @click="toggleEdit()">
              <v-icon>mdi-cog</v-icon>
              <v-list-item-content class="ml-2 mr-1">
                <v-list-item-subtitle>
                  {{ isEditing ? '수정 취소' : '게시글 수정'}}
                </v-list-item-subtitle>
              </v-list-item-content>
            </v-list-item>
            <!-- 2) 삭제 -->
                <v-list-item @click.stop="dialog=true">
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
    <!-- 1) 텍스트 -->
    <v-card-text
      v-if='!isEditing'
      class="post-text mb-0 pb-0"
    >
      {{ post.postText }}
    </v-card-text>
    <!-- 2) 텍스트 수정 시 input창 -->
    <v-textarea
      v-if="isEditing"
      @keyup.esc='toggleEdit()'
      :autofocus='true'
      v-model='postEditText'
      class="post-edit-text mx-3 pa-0 mt-4"
      placeholder="게시글을 작성해주세요."
      rows='8'
      maxlength='500'
      counter='500'
      no-resize
      solo
    >
    </v-textarea>
    <!-- 3) '게시글 수정' 버튼 -->
    <v-row 
      v-if="isEditing"
      class="pt-2 mx-3" 
      justify='end'>
      <v-btn
        class="mr-2"
        outlined
        @click="toggleEdit()"
      >
        수정 취소
      </v-btn>
      <v-btn
        depressed
        class="btnDarkBackground btnDarkText--text"
        style="font-weight: 100"
        @click="editPost()"
      >
        수정 완료
      </v-btn>

    </v-row>
    <div class="d-flex grow">
    </div>
    <!-- 3. 카드 하단부 -->
    <v-row
      class="container justify-between mt-1 py-0 mr-0 pr-0"
    >
      <!-- 3-1. 카드 조작 -->
      <v-col class="py-1 px-0">
        <v-card-actions
          class="ml-2 px-0"
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
          class="text-end date pr-0"
        >{{ post.postDate + (post.postEdit ? '  (수정됨)' : '') }}</v-card-text>
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
          :imgUrl="user.userImg"
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
        @reply-added="getComments"
      ></post-detail-comment>
    </div>

  <!-- 삭제 경고 모달 -->
    <div class="text-center">
      <v-dialog
        v-model="dialog"
        width="300"
      >
        <!-- 모달 카드 본문 -->
        <v-card>
          <v-card-title>
          </v-card-title>
          <v-card-text class="text-center pb-1">
            <p>
              게시글을 삭제하시면 복구할 수 없습니다.
              <br>
              삭제하시겠습니까?
              
            </p>
          </v-card-text>
          <v-divider></v-divider>
          <v-card-actions>
            <v-btn
              text
              small
              @click="dialog=false"
            >
              아니오
            </v-btn>
            <v-spacer></v-spacer>
            <v-btn
              color="primary"
              text
              small
              @click="clickDelete()"
            >
              네
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </div>
  </v-card>
</template>

<script>
import { mapState } from 'vuex'
import axios from 'axios'
import _ from 'lodash'

import EmbeddedContentCard from '@/components/Cards/EmbeddedContentCard.vue'
import PostDetailComment from '@/components/PostDetail/PostDetailComment.vue'
import UserProfileIcon from '@/components/Commons/UserProfileIcon.vue'
// import BtnDark from '@/components/Commons/BtnDark.vue'
// import DeleteWarningModal from '@/components/Modals/DeleteWarningModal.vue'

export default {
  name: 'PostDetail',
  
  components: {
    EmbeddedContentCard,
    PostDetailComment,
    UserProfileIcon,
    // BtnDark,
    // DeleteWarningModal,
  },
  data: () => {
    return {
      post: null,
      content: null,
      comments: [],
      isEditing: false,
      postEditText: '',
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
          this.postEditText = this.post.postText
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
      console.log(12313123)
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
        })
        .catch((err) => {
          console.log(err)
      })
    },
    toggleEdit () {
      this.isEditing = !this.isEditing
      console.log('isEditing', this.isEditing)
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
        const snackbarText = '댓글을 작성해주세요.'
        this.$store.dispatch('turnSnackBarOn', snackbarText)
      }
    },
    writeComment () {
      const writtenComment = this.commentText
      console.log(
          {
          'userCode': this.user.userCode,
          'postCode': this.post.postCode,
          'commentText': writtenComment,
          'commentDepth': 0,
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
          this.commentText = ''
          const snackbarText = '댓글을 작성했습니다.'
          this.$store.dispatch('turnSnackBarOn', snackbarText)
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
      const snackbarText = '게시물 주소를 클립보드에 복사했습니다.'
      this.$store.dispatch('turnSnackBarOn', snackbarText)

    },

    editPost: function () {
      axios({
        method: 'PATCH',
        // headers: this.$setToken(),
        url: `${this.$serverURL}/post`,
        data: {
          "postCode": this.post.postCode,
          "postText": this.postEditText
        },
      })
        .then((res) => {
          console.log(res, 'editSuccess')
          this.post.postText = this.postEditText
          this.toggleEdit()
          const snackbarText = '게시글을 수정했습니다.'
          this.$store.dispatch('turnSnackBarOn', snackbarText)
        })
        .catch((err) => {
          const snackbarText = '일시적 오류로 게시글을 수정하지 못했습니다.\n 문제가 계속될 경우 관리자에게 문의하세요.'
          this.$store.dispatch('turnSnackBarOn', snackbarText)
          console.log(err)
        })
    },


    deletePost: function () {
      axios({
        method: 'DELETE',
        // headers: this.$setToken(),
        url: `${this.$serverURL}/post/${this.post.postCode}`,
      })
        .then((res) => {
          const snackbarText = '게시글을 삭제했습니다.'
          this.$store.dispatch('turnSnackBarOn', snackbarText)
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

.post-edit-text {
  font-family: 'KoPub Dotum';
  font-weight: 400;
  color : #272727;
    font-size : 1em;
}




</style>