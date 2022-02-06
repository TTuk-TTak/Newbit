<template>
  <v-row
    class=" pa-2 px-4"
  >
    <v-row
      class="pt-2"
    >
      <v-col
        v-for="i in 10"
        :key="`archived` + i"    
        class="pa-1 pb-1"
        cols=12
      >
        <post-card></post-card>
      </v-col>
    </v-row>
  </v-row>

</template>

<script>
import PostCard from '@/components/Cards/PostCard.vue'

export default {
  name: 'SocialFeed',
  components: {
    PostCard,
  },
}
</script>

<style>
</style>