<template>
  <div>
    <h3>관심키워드</h3>
    <v-chip-group>
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
}
</script>

<style>
</style>