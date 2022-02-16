<template>
  <div>
    <v-chip-group>
      <h2 class="d-flex align-center mr-3">관심키워드</h2>
      <v-chip
        v-for="(keyword, index) in parsed"
        :key="index"
      >{{ matchName(keyword) }}</v-chip>
    </v-chip-group>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'

export default {
  name: 'FavoredKeywordBar2',
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
  props: {
    parsed: Array
  },
  computed: {
    ...mapGetters([
      'keywordDict',
    ]),
  },
}
</script>

<style>
</style>