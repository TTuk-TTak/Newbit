<template>
  <v-row
    justify="center"
    class="ma-0"
  >
    <v-col cols="10">
      <v-card-title class="d-flex justify-center ">
        <span class="text-h5 font-weight-bold">비밀번호 찾기</span>
      </v-card-title>
      <v-card-text>
        <v-form>
          <v-text-field
            v-model.trim="credentials.username"
            label="이메일"
            name="email"
            type="email"
            solo
            outlined
            rounded
          ></v-text-field>
          <v-text-field
            v-model.trim="credentials.password"
            label="아이디"
            name="userId"
            type="text"
            solo
            outlined
            rounded
          ></v-text-field>
          <div>
            <span class="text-h7 grey--text mr-1">가입하실 때 사용하셨던 이메일과</span>
            <span class="text-h7 grey--text mr-1">현재 아이디를 입력 후 '이메일 발송' 버튼을 눌러주세요.</span>
            <span class="text-h7 grey--text mr-1">이메일로 비밀번호 변경 가능한 링크를 발송해드립니다.</span>
            <span class="text-h7 grey--text mr-1">도움이 필요하실 경우 관리자에게 문의해주세요.</span>
          </div>
          <v-btn
            @click="login(credentials)"
            class="font-weight-bold"
            color="black"
            dark
            x-large
            block
            rounded
          >임시 비밀번호 발송</v-btn>
        </v-form>
      </v-card-text>
    </v-col>
  </v-row>
</template>

<script>
export default {
  name: 'LoginModalFindPassword',
  data: function () {
    return {
      credentials: {
        email: '',
        userId: '',
      },
    }
  },
}
</script>

<style>
</style>