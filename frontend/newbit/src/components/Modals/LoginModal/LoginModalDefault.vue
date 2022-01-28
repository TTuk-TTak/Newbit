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
            <v-row
              align="center"
              justify="center"
            >
              <v-col cols="4">
                <v-btn
                  large
                  rounded
                  block
                  color="white"
                >Google</v-btn>
              </v-col>
              <v-col cols="4">
                <v-btn
                  large
                  rounded
                  block
                  color="blue"
                  dark
                >Facebook</v-btn>
              </v-col>
              <v-col cols="4">
                <v-btn
                  large
                  rounded
                  block
                  color="green"
                  dark
                >Naver</v-btn>
              </v-col>
            </v-row>
          </v-card-actions>
          <v-card-actions class="d-flex justify-center mt-6 pa-0">
            <v-divider></v-divider>
          </v-card-actions>
          <v-card-actions class="d-flex justify-center mt-6 pa-0">
            <span class="text-h7 grey--text mr-1">아직 계정이 없다면?</span>
            <router-link :to="{ name: 'Login' }">회원가입</router-link>
          </v-card-actions>
        </v-form>
      </v-card-text>
    </v-col>
  </v-row>
</template>

<script>
export default {
  name: 'LoginModalDefault',
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