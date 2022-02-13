<template>
  <div class="text-center">
    <v-dialog
      v-model="dialog"
      width="45%"
    >
      <v-card class="rounded-xl ma-0 pa-0">
        <v-container>
          <v-row>
            <v-col
              cols="6"
              class="pa-0"
            >
              <v-img
                src="@/assets/side.jpg"
                class="rounded-l-xl"
                height="100%"
              ></v-img>
            </v-col>
            <v-col
              cols="6"
              class="pa-0"
            >
              <login-modal-default @click-change="closeModal()"></login-modal-default>
            </v-col>
          </v-row>
        </v-container>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
import LoginModalDefault from '@/components/Modals/LoginModal/LoginModalDefault.vue'


export default {
  name: 'LoginModal',
  components: { LoginModalDefault },

  data () {
    return {
      dialog: this.isLogged()
    }
  },
  computed: {

  },
  methods: {
    isLogged: function () {
      const token = localStorage.getItem('jwt')
      if (token) {
        return false
      }
      return true
    },
    closeModal () {
      this.dialog = false
    }
  }
}
</script>

<style>
</style>