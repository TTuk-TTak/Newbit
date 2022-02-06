<template>
  <v-btn style="padding-left:25px; padding-right:25px"
    rounded
    depressed
    outlined
    color = "#0d0e23"
    :class="styleObject"
  >
    {{ message }}
  </v-btn>
</template>

<script>
export default {
  name: 'FollowBtn',
  data() {
    return {
      // 1/24 임시로 생성. 추후 prop으로 대체 필요. . 
      isFollowed: false,
      message: this.isFollowed ? '언팔로우' : '팔로우',
      styleObject:  {
        color: this.isFollowed === true ? '#0D0E23' : '#C4C4C4',
        class: this.isFollowed === true ? 'btnDarkText--text' : 'btnLiteText--text',
      },
    }
  }
}
</script>

<style>

</style>