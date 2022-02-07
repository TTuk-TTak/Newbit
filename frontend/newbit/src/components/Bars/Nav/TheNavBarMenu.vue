<template>
  <div>
    <v-tabs
      class="backgroundDark"
    >
      <v-tab
        to="/social"
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
}
</script>

<style>
</style>