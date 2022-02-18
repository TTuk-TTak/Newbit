<template>
  <v-chip
    outlined
    small
    @click="$clickHashtag(text)"
  >
    #{{ text }}
  </v-chip>
</template>

<script>
export default {
  name: 'Hashtag',
  props: {
    text: String,
  }
}
</script>

<style>

</style>