<template>
  <v-card
    outlined
    class="pa-4 pt-2 cardMargin"
    color="feedBackground"
  >
    <div class="mx-2" style="border-bottom:1px solid lightgray">
      <v-tabs
        class="ml-1 mr-3"
        slider-color='#0d0e23'
      >
        <v-tab class="contentTab">인기 컨텐츠</v-tab>
        <v-tab class="contentTab">최신 컨텐츠</v-tab>
        <hr>
      </v-tabs>
    </div>
    <keyword-toggler class="px-1 mt-3"></keyword-toggler>
    <v-row
      class="pa-2 pt-3"
    >
      <v-col
        class="pa-1 pb-0"
        v-for="i in 10"
        :key="`archived` + i"    
        cols=6
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

<style scope >
.cardMargin{
  padding-left: 1000px; 
}


</style>