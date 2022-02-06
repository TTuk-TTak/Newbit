<template>
  <v-chip-group
    column
  >
    <keyword-chip
      v-for="(key, value) in keywords"
      :key="key"
      :text="value"
      :isToggleAvailable='true'
    ></keyword-chip>
  </v-chip-group>
</template>

<script>
import KeywordChip from '@/components/Keyword/KeywordChip.vue'

export default {
  name: 'KeywordToggler',
  components: {
    KeywordChip,    
  },
  data: () => {
    return {
    }
  },
  computed: {
    // 키워드 中 소분류 키워드만 추출 후 반환.
    keywords: function () {
      const keywordObject = {}
      for (let keywordSets of Object.values(this.$KEYWORDS)) {
        for (let [shownName, queryName] of Object.entries(keywordSets)) {
          keywordObject[shownName] = queryName
        }
      }
      return keywordObject
    }
  },
}
</script>

<style>

</style>