<template>
  <div>
    <v-chip-group>
      <h2 class="d-flex align-center mr-3">관심키워드</h2>
      <v-chip
        v-for="(keyword, index) in parsed"
        :key="index"
      >{{ keyword }}</v-chip>
    </v-chip-group>
  </div>
</template>

<script>

export default {
  name: 'FavoredKeywordBar2',
  data: () => ({

  }),
  props: {
    parsed: Array
  },
}
</script>

<style>
</style>