<template>
  <v-btn
    color='btnDarkBackground'
    class='btnDarkText--text'
  > 
    {{ message }}
  </v-btn>
</template>

<script>
export default {
  name: 'BtnDark',
  props: {
    message: String,
  }
}
</script>

<style>

</style>