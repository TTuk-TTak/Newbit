<template>
  <v-container
    class='justify-center'
  >
    <v-card
      class="container justify-center"
    >
      <!-- 1. 경고 아이콘 -->
      <v-card-text
        class='text-center'
      >
        <v-icon size='250'>mdi-alert-outline</v-icon>
      </v-card-text>
      <!-- 2. 경고 문구 -->
      <v-card-title
        class="justify-center py-2"
      >혹시 다른 글이지 않을까요?</v-card-title>
      <!-- 3. 경고 메인 -->
      <v-card-text
        class="text-center"
      >잘못된 주소이거나, 비공개, 혹은 삭제된 글입니다.</v-card-text>
      <!-- 4. 버튼 -->
      <v-row
        class="justify-center mt-5"
      >
        <btn-dark 
          class="justify-center"
          @click="backToMain()"
          :message="`메인으로 돌아가기`">
        </btn-dark>
      </v-row>
      <v-card-text
        class="text-center"
      >{{ timeLeft }}초 뒤에 자동으로 이동합니다.</v-card-text>
    </v-card>
  </v-container>
</template>
<script>

import BtnDark from '@/components/Commons/BtnDark.vue'

export default {
  name: 'NotFound',
  components: {
    BtnDark,
  },
  data: function () {
    return {
      isLoggedIn: true,
      timeLeft: 3,
    }
  },
  methods: {
    backToMain: function () {
      this.$goToSocialFeed()
    },
    countDown: function () {
      if (this.timeLeft) {
        this.timeLeft = this.timeLeft - 1 
      }
    }
  },
  created () {
    setTimeout(this.backToMain, 3000)
  },

}
</script>

<style>


</style>