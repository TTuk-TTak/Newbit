<template>
  <v-app id="app">
    <the-nav></the-nav>
    <v-main
      id="main"
      class="container"
    >
      <router-view />
    </v-main>
  </v-app>
</template>

<script>
import TheNav from '@/views/Bars/TheNav.vue'

export default {
  name: 'App',
  components: {
    TheNav,
  },

  data: () => ({

  }),

};
</script>

<style>
#main {
  max-width: 1000px;
  background-color: white;
}

#app {
  background-color: var(--v-background-base);
}
</style>