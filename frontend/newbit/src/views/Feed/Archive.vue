<template>
  <v-card
    class="pt-2 px-4"
  >
    <v-row>
      <v-tabs
        class="pt-2"
        slider-color='#C4C4C4'
      >
        <v-tab>읽고 싶은 글</v-tab>
        <v-tab>읽었던 글</v-tab>
    </v-tabs>
    </v-row>
    <v-row>
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
import ContentCard from '@/components/Cards/ContentCard.vue'

export default {
  name: 'Archive',
  components: {
    ContentCard,
  },
}
</script>

<style>
</style>