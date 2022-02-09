<template>
  <v-chip-group
    column
  >
    <keyword-chip
      v-for="(shownName, key) in keywordDict"
      :key="`keywordToggler` + shownName"
      :text="shownName"
      :isInToggler="true"
      :isUserFavorite="keywordActivity[key]"
      :variableName="key"
      @toggle-chip="toggleChip"
    ></keyword-chip>
  </v-chip-group>
</template>

<script>
import { mapGetters, mapState } from 'vuex'

import KeywordChip from '@/components/Keyword/KeywordChip.vue'

export default {
  name: 'KeywordToggler',
  components: {
    KeywordChip,
  },
  data: () => {
    return {
      keywordActivity: {},
    }
  },
  methods: {
    setActivity: function () {
      const userKeywordString = this.user.userKeyword
      const userFavoriteKeyword = this.$parseKeyword(userKeywordString)

      for (let keyword in this.keywordDict) {
        if (userFavoriteKeyword.includes(keyword)) {
          this.keywordActivity[keyword] = true
        } else {
          this.keywordActivity[keyword] = false
        }
      }
    },
    toggleChip: function (status) {
      this.keywordActivity[status[0]] = status[1]
    },
  },
  computed: {
    ...mapState([
      'user',
    ]),
    ...mapGetters([
        'keywordDict',
      ]),

  },
  created () {
    this.setActivity()
  },
}
</script>

<style>
</style>