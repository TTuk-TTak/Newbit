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
      >잘못된 접근입니다.</v-card-title>
      <!-- 3. 버튼 -->
      <v-row
        class="justify-center mt-5"
      >
        <btn-dark 
          class="justify-center"
          :message="`메인으로 돌아가기`">
        </btn-dark>
      </v-row>
      <v-card-text
        class="text-center"
      >3초 뒤에 자동으로 이동합니다.</v-card-text>
    </v-card>
  </v-container>
</template>

<script>

import BtnDark from '@/components/Commons/BtnDark.vue'

export default {
  name: 'Exceptions',
  components: {
    BtnDark,
  }

}
</script>

<style>

</style>