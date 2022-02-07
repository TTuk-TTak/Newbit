<template>
  <div v-if="isVertical === true" class="ml-3 mb-5">
    <h2>관심키워드</h2>
    <v-chip-group column>
      <v-chip>JAVA</v-chip><v-chip>SpringBoot</v-chip><v-chip>웹개발</v-chip>
      <v-chip>Database</v-chip><v-chip>VueJS</v-chip><v-chip>Git</v-chip>
      <keyword-chip></keyword-chip>
      <keyword-chip></keyword-chip>
      <keyword-chip></keyword-chip>
    </v-chip-group>
  </div>
  <div v-else>
    <v-chip-group>
      <h2>관심키워드</h2>
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