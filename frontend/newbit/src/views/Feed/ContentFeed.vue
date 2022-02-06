<template>
  <v-card
    class="pa-2"
    color='feedBackground'>
    <keyword-toggler></keyword-toggler>
    <v-tabs
      slider-color='#C4C4C4'
    >
      <v-tab>인기 컨텐츠</v-tab>
      <v-tab>최신 컨텐츠</v-tab>
      <hr>
    </v-tabs>
    <v-row
      class='pa-2'
    >
      <v-col
        class="pa-2"
        v-for="n in 21"
        :key="n"
        cols="4"
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
  name: 'ContentFeed',
  components: {
    KeywordToggler,
    ContentCard,
  },
}
</script>

<style>

</style>