<template>
    <div class="text-center">
    <v-dialog
      v-model="$store.state.modals.postCreateModal"
      max-width="800"
    >
      <v-card
        v-if="user"
        class="rounded-xl py-1"
      >
        <!-- 1. 카드 상단부 -->
        <v-card-title
          class='justify-space-between'
        >
          <div>
            <v-avatar>
              <img
                :src="user.userImg"
                @error="defaultProfile"
              >
            </v-avatar>
            <span class="ml-2">{{ user.userNick }}</span>
          </div>
          <!-- 닫기 버튼 -->
          <v-btn 
            class="align-self-start"
            icon 
            right 
            @click="closeModal()">
            <v-icon>mdi-close</v-icon>
          </v-btn>
        </v-card-title>
        <!--input-box-->
        <v-textarea
          v-model='postText'
          class="px-5"
          placeholder="게시글을 작성해주세요."
          rows='8'
          maxlength='500'
          counter='500'
          no-resize
          flat
          solo
        >
        </v-textarea>
        <v-container>
          <embedded-content-card
            v-if='content'
            :content='content'
            ></embedded-content-card>
        </v-container>
        <v-card-actions
          @click="clickWriteBtn()"
        >
          <btn-dark
            class='grow my-2'
            message='글 작성'
          ></btn-dark>
        </v-card-actions>
      </v-card>
    </v-dialog>
    <!-- 스낵바 팝업 -->
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

  </div>
</template>

<script>
import _ from 'lodash'
import axios from 'axios'

import { mapState } from 'vuex'
import BtnDark from '@/components/Commons/BtnDark.vue'
import EmbeddedContentCard from '@/components/Cards/EmbeddedContentCard.vue'

export default {
  name: 'PostCreateModal',
  components: {
    BtnDark,
    EmbeddedContentCard,
  },
  data: () => {
    return {
      btnSize: 'large',
      postText: '',
      content: null,
      snackbar: {
        show: false,
        message: '',
        timeout: '1000'
      },
    }
  },
  computed: {
    ...mapState([
      'user',
    ]),
    parsedUrl () {
      const regURL = new RegExp("(http|https|ftp|telnet|news|irc)://([-/.a-zA-Z0-9_~#%$?&=:200-377()]+)","gi");
      const url = this.postText.match(regURL)
      if (url) {
        return url.toString()
      }
      return null
    },

  },
  methods: {
    defaultProfile(e) {
      e.target.src = `https://cdn.vuetifyjs.com/images/john.jpg`
    },
    embedPost(contentCode) {
      axios.get(`${this.$serverURL}/content?cid=${contentCode}`)
        .then(response => {
          this.content = response.data
          console.log(response.data)
        })
        .catch((err) => {
          console.log(err)
      })
    },

    clickWriteBtn: function () {
      // 1. 유저가 로그인되었으며 2. 컨텐츠가 있거나 3. 포스트 텍스트가 있을 때.
      if (this.user && this.content || (this.postText && 0 < this.postText.length <= 500)) {
        this.writePost()
      } else {
        this.snackbar.message = '게시물 내용을 입력해주세요.'
        this.snackbar.show = true
      }
    },

    writePost: function () {
      axios({
        method: 'POST',
        // headers: this.$setToken(),
        url: `${this.$serverURL}/post/`,
        data: {
          'userCode': this.user.userCode,
          'postText': this.postText,
          'contentCode': this.content ? this.content.contentCode : 0,
          'techblogCode': this.content ? this.content.techblogCode : 0,
        },
      })
        .then((res) => {
          this.snackbar.message = '게시물을 작성했습니다.'
          this.snackbar.show = true
          console.log(res, '게시물 등록 완료')
          this.resetModal()
          this.$store.dispatch('turnPostCreateModalOFF')
        })
        .catch((err) => {
          console.log(err)
        })
    },
    resetModal: function () {
      this.postText = ''
      this.content = null
    },
    closeModal: function () {
      this.resetModal()
      this.$store.dispatch('turnPostCreateModalOFF')
    }
  },
  watch: {
    parsedUrl: {
      handler () {
        if (this.parsedUrl && this.parsedUrl.includes(this.$clientURL + `/content/`)) {
          let url = _.split(this.parsedUrl, '/')
          let contentCode = url[url.length-1]

          if (!isNaN(+contentCode) && contentCode 
              && (!this.content ||contentCode !== this.content.contentCode )) {
              this.embedPost(contentCode)
            }          
        }
      }
    }
  },
}
</script>

<style>

</style>