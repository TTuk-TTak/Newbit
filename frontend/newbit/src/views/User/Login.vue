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
              <v-card-title class="d-flex justify-center mt-3 mb-9">
                <span class="text-h3 font-weight-bold">Newbit</span>
                <span class="text-h6 font-weight-bold mt-9 mb-9">로그인 후 다양한 서비스를 이용해보세요.</span>
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
                    @keypress.enter="$login(credentials)"
                  ></v-text-field>
                  <v-card-actions>
                  </v-card-actions>
                  <v-btn
                    @click="$login(credentials)"
                    class="font-weight-bold mt-15"
                    color="black"
                    dark
                    x-large
                    block
                    rounded
                  >로그인</v-btn>
                  <v-card-actions class="d-flex justify-center mt-10 pa-0">
                    <v-divider></v-divider>
                    <!-- <span class="text-h7 font-weight-bold">Or</span> -->
                  </v-card-actions>
                  <v-card-actions class="d-flex justify-center mt-10 pa-0">
                    <span class="text-h7 grey--text mr-1">아직 계정이 없다면?</span>
                    <router-link :to="{ name: 'Signup' }">회원가입</router-link>
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
  name: 'Login',
  data: function () {
    return {
      credentials: {
        userEmail: '',
        userPassword: '',
      },
    }
  },
}
</script>

<style>
</style>