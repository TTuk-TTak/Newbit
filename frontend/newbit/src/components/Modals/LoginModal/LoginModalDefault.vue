<template>
  <v-row
    justify="center"
    class="ma-0"
  >
    <v-col cols="10">
      <v-card-title class="d-flex justify-center ">
        <span class="text-h5 font-weight-bold">로그인하고 더보기</span>
      </v-card-title>
      <v-card-text>
        <v-form>
          <v-text-field
            v-model.trim="credentials.userEmail"
            label="이메일"
            type="text"
            solo
            outlined
            rounded
          ></v-text-field>
          <v-text-field
            v-model.trim="credentials.userPassword"
            label="비밀번호"
            type="password"
            solo
            outlined
            rounded
            @keypress.enter="modalLogin(credentials)"
          ></v-text-field>
          <v-btn
            @click="modalLogin(credentials)"
            class="font-weight-bold"
            color="black"
            dark
            x-large
            block
            rounded
          >로그인</v-btn>
          <v-card-actions class="d-flex justify-center mt-6 pa-0">
            <v-divider></v-divider>
          </v-card-actions>
          <v-card-actions class="d-flex justify-center mt-6 pa-0">
            <span class="text-h7 grey--text mr-1">아직 계정이 없다면?</span>
            <router-link :to="{ name: 'Signup' }">회원가입</router-link>
          </v-card-actions>
        </v-form>
      </v-card-text>
    </v-col>
  </v-row>
</template>

<script>
import axios from 'axios'

export default {
  props: {
    dialog: Boolean
  },
  name: 'LoginModalDefault',
  data: function () {
    return {
      credentials: {
        userEmail: '',
        userPassword: '',
      },
    }
  },
  methods: {
    modalLogin: function (credentials) {
      axios.post(`${this.$serverURL}/user/login`, credentials)
        .then((res) => {
          if (res.data.message === 'success') {
            this.dialog = false
            this.$emit('click-change', this.dialog)
            localStorage.setItem('jwt', res.data['access-token'])
            return res.data.userCode
          }
          else {
            return false
          }
        })
        .then((res) => {
          if (res) {
            localStorage.setItem('user_code', res)
            this.$fetchMyInformation(res)
            this.$fetchFollowRecommendation(res)
            this.$goToSocialFeed()
          }
          else {
            alert('이메일 또는 비밀번호를 다시 입력해주세요')
          }
        })
        .catch((err) => {
          console.log(err)
        })
    }
  },

}
</script>

<style>
</style>