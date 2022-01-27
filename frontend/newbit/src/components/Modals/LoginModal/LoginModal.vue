<template>
  <div class="text-center">
    <v-dialog
      v-model="dialog"
      width="45%"
    >
      <template v-slot:activator="{ on, attrs }">
        <v-btn
          color="red lighten-2"
          dark
          v-bind="attrs"
          v-on="on"
        >
          Click Me
        </v-btn>
      </template>
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
              <login-modal-default></login-modal-default>
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
  components: { LoginModalDefault },

  data () {
    return {
      dialog: false,
    }
  },
  methods: {

  }
}
</script>

<style>
</style>