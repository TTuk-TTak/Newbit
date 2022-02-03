<template>
  <v-app
    v-if="toggle"
    id="app"
  >
    <the-nav></the-nav>
    <v-main
      id="main"
      class="container p-1"
    >
      <router-view />
    </v-main>
  </v-app>
  <!-- signup, login 페이지를 위해 케이스를 나눈다. vuex로 toggle의 boolean 값을 관리하여 Signup, Login 페이지 Router push 시 아래 출력 -->
  <v-app
    v-else
    id="app"
  >
    <router-view />
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
    toggle: false
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
  /* color: #f7f7eb; */
  background-color: var(--v-background-base);

  padding: 0;
  position: relative;
}

#main {
  max-width: 1500px;
  /* background-color: '#FFFFFF'; */
}
</style>