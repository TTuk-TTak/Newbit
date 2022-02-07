<template>
  <v-card
    class="pa-2"
    color="feedBackground"
  >
    <keyword-toggler></keyword-toggler>
      <v-tabs
        slider-color='#C4C4C4'
      >
        <v-tab>읽고 싶은 글</v-tab>
        <v-tab>읽었던 글</v-tab>
      </v-tabs>
    <v-row
      class="pa-2"
    >
      <v-col
        v-for="i in 10"
        :key="`archived` + i"    
        class="pa-1 pb-0"
        cols=4

      >
        <content-card></content-card>
      </v-col>
    </v-row>
  </v-card>
</template>

<script>
import KeywordToggler from '@/components/Keyword/KeywordToggler.vue'
import ContentCard from '@/components/Cards/ContentCard.vue'

export default {
  name: 'Archive',
  components: {
    KeywordToggler,
    ContentCard,
  },
}
</script>

<style>
</style>