<template>
  <v-chip
    class="ma-1"
    :class="{ short: isFixed }"
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
    isToggleAvailable: Boolean,
  },
  data: () => ({
    isActive: false,
  }),
  methods: {
    toggleChip: function () {
      if (this.isToggleAvailable) {
        this.isActive = !this.isActive
      }
    },
  }

}
</script>

<style>
</style>