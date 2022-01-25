<template>
  <div>
    <v-app-bar
      color="grey darken-3"
      dense
      dark
    >
      <the-nav-bar-menu></the-nav-bar-menu>
      <v-spacer></v-spacer>
      <v-toolbar-title>NEWBIT</v-toolbar-title>
      <v-spacer></v-spacer>
      <the-nav-bar-icons></the-nav-bar-icons>
    </v-app-bar>
  </div>

</template>

<script>
import TheNavBarIcons from '@/components/Bars/Nav/TheNavBarIcons.vue'
import TheNavBarMenu from '@/components/Bars/Nav/TheNavBarMenu.vue'

export default {
  name: 'TheNav',
  components: {
    TheNavBarIcons,
    TheNavBarMenu,
  },
  data: () => ({
  
  }),
}
</script>

<style>

</style>