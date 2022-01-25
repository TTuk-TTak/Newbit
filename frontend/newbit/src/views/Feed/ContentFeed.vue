<template>
  <div>
    <keyword-toggler></keyword-toggler>
    <v-tabs>
      <v-tab>인기 컨텐츠</v-tab>
      <v-tab>최신 컨텐츠</v-tab>
    </v-tabs>
    <v-row>
      <v-col
        v-for="n in 3"
        :key="n"
        cols="6"
      >
        <content-card></content-card>
      </v-col>
    </v-row>
  </div>
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