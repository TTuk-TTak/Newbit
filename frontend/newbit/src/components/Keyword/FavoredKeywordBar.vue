<template>
  <div class="ml-3 mb-5">
    <h2>관심키워드</h2>
    <v-chip-group column>
      <v-chip
        v-for="(keyword, index) in favoredKeyword"
        :key="index"
      >{{ matchName(keyword) }}</v-chip>
    </v-chip-group>
  </div>
</template>

<script>
import { mapGetters, mapState } from 'vuex'


export default {
  name: 'FavoredKeywordBar',

  data: () => ({

  }),
  methods: {
    matchName (string) {
      let found = false
      for (let keyword in this.keywordDict) {
        if (string === keyword) {
          found = true
          return this.keywordDict[keyword]
        }
      }
      if (!found) {
        return string
      }
    },
  },
  computed: {
    ...mapState([
      'favoredKeyword',
    ]),
    ...mapGetters([
      'keywordDict',
    ]),
  },
}
</script>

<style>
</style>