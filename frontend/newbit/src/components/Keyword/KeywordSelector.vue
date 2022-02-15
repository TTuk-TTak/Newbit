<template>
  <div class="px-10">
    <v-tabs>
      <v-tabs-slider></v-tabs-slider>
      <v-tab
        @click="changeToLanguage"
        class="font-weight-bold"
      >개발언어</v-tab>
      <v-tab
        @click="changeToFrontend"
        class="font-weight-bold"
      >프론트엔드</v-tab>
      <v-tab
        @click="changeToBackend"
        class="font-weight-bold"
      >백엔드</v-tab>
      <v-tab
        @click="changeToNormal"
        class="font-weight-bold"
      >일반</v-tab>
    </v-tabs>
    <v-divider></v-divider>
    <v-chip-group
      column
      class="mt-3 mb-9"
    >
      <div v-if="toggle === 'language'">
        <keyword-chip2
          v-for="(languagetag, key) of categorizedKeywords['개발언어'].data"
          :key="key"
          :text="languagetag.shownName"
          :isInToggler="true"
          :isUserFavorite="keywordActivity[key]"
          :variableName="key"
          @toggle-chip="toggleChip"
        ></keyword-chip2>
      </div>
      <div v-else-if="toggle === 'frontend'">
        <keyword-chip2
          v-for="(frontendtag, key) of categorizedKeywords['Front-end'].data"
          :key="key"
          :text="frontendtag.shownName"
          :isInToggler="true"
          :isUserFavorite="keywordActivity[key]"
          :variableName="key"
          @toggle-chip="toggleChip"
        ></keyword-chip2>
      </div>
      <div v-else-if="toggle === 'backend'">
        <keyword-chip2
          v-for="(backendtag, key) of categorizedKeywords['Back-end'].data"
          :key="key"
          :text="backendtag.shownName"
          :isInToggler="true"
          :isUserFavorite="keywordActivity[key]"
          :variableName="key"
          @toggle-chip="toggleChip"
        ></keyword-chip2>
      </div>
      <div v-else-if="toggle === 'normal'">
        <keyword-chip2
          v-for="(normaltag, key) of categorizedKeywords['일반'].data"
          :key="key"
          :text="normaltag.shownName"
          :isInToggler="true"
          :isUserFavorite="keywordActivity[key]"
          :variableName="key"
          @toggle-chip="toggleChip"
        ></keyword-chip2>
      </div>
    </v-chip-group>
  </div>
</template>

<script>
import { mapGetters, mapState } from 'vuex'

import KeywordChip2 from '@/components/Keyword/KeywordChip2.vue'


export default {
  name: 'KeywordSelector',
  components: {
    KeywordChip2,
  },
  data: () => {
    return {
      keywordActivity: {},
      toggle: 'language',
      keyword: '',
    }
  },
  methods: {
    changeToLanguage () {
      this.toggle = 'language'
    },
    changeToFrontend () {
      this.toggle = 'frontend'
    },
    changeToBackend () {
      this.toggle = 'backend'
    },
    changeToNormal () {
      this.toggle = 'normal'
    },
    //
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
      this.$store.dispatch('saveUserKeyword', this.makeQueryString())
    },
    makeQueryString: function () {
      let queryString = ''
      for (let key in this.keywordActivity) {
        if (this.keywordActivity[key]) {
          queryString += '_' + key
        }
      }
      queryString = queryString.slice(1)
      return queryString
    },
  },
  computed: {
    ...mapState([
      'user',
    ]),
    ...mapGetters([
      'categorizedKeywords',
      'keywordDict',
    ])
  },
  created () {
    this.setActivity()
  },
}
</script>

<style>
</style>