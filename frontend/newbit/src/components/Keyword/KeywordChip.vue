<template>
  <v-chip
    class="ma-1"
    :color='isActive ? `keywordChipText` : `keywordChipBackground`'
    :text-color='isActive ? `keywordChipBackground` : `keywordChipText`'
    @click="toggleChip()"
    small
  >{{text}}</v-chip>
</template>

<script>
export default {
  name: 'KeywordChip',
  props: {
    text: String,
    isInToggler: Boolean,
    isUserFavorite: Boolean,
    variableName: String,
  },
  data: () => ({
    isActive: false,
  }),
  methods: {
    toggleChip: function () {
      if (this.isInToggler) {
        this.isActive = !this.isActive
        const status = [this.variableName, this.isActive]
        this.$emit('toggle-chip', status)
      } else {
        this.$clickKeywordChip(this.variableName)
      }
    },
    setDefaultActivation: function () {
      if (this.isInToggler && this.isUserFavorite) {
        this.isActive = true
      }
    }

  },
  created () {
    this.setDefaultActivation()
  },

}
</script>

<style>
</style>