<template>
  <div>
    <v-tabs class="backgroundDark">
      <v-tab
        to="/social"
        ripple=false
      >소셜 피드</v-tab>
      <v-tab
        to="/content"
        ripple=false
      >큐레이션</v-tab>
      <v-tab
        to="/archive"
        ripple=false
      >아카이빙</v-tab>
    </v-tabs>
  </div>
</template>

<script>
export default {
  name: 'TheNavBarMenu',
  components: {
  }
}
</script>

<style>
</style>