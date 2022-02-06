<template>
  <div class="ml-3">
    <h2>팔로우 추천</h2>
    <v-container>
      <v-row
        v-for="n in 4"
        :key="n"
      >
        <v-col
          cols="2"
          align-self="center"
          class="pa-1"
        >
          <v-avatar size="40">
          <v-img
            class="v-avatar image"
            src="https://www.gravatar.com/avatar/default?s=200&r=pg&d=mm"
          />
        </v-avatar>
        </v-col>
        <v-col
          cols="4"
          class="pa-0 ml-2"
          align-self="center"
        >
          <span style="font-size:1.2em">user name</span>
        </v-col>
        <v-spacer></v-spacer>
        <v-col
          class="mr-3"
          cols="3"
        >
          <follow-btn></follow-btn>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import FollowBtn from '@/components/Commons/FollowBtn.vue'

export default {
  components: { FollowBtn },

}
</script>

<style>
</style>