<template>
  <v-btn>
    {{ message }}
  </v-btn>
</template>

<script>
export default {
  name: 'FollowBtn',
  data() {
    return {
      // 1/24 임시로 생성. 추후 prop으로 대체 필요. . 
      isFollowed: true,
      message: this.isFollowed ? '언팔로우' : '팔로우'
    }
  }
}
</script>

<style>

</style>