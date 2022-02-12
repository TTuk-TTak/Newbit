<template>
  <v-card>
    <v-card-title class="text-h5 grey lighten-2">
      Privacy Policy
    </v-card-title>

    <v-card-text>
      {{ warningMessage }}
    </v-card-text>

    <v-divider></v-divider>

    <v-card-actions>
      <v-spacer></v-spacer>
      <v-btn
        color="primary"
        text
        @click="dialog = false"
      >
        I accept
      </v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
export default {
  name: 'DeleteWarningModal',
  props: {
    type: String,
  },
  data: () =>{
    return {
      defaultMessage: " 삭제하면 복구할 수 없습니다. 삭제할까요?",
    }
  },
  computed: {
    warningMessage () {
      let warningHeader = ''
      
      if (this.type === 'post' ) {
        warningHeader = '게시글을'
      } else if (this.type === 'comment') {
        warningHeader = '덧글을'
      } else if (this.type === 'reply') {
        warningHeader = '답글을'
      }
        return warningHeader + this.defaultMessage
      } 
    }
}
</script>

<style>

</style>