<template>
  <div>
    <v-app-bar
      color="backgroundDark"
      dense
      dark
      flat
    >
      <v-spacer></v-spacer>
      <the-nav-bar-menu></the-nav-bar-menu>
      <v-spacer></v-spacer><v-spacer></v-spacer>
      <v-toolbar-title>Newbit</v-toolbar-title>
      <v-spacer></v-spacer><v-spacer></v-spacer>
      <the-nav-bar-icons></the-nav-bar-icons>
      <v-spacer></v-spacer>
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

<style scoped>
.v-toolbar__title {
  font-family: 'Do Hyeon';
  font-weight: 100;
  font-size: 1.75em;
}

.v-toolbar {
  position : fixed;
  z-index: 100;
}

</style>