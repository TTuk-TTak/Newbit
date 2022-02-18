<template>
  <v-card>
    <v-container
      fluid
      class="pa-10"
    >
      <p class="text-h5 font-weight-bold">프로필 수정</p>
      <v-divider></v-divider>
      <v-row
        justify="center"
        class="mt-6 textsize"
      >
        <v-col cols="10">
          <v-form>
            <v-row>
              <v-col cols="2">
                <v-img
                  class="v-avatar image"
                  :src="user.userImg"
                />
              </v-col>
              <v-col
                align-self="center"
                cols="10"
              >
                <span class="text-h6 font-weight-bold mr-1">{{ user.userNick }}</span>
                <span class="grey--text">{{ `@${user.userId}` }}</span>
              </v-col>
            </v-row>
            <v-row>
              <v-col
                class="py-0"
                cols="2"
              >
                <p class="font-weight-bold mt-3 text-center">아이디</p>
              </v-col>
              <v-col
                class="py-0"
                cols="10"
              >
                <v-text-field
                  v-model.trim="credentials.userId"
                  type="text"
                  outlined
                  rounded
                  solo
                ></v-text-field>
              </v-col>
              <v-col
                cols="2"
                class="py-0"
              >
                <p class="font-weight-bold mt-3 text-center">닉네임</p>
              </v-col>
              <v-col
                class="py-0"
                cols="10"
              >
                <v-text-field
                  v-model.trim="credentials.userNick"
                  type="text"
                  outlined
                  rounded
                  solo
                ></v-text-field>
              </v-col>
              <v-col
                class="py-0"
                cols="2"
              >
                <p class="font-weight-bold mb-0 text-center">비밀번호</p>
                <p class="font-weight-bold mb-0 text-center">변경</p>
              </v-col>
              <v-col
                class="py-0"
                cols="10"
              >
                <v-text-field
                  v-model.trim="credentials.userPassword"
                  type="password"
                  outlined
                  rounded
                  solo
                ></v-text-field>
              </v-col>
              <v-col
                class="py-0"
                cols="2"
              >
                <p class="font-weight-bold mb-0 text-center">비밀번호</p>
                <p class="font-weight-bold mb-0 text-center">변경확인</p>
              </v-col>
              <v-col
                class="py-0"
                cols="10"
              >
                <v-text-field
                  v-model.trim="rePassword"
                  type="password"
                  outlined
                  rounded
                  solo
                ></v-text-field>
              </v-col>
              <v-col
                class="py-0"
                cols="2"
              >
                <p class="font-weight-bold mt-15 text-center">소개</p>
              </v-col>
              <v-col
                class="py-0"
                cols="10"
              >
                <v-textarea
                  v-model.trim="credentials.userIntro"
                  type="text"
                  outlined
                  rounded
                  solo
                ></v-textarea>
              </v-col>
            </v-row>
            <div class="mb-7">
              <div class="font-weight-bold mb-3">관심키워드</div>
              <keyword-toggler @query-string-changed="changeKeywordString"></keyword-toggler>
            </div>
            <v-row justify="center">
              <v-col cols="6">
                <v-btn
                  @click="changeMyInformation(user.userCode, user.userImg)"
                  class="font-weight-bold"
                  color="black"
                  dark
                  large
                  rounded
                  block
                >저장</v-btn>
              </v-col>
            </v-row>
          </v-form>
        </v-col>
      </v-row>
    </v-container>
  </v-card>
</template>
<script>
import axios from 'axios'
import KeywordToggler from '@/components/Keyword/KeywordToggler.vue'

const myUserCode = localStorage.getItem('user_code')

export default {
  components: { KeywordToggler },
  name: 'ProfileEdit',
  data: function () {
    return {
      user: {},
      credentials: {
        userId: '',
        userNick: '',
        userPassword: '',
        userIntro: '',
        userKeyword: '',
      },
      rePassword: '',
    }
  },
  methods: {
    // Vuex 사용해서 연결하면 새로고침하면 초기화되서 빈화면 뜸
    fetchMyInformation (user_code) {
      axios({
        url: `${this.$serverURL}/user?uid=${user_code}`,
        method: 'get',
      })
        .then((res) => {
          this.user = res.data
          this.credentials.userId = res.data.userId
          this.credentials.userNick = res.data.userNick
          this.credentials.userIntro = res.data.userIntro
        })
        .catch((err) => {
          console.log(err)
        })
    },
    changeMyInformation (user_code, user_img) {
      const headers = this.$setToken()
      const data = { ...this.credentials, userCode: user_code, userImg: user_img }
      axios({
        url: `${this.$serverURL}/user`,
        method: 'patch',
        data,
        headers,
      })
        .then((res) => {
          console.log(res)
        })
    },
    changeKeywordString (changed) {
      this.userKeyword = changed
    }
  },
  created () {
    this.fetchMyInformation(myUserCode)
  }
}
</script>

<style scoped>
.textsize {
  font-size: 1.2em;
}
</style>
