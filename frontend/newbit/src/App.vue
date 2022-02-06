<template>
  <v-app id="app">
    <the-nav v-if="toggle"></the-nav>
    <v-main
      id="main"
      class="container"
      style="margin-top:50px"
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
    toggle: true
  }),

};
</script>

<style>
@import url("https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@400;700;800&display=swap");
@import url("https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap");

@font-face {
  font-family: "KoPubDotumLight";
  src: url("https://cdn.jsdelivr.net/npm/font-kopubworld@1.0.3/fonts/KoPubWorld-Dotum-Light.woff2")
    format("woff");
  font-weight: normal;
  font-style: normal;
}
@font-face {
  font-family: "KoPubDotumMedium";
  src: url("https://cdn.jsdelivr.net/npm/font-kopubworld@1.0.3/fonts/KoPubWorld-Dotum-Medium.woff2")
    format("woff");
  font-weight: normal;
  font-style: normal;
}
@font-face {
  font-family: "KoPubDotumBold";
  src: url("https://cdn.jsdelivr.net/npm/font-kopubworld@1.0.3/fonts/KoPubWorld-Dotum-Bold.woff2")
    format("woff");
  font-weight: normal;
  font-style: normal;
}

#app {
  font-family: "KoPubDotumMedium", sans-serif;
  font-weight: 400;
  letter-spacing: 0.05em;
  font-size: 14px;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  background-color: #f2f2f4;

  padding: 0;
  position: relative;
}

#main {
  max-width: 1500px;
  /* background-color: '#FFFFFF'; */
}
</style>