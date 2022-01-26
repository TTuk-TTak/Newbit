<template>
  <v-chip
    color='keywordChipBackground'
    text-color='keywordChipText'
  >{{text}}</v-chip>
</template>

<script>
export default {
  name: 'KeywordChip',
  props: {
    text: String,
  },
  data: () => ({

  }),


}
</script>

<style>

</style>