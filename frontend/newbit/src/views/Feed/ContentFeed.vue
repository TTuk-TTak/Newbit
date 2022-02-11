<template >
  <v-card
    outlined
    class="pa-4 pt-2 cardMargin"
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
      class='pa-2 pt-3'
    >
      <v-col
        class="pa-2"
        v-for="n in 21"
        :key="n"
        cols="6"
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

<style scope>
.cardMargin{
  padding-left: 1000px; 
}
.v-tab.v-tab.contentTab {
  font-size: 1.2em;
  font-weight: 500;
  font-family: 'KoPub Dotum';
  color : #818181;
}
  .v-tab.v-tab--active.contentTab{
    color : #0d0e23;
    font-weight: 700;
  }
</style>