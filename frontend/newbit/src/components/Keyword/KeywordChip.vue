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
    isInToggler: Boolean,
    isSelected: Boolean,
  },
  data: () => ({

  }),
  methods: {
    toggleChip: function () {
      if (this.isToggleAvailable && this.isSelected) {
        this.isSelected = !this.isSelected
      }
    },
  }

}
</script>

<style>
</style>