<template>
  <div>
    <h3>팔로우 추천</h3>
    <v-divider></v-divider>
    <v-container>
      <v-row
        v-for="n in 4"
        :key="n"
      >
        <v-col
          cols="3"
          align-self="center"
          class="pa-2"
        >
          <v-img
            class="v-avatar"
            src="https://www.gravatar.com/avatar/default?s=200&r=pg&d=mm"
          ></v-img>
        </v-col>
        <v-col
          cols="3"
          class="pa-0"
          align-self="center"
        >
          <span>hello</span>
        </v-col>
        <v-col
          cols="4"
          offset="2"
          align-self="center"
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