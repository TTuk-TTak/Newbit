<template>
  <div>
    <v-tabs
      class="backgroundDark"
    >
      <v-tab
        to="/social"
        @click='clickSocial()'
        class="backgroundDark"
        :ripple='false'
        text
        >
        소셜 피드
      </v-tab>
      <v-tab
        to="/content"
        class="backgroundDark"
        :ripple='false'
        text
        >
        큐레이션
      </v-tab>
      <v-tab
        to="/archive"
        class="backgroundDark"
        :ripple='false'
        text
        >
        아카이빙
      </v-tab>
    </v-tabs>
  </div>
</template>

<script>
export default {
  name: 'TheNavBarMenu',
  components: {
  },
  methods: {
    clickSocial() {
      console.log('social에서 눌렀을 때 누르면 새로고침하는 함수')
    }
  }
}
</script>

<style scope>
.v-tab.v-tab  {
  font-family: 'KoPub Dotum';
  font-weight: 500;
}
</style>