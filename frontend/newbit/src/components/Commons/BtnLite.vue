<template>
  <v-btn
    color='btnLiteBackground'
    class='btnLiteText--text'
  >
    {{ message }}
  </v-btn>
</template>

<script>
export default {
  name: 'BtnLite',
  props: {
    message: String,
  },
}
</script>

<style>

</style>