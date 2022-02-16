<template>
  <div
    v-if="isVertical === true"
    class="ml-3 mb-5"
  >
    <h2>관심키워드</h2>
    <v-chip-group column>
      <v-chip
        v-for="(keyword, index) in favoredKeyword"
        :key="index"
      >{{ keyword }}</v-chip>
    </v-chip-group>
  </div>
  <div v-else>
    <v-chip-group>
      <h2>관심키워드</h2>
    </v-chip-group>
  </div>
</template>

<script>
// import axios from 'axios'
import { mapState } from 'vuex'


// const myUserCode = localStorage.getItem('user_code')

export default {
  name: 'FavoredKeywordBar',

  props: {
    isVertical: Boolean
  },
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