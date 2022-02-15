<template>
  <v-btn
    style="padding-left:25px; padding-right:25px"
    rounded
    depressed
    outlined
    :color="color"
    :class="fontcolor"
    @click="onClick()"
  >
    {{ message }}
  </v-btn>
</template>

<script>
export default {
  name: 'FollowBtn',
  props: {
    isFollowed: Boolean,
    userCode: Number,
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
        this.$follow(this.userCode)
      }
      else {
        this.$unFollow(this.userCode)
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

<style>
</style>