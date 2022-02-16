<template>
  <div class="ml-3 mb-5">
    <h2>관심키워드</h2>
    <v-chip-group column>
      <v-chip
        v-for="(keyword, index) in favoredKeyword"
        :key="index"
      >{{ keyword }}</v-chip>
    </v-chip-group>
  </div>
</template>

<script>
import { mapState } from 'vuex'


export default {
  name: 'FavoredKeywordBar',

  data: () => ({

  }),
  computed: {
    ...mapState([
      'favoredKeyword',
    ]),
  },
}
</script>

<style>
</style>