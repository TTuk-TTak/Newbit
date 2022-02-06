<template>

  <!-- <div>
    {{ keywords }}
    <keyword-chip></keyword-chip>
  </div> -->
  <v-chip-group column>
    >>>>>>> master
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
  computed: {
    keywords: function () {
      // const keywordObject = {}
      // for (let key in this.$KEYWORDS) {
      //   const value = this.$KEYWORDS[key]
      // }
      return Object.values(this.$KEYWORDS)
    }
  },
}
</script>

<style>
</style>