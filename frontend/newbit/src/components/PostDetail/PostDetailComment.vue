<template>
  <v-container class="my-0 py-0">
    <v-row class="ml-2 mt-0 my-1 align-center">
      <user-profile-icon :imgUrl="`https://avatars0.githubusercontent.com/u/9064066?v=4&s=460`"></user-profile-icon>
      <v-col>
        <span>HunHun</span>
        <span class="mx-2">{{ comment.time }}</span>
        <v-btn
          @click="clickReplyBtn()" 
          plain 
          text
          >
            {{ isReplying ? '작성 취소' : '답글 작성'}}
          </v-btn>
        <p class="mb-0">{{ comment.text }}</p>
      </v-col>
    </v-row>
    <!-- 댓글 작성창 -->
    <v-row 
      v-if="isReplying"
      class="ml-5 my-2 px-5">
      <v-icon>mdi-arrow-right-bottom</v-icon>
      <v-textarea 
        v-model="reply.text"
        class="ml-2 py-0"
        placeholder="답글을 작성해주세요."
        rows=1
        counter='100'
        maxlength='100'
        no-resize
        auto-grow
        @keydown.enter="writeReply()"
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
      v-if="showReply"
      class="ml-5 mt-0">
      <post-detail-reply></post-detail-reply>
    </v-row>
    <v-row
      class="mt-0"
      justify='center'
    >
      <v-btn 
        @click="toggleReply()"
        text
        small
        >
        {{ showReply ? '숨기기' : '답글 5개 보기'}}
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
import UserProfileIcon from '@/components/Commons/UserProfileIcon.vue'
import PostDetailReply from '@/components/PostDetail/PostDetailReply.vue'

export default {
  name: 'PostDetailComment',
  components: {
    UserProfileIcon,
    PostDetailReply,
  },
  methods: {
    clickReplyBtn () {
      this.isReplying = !this.isReplying
    },
    toggleReply () {
      this.showReply = !this.showReply
    },
    writeReply () {
      console.log('엔터 누름')
      this.snackbar.show = true
    },
  },
  data: () => {
    return {
      isReplying: false,
      showReply: false,
      comment: {
        userCode: '',
        postCode: '',
        time: '25분 전',
        text: '이 편지는 중국에서 시작되었으며...',
        report: '',
      }, 
      reply: {
        text: '',
      },
      snackbar: {
        show: false,
        message: '답글을 달았습니다.',
        timeout: '1000'
      },
    }
  },


}
</script>

<style>

</style>