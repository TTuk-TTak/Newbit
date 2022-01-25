<template>
  <v-chip-group>
    <keyword-chip></keyword-chip>
    <keyword-chip></keyword-chip>
    <keyword-chip></keyword-chip>
  </v-chip-group>
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