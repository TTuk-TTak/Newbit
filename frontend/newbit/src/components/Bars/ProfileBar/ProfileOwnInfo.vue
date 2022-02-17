<template>
  <div v-if="$store.state.user">
    <v-row class="text-center">
      <v-col class="pb-0">
        <a href="#none">
        <v-avatar
          @click="$goToProfile(user.userCode)"
          size="150"
          class="mt-5"
        >
          <v-img
            v-if="user.userImg"
            class="v-avatar image"
            :src="user.userImg"
          />
        </v-avatar>
        </a>
      </v-col>
    </v-row>
    <v-row class="text-center">
      <v-col class="">
        <a href="#none" class="underlineOff">
          <div class="text-h6 underlineOff" @click="$goToProfile(user.userCode)">{{ user.userNick }}</div>
        </a>
        <a href="#none" class="underlineOff">
          <div class="grey--text underlineOff" @click="$goToProfile(user.userCode)">{{ `@${user.userId}` }}</div>
        </a>
      </v-col>
      <!-- <v-col class="d-flex justify-end">
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
      </v-col> -->
    </v-row>
    <div class="text-center mt-2 mb-3">
      <v-btn
        @click="$goToSocialFeed()"
        class="font-weight-black"
        elevation="0"
        plain
        rounded
        shaped
      >
        게시물 {{ user.userPostCount }}
      </v-btn>
      <v-dialog
        v-model="dialog1"
        width="500"
      >
        <template v-slot:activator="{ on, attrs }">
          <v-btn
            class="font-weight-black"
            elevation="0"
            v-bind="attrs"
            v-on="on"
            plain
            rounded
            shaped
            @click="openFollowerList(user.userCode)"
          >
            팔로워 {{ user.userFollowerCount }}
          </v-btn>
        </template>
        <follow-modal
          :myUserCode="myUserCode"
          :following_list="following_list"
          :follower_list_origin="follower_list_origin"
          :dialog1="dialog1"
          @props-status-change="onClickChange"
          category="follower"
        ></follow-modal>
      </v-dialog>
      <v-dialog
        v-model="dialog2"
        width="500"
      >
        <template v-slot:activator="{ on, attrs }">
          <v-btn
            class="font-weight-black"
            elevation="0"
            v-bind="attrs"
            v-on="on"
            plain
            rounded
            shaped
            @click="openFollowingList(user.userCode)"
          >
            팔로잉 {{ user.userFollowingCount }}
          </v-btn>
        </template>
        <follow-modal
          :myUserCode="myUserCode"
          :following_list="following_list"
          :following_list_origin="following_list_origin"
          :dialog2="dialog2"
          @props-status-change="onClickChange"
          category="following"
        ></follow-modal>
      </v-dialog>
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
    <v-row class="text-center">
      <v-col class="pb-0">
        <v-avatar
          size="150"
          class="mt-5"
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
        총게시물 {{ allInfo.posts }}개 | 총회원수 {{ allInfo.users }}명 | 컨텐츠 {{ allInfo.contents }}개 <br />
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
import axios from 'axios'
import { mapMutations } from 'vuex'
import { mapState } from 'vuex'
import FollowModal from '@/components/Modals/FollowModal/FollowModal.vue'


const myUserCode = localStorage.getItem('user_code')


export default {
  components: {
    FollowModal
  },
  data: () => {
    return {
      dialog1: false,
      dialog2: false,
      allInfo: {},
      following_list: [],
      following_list_origin: [],
      follower_list_origin: [],
      myUserCode: myUserCode,
    }
  },
  methods: {
    ...mapMutations([
      'TURN_POST_CREATE_MODAL_ON'
    ]),
    fetchAllInformation () {
      axios({
        url: `${this.$serverURL}/info`,
        method: 'get',
      })
        .then((res) => {
          this.allInfo = res.data
        })
    },
    fetchUserFollowingList (user_code) {
      axios({
        url: `${this.$serverURL}/follow/following?uid=${user_code}`,
        method: 'get',
      })
        .then((res) => {
          this.following_list = res.data.map((object) => {
            return object['userCode']
          })
        })
    },
    openFollowingList (user_code) {
      axios({
        url: `${this.$serverURL}/follow/following?uid=${user_code}`,
        method: 'get',
      })
        .then((res) => {
          this.following_list_origin = res.data
        })
    },
    openFollowerList (user_code) {
      axios({
        url: `${this.$serverURL}/follow/follower?uid=${user_code}`,
        method: 'get',
      })
        .then((res) => {
          this.follower_list_origin = res.data
        })
    },
    onClickChange (changedStatus, category) {
      if (category === 'follower') {
        this.dialog1 = changedStatus
      }
      else if (category === 'following') {
        this.dialog2 = changedStatus
      }
    },
  },
  computed: {
    ...mapState([
      'user',
    ])
  },
  created () {
    this.fetchAllInformation()
    this.fetchUserFollowingList(myUserCode)
  }
}
</script>

<style scoped>
.v-application .mx-3 {
  font-family: "KoPub Dotum";
  line-height: 1.5;
  font-weight: 500;
}

.underlineOff {
  text-decoration: none;
}

</style>