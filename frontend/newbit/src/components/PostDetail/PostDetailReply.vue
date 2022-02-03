<template>
  <v-container class="py-0">
    <v-row class="ml-2 align-center">
      <user-profile-icon :imgUrl="`https://avatars0.githubusercontent.com/u/9064066?v=4&s=460`"></user-profile-icon>
      <v-col>
        <span>HunHun</span>
        <span class="mx-2">25분 전</span>
        <p class="mb-0">답글답글입니다</p>
      </v-col>
    </v-row>
  </v-container>

</template>

<script>
import UserProfileIcon from '@/components/Commons/UserProfileIcon.vue'

export default {
  name: 'PostDetailReply',
  components: {
    UserProfileIcon,
  },
}
</script>

<style>

</style>