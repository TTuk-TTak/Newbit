<template>
  <div v-if="isVertical === true">
    <span class="text-h5 font-weight-bold">관심키워드</span>
    <v-chip-group column>
      <keyword-chip></keyword-chip>
      <keyword-chip></keyword-chip>
      <keyword-chip></keyword-chip>

    </v-chip-group>
  </div>
  <div v-else>
    <v-chip-group>
      <span class="text-h5 font-weight-bold mr-6 d-flex align-center">관심키워드</span>
      <keyword-chip></keyword-chip>
      <keyword-chip></keyword-chip>
      <keyword-chip></keyword-chip>
    </v-chip-group>
  </div>
</template>

<script>
import KeywordChip from '@/components/Keyword/KeywordChip.vue'

export default {
  name: 'FavoredKeywordBar',
  components: {
    KeywordChip
  },
  props: {
    isVertical: Boolean
  },
  data: () => ({

  }),
}
</script>

<style>
</style>