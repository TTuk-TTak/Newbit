<template>
  <v-chip-group
    column
    :key='togglerRerenderKey'
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
  props: {
    isInCurationFeed: Boolean
  },
  data: () => {
    return {
      keywordActivity: {},
      togglerRerenderKey: 0,
    }
  },
  methods: {
    setActivity: function () {
      let userKeywordString = ''
      if (this.isInCurationFeed && this.preSelectedKeyword) {
        userKeywordString = this.preSelectedKeyword
      // } else if (this.user){
      //   userKeywordString = this.user.userKeyword
      } else {
        userKeywordString = "null"
      }
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
      this.makeQueryString()
    },
    makeQueryString: function () {
      let queryString = ''
      for (let key in this.keywordActivity) {
        if (this.keywordActivity[key]) {
          queryString += '_' + key 
        } 
      }
      queryString = queryString.slice(1)
      this.$emit('query-string-changed', queryString)
      return queryString
    },
  },
  computed: {
    ...mapState([
      'user',
      'preSelectedKeyword',
    ]),
    ...mapGetters([
        'keywordDict',
      ]),

  },
  created () {
    this.setActivity()
  },
  watch: {
    preSelectedKeyword: {
      async handler () {
        if (this.preSelectedKeyword) {
          console.log(this.preSelectedKeyword, 12312323)
          await this.setActivity()
          this.makeQueryString()
          this.$store.dispatch("presetCurationKeyword", '')
        }
      }
    }
  }
}
</script>

<style>
</style>