<template>
  <v-avatar
    size='32'
  >
    <img
      :src="imgUrl"
      @error="defaultProfile"
    >
  </v-avatar>
</template>

<script>
export default {
  name: 'UserProfileIcon',
  props: {
    imgUrl: String,
  },
  methods: {
    defaultProfile(e) {
      e.target.src = `https://cdn.vuetifyjs.com/images/john.jpg`
    },
  }
}
</script>

<style>

</style>