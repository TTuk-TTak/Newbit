<template>
  <v-container 
    v-if="reply"
    class="py-0">
        <v-divider></v-divider>
    <v-row class="ml-2 mt-0 my-1 align-center">
      <v-col
        class="d-flex shrink"
      >
        <user-profile-icon :imgUrl="reply.userImg"></user-profile-icon>
      </v-col>
      <v-col>
        <v-row>
          <v-col>
            <span class="writer">{{ reply.userNick }}</span>
            <span class="date ml-2">@{{ reply.userId }}</span>
            <span class="date mx-2">·{{ $createdAt(reply.commentDate ) }}</span>
          </v-col>
          <v-col
            class='d-flex shrink'
          >
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
                      v-if="user.userCode === reply.userCode"
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
                        덧글을 삭제하시면 복구할 수 없습니다.
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
            <!-- <v-btn
              v-if="user.userCode === reply.userCode"
              icon
              @click="deleteComment()"
            >
              <v-icon small>mdi-trash-can-outline</v-icon>
            </v-btn> -->
          </v-col>
        </v-row>
        <p class="comment-text mb-0">{{ reply.commentText }}</p>
      </v-col>
    </v-row>
  </v-container>

</template>

<script>
import axios from 'axios'
import UserProfileIcon from '@/components/Commons/UserProfileIcon.vue'
import { mapState } from 'vuex'

export default {
  name: 'PostDetailReply',
  props: {
    reply: Object,
  },
  components: {
    UserProfileIcon,
  },
  data: () => {
    return {
      dialog: false,
    }
  },
  computed: {
    ...mapState([
      'user',
    ])
  },
  methods: {
    
    deleteComment() {
      console.log(1111)
      axios({
        method: 'DELETE',
        url: `${this.$serverURL}/comment/${this.reply.commentCode}`,
      })
        .then(res => {
          const snackbarText = '답글을 삭제했습니다.'
          this.$store.dispatch('turnSnackBarOn', snackbarText)
          console.log(res)
          this.reply = null
        })
        .catch((err) => {
          console.log(err)
        })
    },

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