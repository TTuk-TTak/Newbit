<template>
  <v-chip
    class="ma-1"
    :color='isActive ? `keywordChipText` : `keywordChipBackground`'
    :text-color='isActive ? `keywordChipBackground` : `keywordChipText`'
    small
    @click="toggleChip"
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