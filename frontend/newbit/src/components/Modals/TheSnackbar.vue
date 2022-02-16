<template>
  <v-snackbar
    v-model="snackbar.isRendered"
    :timeout="snackbar.timeout"
    >
    {{ snackbar.text }}
    <template v-slot:action="{ attrs }">
      <v-btn
        color="blue"
        text
        v-bind="attrs"
        @click="snackbar = false"
        >
        Close
      </v-btn>
    </template>
  </v-snackbar>
</template>

<script>
import { mapState } from 'vuex'

export default {
  name: 'TheSnackbar',
  computed: {
    ...mapState([
      'snackbar',
    ])
  }
}
</script>

<style>

</style>