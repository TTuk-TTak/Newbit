<template>
  <v-btn
    style="padding-left:25px; padding-right:25px"
    rounded
    depressed
    :outlined="this.followStatus"
    :color="color"
    :class="fontcolor"
    @click="onClick()"
  >
    {{ message }}
  </v-btn>
</template>

<script>
import { mapState } from 'vuex'

export default {
  name: 'FollowBtn',
  props: {
    isFollowed: Boolean,
    userCode: Number,
    userNick: String,
  },
  data () {
    return {
      followStatus: this.isFollowed,
    }
  },
  methods: {
    onClick () {
      this.followStatus = !this.followStatus
      if (this.followStatus) {
        this.$follow(this.userCode, this.userNick)
        this.plusOne()
      }
      else {
        this.$unFollow(this.userCode, this.userNick)
        this.minusOne()
      }
    },
    plusOne () {
      this.user.userFollowingCount += 1
    },
    minusOne () {
      this.user.userFollowingCount -= 1
    },
  },
  computed: {
    message: function () {
      return this.followStatus ? '언팔로우' : '팔로우'
    },
    color: function () {
      return this.followStatus ? "'#C4C4C4'" : '#0d0e23'
    },
    fontcolor: function () {
      return this.followStatus ? '"#0D0E23"--text' : 'white--text'
    },
    ...mapState([
      'user',
    ])
  }
}
</script>

<style>
</style>