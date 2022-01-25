<template>
  <v-container class="fill-height">
    <v-row
      align="center"
      justify="center"
      dense
    >
      <v-col cols="4">
        <v-card
          class="rounded-xl"
          elevation="3"
          height="700"
        >
          <v-row justify="center">
            <v-col cols="10">
              <v-card-title class="d-flex justify-center mb-9">
                <p class="text-h3 font-weight-bold">Newbit</p>
              </v-card-title>
              <v-card-text class="text-center">
                <p class="text-h5 font-weight-bold mb-9">회원가입이 완료되었습니다!</p>
                <Icon
                  icon="noto:party-popper"
                  style="font-size: 120px;"
                  class="mb-9"
                />
                <p class="text-h6 font-weight-bold ma-0 grey--text">환영합니다!</p>
                <p class="text-h6 font-weight-bold mb-15 grey--text">로그인 하고 Newbit의 모든 서비스를 이용하세요.</p>
                <v-btn
                  @click="login(credentials)"
                  class="font-weight-bold mb-6"
                  color="black"
                  dark
                  x-large
                  block
                  rounded
                >로그인</v-btn>
                <v-btn
                  to="/"
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
import { Icon } from '@iconify/vue2';

export default {
  components: {
    Icon,
  },
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