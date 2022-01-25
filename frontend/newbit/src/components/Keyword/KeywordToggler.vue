<template>
  <v-chip-group
    multiple
    active-class="primary--text"
  >
    <keyword-chip
      v-for="tag in tags"
      :key="tag"
      :text="tag"
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
      ],
    }
  },
}
</script>

<style>

</style>