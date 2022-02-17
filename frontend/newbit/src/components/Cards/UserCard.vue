<template>
  <v-list-item
    class="border mb-3 ml-10 firstlogin-follow"
  >
    <v-list-item-avatar
      @click="$goToProfile(userInfo.userCode)"
    >
      <v-img
        class="v-avatar image"
        :src="userInfo.userImg"
      ></v-img>
    </v-list-item-avatar>

    <v-list-item-content>
      <v-list-item-title 
        @click="$goToProfile(userInfo.userCode)"
        v-text="userInfo.userNick"></v-list-item-title>
      <v-list-item-subtitle
        @click="$goToProfile(userInfo.userCode)" 
        v-text="`@${userInfo.userId}`"></v-list-item-subtitle>
    </v-list-item-content>
    <v-list-item-action>
      <v-btn
        v-if="userInfo.isFollow"
        @click="clickFollowBtn()"
        color="btnDarkBackground"
        class="white--text"
        rounded
        depressed
      >
      언팔로우
      </v-btn>
      <v-btn
        v-if="!userInfo.isFollow"
        @click="clickFollowBtn()"
        class="btnDarkBackground--text"
        rounded
        depressed
        outlined
      >
      팔로우
      </v-btn>
    </v-list-item-action>
  </v-list-item>
</template>

<script>

export default {
  name: 'UserCard',
  props: {
    userInfo: Object,
  },
  components: {

  },

  methods: {
    clickFollowBtn () {
      if (this.userInfo.isFollow) {
        this.userInfo.isFollow = false
        this.$unFollow(this.userInfo.userCode, this.userInfo.userNick)
      } else {
        this.userInfo.isFollow = true
        this.$follow(this.userInfo.userCode, this.userInfo.userNick)
      }
    }
  },
  computed: {
    message: function () {
      return this.followStatus ? '언팔로우' : '팔로우'
    },
    color: function () {
      return this.followStatus ? 'btnDarkBackground' : 'btnLiteBackground'
    },
    fontcolor: function () {
      return this.followStatus ? 'btnDarkText--text' : 'btnLiteText--text'
    }
  }

}
</script>

<style scoped>

</style>