<template>
  <v-container class="fill-height">
    <v-row
      align="center"
      justify="center"
      dense
    >
      <v-col cols="5">
        <v-card
          class="rounded-xl"
          elevation="3"
          height="700"
        >
          <v-row justify="center">
            <v-col cols="10">
              <v-card-title class="d-flex justify-center mb-3">
                <span class="text-h3 font-weight-bold">Newbit</span>
                <span class="text-h6 font-weight-bold mt-6 mb-15">로그인 후 다양한 서비스를 이용해보세요.</span>
              </v-card-title>
              <v-card-text>
                <v-form>
                  <v-text-field
                    v-model.trim="credentials.username"
                    label="아이디 or 이메일"
                    name="username"
                    type="text"
                    solo
                    outlined
                    rounded
                  ></v-text-field>
                  <v-text-field
                    v-model.trim="credentials.password"
                    label="비밀번호"
                    name="password"
                    type="password"
                    solo
                    outlined
                    rounded
                  ></v-text-field>
                  <v-checkbox
                    class="mt-0 ml-3 pt-0"
                    label="내 정보 기억하기"
                  ></v-checkbox>
                  <v-btn
                    @click="login(credentials)"
                    class="font-weight-bold"
                    color="black"
                    dark
                    x-large
                    block
                    rounded
                  >로그인</v-btn>
                  <v-card-actions class="mt-6">
                    <social-login></social-login>
                  </v-card-actions>
                  <v-card-actions class="d-flex justify-center mt-6 pa-0">
                    <v-divider></v-divider>
                    <!-- <span class="text-h7 font-weight-bold">Or</span> -->
                  </v-card-actions>
                  <v-card-actions class="d-flex justify-center mt-6 pa-0">
                    <span class="text-h7 grey--text mr-1">아직 계정이 없다면?</span>
                    <router-link :to="{ name: 'Login' }">회원가입</router-link>
                  </v-card-actions>
                </v-form>
              </v-card-text>
            </v-col>
          </v-row>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import SocialLogin from '@/components/Commons/SocialLogin.vue'
export default {
  components: { SocialLogin },
  name: 'Login',
  data: function () {
    return {
      credentials: {
        username: '',
        password: '',
      },
    }
  },
}
</script>

<style>
</style>