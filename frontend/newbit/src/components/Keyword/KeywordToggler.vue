<template>
  <v-chip-group
    column
  >
    <keyword-chip
      v-for="(key, value) in keywords"
      :key="value"
      :text="key"
      :isInToggler='true'
    ></keyword-chip>
  </v-chip-group>
</template>

<script>
import _ from 'lodash'

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
      const keywords = _.mapValues(this.$KEYWORDS, 'shownName')
      return keywords
    }
  },
  created: {
    // 1. 
    // 유저의 정보 중 '키워드' 정보만 파싱해
    // 관심 있어한 keyword를 active하는 함수
  }
}
</script>

<style>
</style>