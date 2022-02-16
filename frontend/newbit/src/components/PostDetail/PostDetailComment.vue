<template>
  <v-container 
    v-if="comment"
    class="my-0 py-0">
    <v-row class="ml-2 mt-0 my-1 align-center">
      <v-col
        class="d-flex shrink"
      >
        <user-profile-icon :imgUrl="comment.userImg"></user-profile-icon>
      </v-col>
      <v-col>
        <v-row>
          <v-col>
            <span class="writer">{{ comment.userNick }}</span>
            <span class="date mx-2">@{{ comment.userId }}</span>
            <span class="date mx-2">·{{ $createdAt(comment.commentDate ) }}</span>
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

            <!-- 삭제 버튼 및 모달 창 -->
            <template>
              <div class="text-center">
                <v-dialog
                  v-model="dialog"
                  width="300"
                >
                  <template v-slot:activator="{ on, attrs }">
                    <v-btn
                      class="pb-2"
                      v-if="user.userCode === comment.userCode"
                      icon

                      v-bind='attrs'
                      v-on='on'
                    >
                      <v-icon small>mdi-trash-can-outline</v-icon>
                    </v-btn>
                  </template>
                  <!-- 모달 카드 본문 -->
                  <v-card>
                    <v-card-title>
                    </v-card-title>
                    <v-card-text class="text-center pb-1">
                      <p>
                        댓글을 삭제하시면 복구할 수 없습니다.
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
                        @click="deleteComment()"
                      >
                        네
                      </v-btn>
                    </v-card-actions>
                  </v-card>
                </v-dialog>
              </div>
            </template>
          </v-col>
        </v-row>
        <!-- 댓글 본문 -->
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
      dialog: false,
      isDeleted: false
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
          const snackbarText = '답글을 작성했습니다.'
          this.$store.dispatch('turnSnackBarOn', snackbarText)
          this.$emit('reply-added', this.comment.commentCode)
          this.replyText = ''
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
          const snackbarText = '댓글을 삭제했습니다.'
          this.$store.dispatch('turnSnackBarOn', snackbarText)
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