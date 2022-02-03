<template>
  <v-avatar
    size='32'
  >
    <img
      :src="imgUrl"
      alt="Img"
    >
  </v-avatar>
</template>

<script>
export default {
  name: 'UserProfileIcon',
  props: {
    imgUrl: String,
  },
}
</script>

<style>

</style>