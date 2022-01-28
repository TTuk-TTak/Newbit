<template>
  <v-chip
    class="ma-1"
    :color='isActive ? `keywordChipText` : `keywordChipBackground`'
    :text-color='isActive ? `keywordChipBackground` : `keywordChipText`'
    @click="toggleChip"
    small
  >{{text}}</v-chip>
</template>

<script>
export default {
  name: 'KeywordChip',
  props: {
    text: String,
  },
  data: () => ({
    isActive: false,
  }),
  methods: {
    toggleChip: function () {
      this.isActive = !this.isActive
    }
  }

}
</script>

<style>

</style>