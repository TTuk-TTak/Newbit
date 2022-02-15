<template>
  <div v-if="$store.state.user">
    <v-row>
      <v-col class="pb-0">
        <v-avatar
          size="150"
          class="mt-5 ml-3"
        >
          <v-img src="https://www.gravatar.com/avatar/default?s=200&r=pg&d=mm" />
        </v-avatar>
      </v-col>
    </v-row>
    <v-row class="align-center">
      <v-col class="mx-3">
        <div>{{ user.userNick }}</div>
        <div class="grey--text">{{ `@${user.userId}` }}</div>
      </v-col>
      <v-col class="d-flex justify-end">
        <v-btn
          width="150"
          rounded
          outlined
          color="#0d0e23"
          class="font-weight-bold justify-center"
          style="font-size: 1.15em; font-weight: 500;"
          @click="$goToProfileEdit()"
        >
          프로필 수정
        </v-btn>
      </v-col>
    </v-row>
    <div class="mt-5 mb-5 mx-3">
      <div>{{ user.userIntro }}</div>
      <div class="text-center">게시물 {{ user.userPostCount }}개 | 팔로워 {{ user.userFollowerCount }}명 | 팔로잉 {{ user.userFollowingCount }}명</div>
    </div>
    <v-btn
      rounded
      block
      depressed
      large
      color="#0d0e23"
      dark
      class="mb-3"
      style="font-size: 1.15em; font-weight: 500;"
      @click="TURN_POST_CREATE_MODAL_ON()"
    >
      글작성
    </v-btn>
  </div>
  <div
    v-else
    style="margin-bottom:30px"
  >
    <v-row>
      <v-col class="pb-0">
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
    <v-row>
      <v-col class="mx-3 mb-5">
        로그인이 필요합니다. <br />
        로그인 후 SNS 기능을 이용해보세요. <br />
        아직 회원이 아니시라면, 회원가입 후 아래의 서비스들과 함께하세요! <br /><br />
        <!-- ToDo : 숫자 부분은 후에 {{}}로 수정 -->
        총게시물 100개 | 총회원수 200명 | 컨텐츠 300개 <br />
      </v-col>
    </v-row>
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
import { mapMutations } from 'vuex'
import { mapState } from 'vuex'

export default {
  data: () => {
    return {

    }
  },
  methods: {
    ...mapMutations([
      'TURN_POST_CREATE_MODAL_ON'
    ])
  },
  computed: {
    ...mapState([
      'user',
    ])
  }
}
</script>

<style scoped>
.v-application .mx-3 {
  font-family: "KoPub Dotum";
  line-height: 1.5;
  font-weight: 500;
}
</style>