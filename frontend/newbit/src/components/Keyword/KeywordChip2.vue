<template>
  <v-chip
    class="ma-1 short justify-center"
    :color='isActive ? `keywordChipText` : `keywordChipBackground`'
    :text-color='isActive ? `keywordChipBackground` : `keywordChipText`'
    @click="toggleChip"
    large
    label
  ><span>{{text}}</span></v-chip>
</template>

<script>
export default {
  name: 'KeywordChip2',
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
.short {
  width: 140px;
}
.short span {
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}
</style>