<template>
  <v-chip
    class="ma-1 justify-center"
    :class="{ short: isFixed }"
    :color='isActive ? `keywordChipText` : `keywordChipBackground`'
    :text-color='isActive ? `keywordChipBackground` : `keywordChipText`'
    @click="toggleChip"
    :small='isSmall'
    :large='!isSmall'
    :label='isLabel'
  ><span>{{text}}</span></v-chip>
</template>

<script>
export default {
  name: 'KeywordChip',
  props: {
    text: String,
    isToggleAvailable: Boolean,
    isSmall: Boolean,
    isLabel: Boolean,
    isFixed: Boolean,
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
.short {
  width: 150px;
}
.short span {
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}
</style>