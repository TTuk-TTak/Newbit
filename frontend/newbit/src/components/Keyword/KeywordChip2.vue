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
.short {
  width: 150px;
}
.short span {
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}
</style>