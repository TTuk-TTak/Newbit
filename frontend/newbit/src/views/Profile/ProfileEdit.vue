<template>
  <v-card>
    <v-container
      fluid
      class="pa-10"
    >
      <p class="text-h5 font-weight-bold">프로필 수정</p>
      <v-divider></v-divider>
      <v-row
        justify="center"
        class="mt-6"
      >
        <v-col cols="10">
          <v-form>
            <v-row>
              <v-col cols="2">
                <v-img
                  class="v-avatar image"
                  src="https://www.gravatar.com/avatar/default?s=200&r=pg&d=mm"
                />
              </v-col>
              <v-col
                align-self="center"
                cols="10"
              >
                <p class="text-h6 grey--text mb-0">{{ `@${$store.state.user.userId}` }}</p>
                <p class="text-h6 mb-0 font-weight-bold">프로필 사진 바꾸기</p>
              </v-col>
            </v-row>
            <v-row>
              <v-col cols="2">
                <p class="text-h6 font-weight-bold mt-3 text-center">아이디</p>
              </v-col>
              <v-col cols="10">
                <v-text-field
                  v-model.trim="credentials.userId"
                  name="userId"
                  type="text"
                  outlined
                  rounded
                  solo
                ></v-text-field>
              </v-col>
              <v-col cols="2">
                <p class="text-h6 font-weight-bold mt-3 text-center">닉네임</p>
              </v-col>
              <v-col cols="10">
                <v-text-field
                  v-model.trim="credentials.nickname"
                  name="nickname"
                  type="text"
                  outlined
                  rounded
                  solo
                ></v-text-field>
              </v-col>
              <v-col cols="2">
                <p class="text-h6 font-weight-bold mt-3 text-center">비밀번호</p>
              </v-col>
              <v-col cols="10">
                <v-text-field
                  v-model.trim="credentials.password"
                  name="password"
                  type="password"
                  outlined
                  rounded
                  solo
                ></v-text-field>
              </v-col>
              <v-col cols="2">
                <p class="text-h6 font-weight-bold mb-0 text-center">비밀번호</p>
                <p class="text-h6 font-weight-bold mb-0 text-center">확인</p>
              </v-col>
              <v-col cols="10">
                <v-text-field
                  v-model.trim="credentials.passwordConfirmation"
                  name="passwordConfirmation"
                  type="password"
                  outlined
                  rounded
                  solo
                ></v-text-field>
              </v-col>
              <v-col cols="2">
                <p class="text-h6 font-weight-bold mt-3 text-center">소개</p>
              </v-col>
              <v-col cols="10">
                <v-textarea
                  v-model.trim="credentials.introduction"
                  name="introduction"
                  type="text"
                  outlined
                  rounded
                  solo
                ></v-textarea>
              </v-col>
            </v-row>
            <v-row>
              <v-col>
                <first-login-modal></first-login-modal>
              </v-col>
            </v-row>
            <v-row justify="center">
              <v-col cols="6">
                <v-btn
                  @click="save(credentials)"
                  class="font-weight-bold"
                  color="black"
                  dark
                  large
                  rounded
                  block
                >저장</v-btn>
              </v-col>
            </v-row>
          </v-form>
        </v-col>
      </v-row>
    </v-container>
  </v-card>
</template>
<script>
import FirstLoginModal from '@/components/Modals/FirstLoginModal.vue'
export default {
  components: { FirstLoginModal },
  name: 'ProfileEdit',
  data: () => ({
    credentials: {
      userId: '',
      nickname: '',
      password: '',
      passwordConfirmation: '',
      introduction: '',
    },
  })
}
</script>

<style>
</style>