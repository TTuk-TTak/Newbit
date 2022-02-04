<template>
  <v-chip-group column>
    <keyword-chip
      v-for="tag in tags"
      :key="tag"
      :text="tag"
      :isToggleAvailable='true'
      :isSmall='true'
      :isLabel='false'
      :isFixed='false'
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
      tags: [
        'UI/UX',
        'Vue.js',
        'Frontend',
        'Backend',
        'JS',
        'Python',
        'UI/UX',
        'Vue.js',
        'Frontend',
        'Backend',
        'JS',
        'Python',
        'UI/UX',
        'Vue.js',
        'Frontend',
        'Backend',
        'JS',
        'Python',
      ],
    }
  },
}
</script>

<style>
</style>