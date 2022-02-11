<template>
  <v-container 
    v-if="comment"
    class="my-0 py-0">
    <v-row class="ml-2 mt-0 my-1 align-center">
      <v-col
        class="d-flex shrink"
      >
        <user-profile-icon :imgUrl="`https://avatars0.githubusercontent.com/u/9064066?v=4&s=460`"></user-profile-icon>
      </v-col>
      <v-col>
        <v-row>
          <v-col>
            <span class="writer">{{ comment.userCode }}</span>
            <span class="date mx-2">{{ $createdAt(comment.commentDate ) }}</span>
          </v-col>
          <v-col
            class='d-flex shrink'
          >
            <v-btn
              class="d-flex shrink text-align-center"
              @click="clickReplyBtn()" 
              plain 
              text
              small
              >
                {{ isReplying ? '작성 취소' : '답글 작성'}}
            </v-btn>
            <v-btn
              v-if="user.userCode === comment.userCode"
              icon
              @click="deleteComment()"
            >
              <v-icon small>mdi-trash-can-outline</v-icon>
            </v-btn>
          </v-col>
        </v-row>

        <p class="comment-text mb-0">{{ comment.commentText }}</p>
      </v-col>
    </v-row>
    <!-- 댓글 작성창 -->
    <v-row 
      v-if="isReplying"
      class="ml-5 my-2 px-5">
      <v-icon>mdi-arrow-right-bottom</v-icon>
      <v-textarea 
        v-model="replyText"
        class="ml-2 py-0"
        placeholder="답글을 작성해주세요."
        rows=1
        counter='100'
        maxlength='100'
        no-resize
        auto-grow
        @keydown.enter.prevent="writeReply()"
        ></v-textarea>
      <v-btn 
        @click="writeReply()"
        icon
        >
        <v-icon>mdi-pencil</v-icon>
      </v-btn>
    </v-row>
    <!-- 답글 -->
    <v-row 
      v-if="showReplies && comment.replies"
      class="ml-5 mt-0"
      >
      <post-detail-reply
        v-for="(reply, index) in replies"
        :key="`reply` + index"
        :reply='reply'
      ></post-detail-reply>
    </v-row>
    <v-row
      class="mt-0"
      justify='center'
    >
      <v-btn
        v-if="replies && replies.length"
        @click="toggleReply()"
        text
        small
        >
        {{ showReplies ? '숨기기' : `답글 ${replies.length}개 보기`}}
      </v-btn>
    </v-row>
    <v-divider class="mt-2"></v-divider>

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



  </v-container>
</template>

<script>
import { mapState } from 'vuex'
import axios from 'axios'
import UserProfileIcon from '@/components/Commons/UserProfileIcon.vue'
import PostDetailReply from '@/components/PostDetail/PostDetailReply.vue'

export default {
  name: 'PostDetailComment',
  props: {
    comment: Object,
  },
  components: {
    UserProfileIcon,
    PostDetailReply,
  },
  data: () => {
    return {
      replies: [],
      showReplies: false,
      // 작성중인 답글 텍스트
      // 답글창 토글 여부
      isReplying: false,
      replyText: '',
      snackbar: {
        show: false,
        message: '',
        timeout: '1000'
      },
      dialog: false,
    }
  },
  computed: {
    ...mapState([
        "user",
    ]),
  },
  methods: {
    clickReplyBtn () {
      this.isReplying = !this.isReplying
    },
    toggleReply () {
      this.showReplies = !this.showReplies
    },
    writeReply () {
      const writtenComment = this.replyText
      this.replyText = ''
      this.snackbar.message = '답글을 달았습니다.'
      this.snackbar.show = true
      axios({
        method: 'POST',
        url: `${this.$serverURL}/comment/`,
        data: {
          'userCode': this.user.userCode,
          'postCode': this.comment.postCode,
          'commentText': writtenComment,
          'commentDepth': true,
          'commentParent': this.comment.commentCode,
        },
      })
        .then(res => {
          this.replies.unshift({
            'userCode': this.user.userCode,
            'userNick': this.user.userNick, 
            'postCode': 0,
            'commentText': writtenComment,
            'commentDepth': true,
            'commentParent': this.comment.commentCode,
            'commentDate': Date.now()
          })
          this.commentText = ''
          this.showReplies = true
          console.log(res)
        })
        .catch((err) => {
          console.log(err)
        })
    },
    clickDeleteBtn () {
      console.log(this.dialog)
      this.dialog = true
      console.log(this.dialog)
      console.log(1221323)
      setTimeout(this.dialog = false, 1000)
    },
    deleteComment() {
      axios({
        method: 'DELETE',
        url: `${this.$serverURL}/comment/${this.comment.commentCode}`,
      })
        .then(res => {
          this.snackbar.message = '댓글을 삭제했습니다.'
          this.snackbar.show = true
          console.log(res)
          this.comment = null
        })
        .catch((err) => {
          console.log(err)
        })
    },
  },
  mounted () {
    console.log(this.comment.replies)
    const replies = this.comment.replies.length
    if ( replies ) {
      for (let i = replies-1; i >= 0; i--) {
        this.replies.push(this.comment.replies[i])
      }
    }
  }
}
</script>

<style scoped>

.writer{
  font-size : 1.1em;
}

/* 본문 글씨체 */
.comment-text {
  font-family: 'KoPub Dotum';
  font-weight: 400;
  color : #272727;
}


</style>