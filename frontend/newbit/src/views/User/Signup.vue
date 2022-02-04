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
              </v-card-title>
              <v-card-text>
                <v-form>
                  <v-text-field
                    v-model.trim="credentials.nickname"
                    label="닉네임"
                    name="nickname"
                    type="text"
                    solo
                    outlined
                    rounded
                  ></v-text-field>
                  <v-text-field
                    v-model.trim="credentials.userId"
                    label="아이디"
                    name="userId"
                    type="text"
                    solo
                    outlined
                    rounded
                  ></v-text-field>
                  <v-text-field
                    v-model.trim="credentials.email"
                    label="이메일"
                    name="email"
                    type="email"
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
                  <v-text-field
                    v-model.trim="credentials.passwordConfirmation"
                    label="비밀번호 확인"
                    name="passwordConfirmation"
                    type="password"
                    solo
                    outlined
                    rounded
                    @keypress.enter="signup(credentials)"
                  ></v-text-field>
                  <v-checkbox
                    class="mt-0 ml-3 pt-0"
                    label="이용약관 동의"
                  ></v-checkbox>
                  <v-btn
                    @click="signup(credentials)"
                    class="font-weight-bold"
                    color="black"
                    dark
                    x-large
                    block
                    rounded
                  >가입하기</v-btn>
                  <v-card-actions class="d-flex justify-center mt-6 pa-0">
                    <span class="text-h7 grey--text mr-1">이미 계정이 있으신가요?</span>
                    <router-link :to="{ name: 'Login' }">로그인 하기</router-link>
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
export default {
  name: 'Signup',
  data: function () {
    return {
      credentials: {
        nickname: '',
        userId: '',
        email: '',
        password: '',
        passwordConfirmation: '',
      },
    }
  },
}
</script>

<style>
</style>