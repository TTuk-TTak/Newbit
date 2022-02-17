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
              <v-card-title class="d-flex justify-center mt-6 mb-15">
                <p class="text-h3 font-weight-bold">Newbit</p>
              </v-card-title>
              <v-card-text class="text-center">
                <p class="text-h5 font-weight-bold mb-12">회원가입이 완료되었습니다!</p>
                <v-icon
                  class="mb-12"
                  x-large
                  color="orange"
                >mdi-party-popper
                </v-icon>
                <div class="text-h6 font-weight-bold">환영합니다!</div>
                <div class="text-h6 font-weight-bold mb-15">로그인 하고 Newbit의 모든 서비스를 이용하세요.</div>
                <v-card-actions>
                </v-card-actions>
                <v-btn
                  @click="$goToLoginPage()"
                  class="font-weight-bold mb-6"
                  color="black"
                  dark
                  x-large
                  block
                  rounded
                >로그인</v-btn>
                <v-btn
                  @click="$goToSocialFeed()"
                  class="font-weight-bold"
                  color="white"
                  x-large
                  block
                  rounded
                >메인 페이지로</v-btn>
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
        username: '',
        password: '',
      },
    }
  },
}
</script>

<style>
</style>