<template>
  <v-btn
    color='btnDarkBackground'
    class='btnDarkText--text'
    :style="style"
  > 
    {{ message }}
  </v-btn>
</template>

<script>
export default {
  name: 'BtnDark',
  props: {
    message: String,
    style: Object,
  }
}
</script>

<style>

</style>