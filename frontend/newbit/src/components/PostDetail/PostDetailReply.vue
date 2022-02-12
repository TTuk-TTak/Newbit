<template>
  <v-container 
    v-if="reply"
    class="py-0">
    <v-row class="ml-2 mt-0 my-1 align-center">
      <!-- Comment에 userImg 오게 되면 아랫걸로 갱신.  -->
      <!-- <user-profile-icon :imgUrl="reply.userImg"></user-profile-icon> -->
      <v-col
        class="d-flex shrink"
      >
        <user-profile-icon :imgUrl="`https://avatars0.githubusercontent.com/u/9064066?v=4&s=460`"></user-profile-icon>
      </v-col>
      <v-col>
        <v-row>
          <v-col>
            <span class="writer">{{ reply.userCode }}</span>
            <span class="date mx-2">{{ $createdAt(reply.commentDate ) }}</span>
          </v-col>
          <v-col
            class='d-flex shrink'
          >
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
          this.snackbar.message = '답글을 삭제했습니다.'
          this.snackbar.show = true
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