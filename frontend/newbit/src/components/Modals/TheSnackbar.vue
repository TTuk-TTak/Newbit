<template>
  <v-snackbar
    v-model="snackbar.isRendered"
    :timeout="snackbar.timeout"
    :color="`btnDarkBackground`"
    :class="`btnDarkText--text pa-0 ma-0`"
    >
    {{ snackbar.text }}
    <!-- <p class="text-center ma-0 pa-0">{{ snackbar.text }}</p> -->
    <!-- <template v-slot:action="{ attrs }">
      <v-btn
        color="blue"
        text
        v-bind="attrs"
        @click="snackbar = false"
        >
        닫기
      </v-btn>
    </template> -->
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