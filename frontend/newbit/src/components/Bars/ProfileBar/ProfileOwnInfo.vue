<template>
  <div v-if="isLoggedIn === true">
    <v-row>
      <v-col
        cols="7"
        align-self="center"
      >
        <v-img
          class="v-avatar image"
          src="https://www.gravatar.com/avatar/default?s=200&r=pg&d=mm"
        />
      </v-col>
    </v-row>
    <v-row>
      <v-col cols="6">
        <h1>트루</h1>
      </v-col>
      <v-col
        cols="6"
        align-self="center"
      >
        <v-btn
          block
          rounded
          small
        >
          hello
        </v-btn>
      </v-col>
    </v-row>
    <div class="d-flex justify-space-between">
      <span>게시물</span>
      <span>팔로워</span>
      <span>팔로잉</span>
    </div>
    <v-btn
      rounded
      block
      color="black"
      dark
      class="font-weight-bold"
    >
      글작성
    </v-btn>
  </div>
  <div
    v-else
    style="margin-bottom:30px"
  >
    <v-row>
      <v-col
        cols="7"
        align-self="center"
      >
        <v-avatar
          size="150"
          class="mt-5 ml-3"
        >
          <v-img
            class="v-avatar image"
            src="https://www.gravatar.com/avatar/default?s=200&r=pg&d=mm"
          />
        </v-avatar>

      </v-col>
    </v-row>
    <div class="mt-5 mb-5 ml-3">
      로그인이 필요합니다. <br />
      로그인 후 SNS 기능을 이용해보세요. <br />
      아직 회원이 아니시라면, 회원가입 후 아래의 서비스들과 함께하세요! <br /><br />
      <!-- ToDo : 숫자 부분은 후에 {{}}로 수정 -->
      총게시물 365개 | 총회원수 5,041명 | 컨텐츠 3,855개 <br />
    </div>
    <v-btn
      rounded
      block
      outlined
      large
      color="#0d0e23"
      class="font-weight-bold mb-3"
      style="font-size: 1.15em; font-weight: 500;"
      @click="$goToLoginPage()"
    >
      로그인하기
    </v-btn>
    <v-btn
      rounded
      block
      depressed
      large
      color="#0d0e23"
      dark
      style="font-size: 1.15em; font-weight: 500;"
      @click="$goToSignupPage()"
    >
      회원가입하기
    </v-btn>
  </div>
</template>

<script>
export default {
  data: () => {
    return {
      isLoggedIn: false,
    }
  }
}
</script>

<style scope>
.v-application .mt-5 {
  font-family: "KoPub Dotum";
  line-height: 1.5;
  font-weight: 500;
}
</style>