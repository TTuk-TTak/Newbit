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
  <div v-else>
    <v-row>
      <v-col
        cols="7"
        align-self="center"
      >
        <v-avatar></v-avatar>
        <v-img
          class="v-avatar image"
          src="https://www.gravatar.com/avatar/default?s=200&r=pg&d=mm"
        />
      </v-col>
    </v-row>
    <div>
      <p>로그인이 필요합니다</p>
      <p>로그인 후 SNS 기능을 이용해보세요.</p>
      <p>아직 회원이 아니시라면, 회원가입하시고 아래의 서비스들과 함께하세요!</p>
    </div>
    <div class="d-flex justify-space-between">
      <span>총게시물</span>
      <span>총회원수</span>
      <span>컨텐츠</span>
    </div>
    <v-btn
      rounded
      block
      class="font-weight-bold mb-3"
      color="grey lighten-3"
    >
      로그인하기
    </v-btn>
    <v-btn
      rounded
      block
      color="black"
      dark
      class="font-weight-bold"
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

<style>
</style>