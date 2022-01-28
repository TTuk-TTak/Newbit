<template>
  <v-card>
    <v-avatar>
      <img
        src="https://cdn.vuetifyjs.com/images/john.jpg"
        alt="John"
      >
    </v-avatar>
    <v-card-actions>
      <v-btn icon>
        <v-icon>mdi-cards-heart-outline</v-icon>
        <span>2</span>
      </v-btn>
      <v-btn icon>
        <v-icon>mdi-message-outline</v-icon>
        <span>3</span>
      </v-btn>
      <v-btn icon>
        <v-icon>mdi-share</v-icon>
      </v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
export default {
  name: 'PostCard',
}
</script>

<style>

</style>