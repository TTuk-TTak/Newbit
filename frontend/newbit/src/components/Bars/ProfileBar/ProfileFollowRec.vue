<template>
  <div>
    <h2 class="ml-3 mb-2">팔로우 추천</h2>
    <v-divider></v-divider>
    <v-list
      two-line
      class="py-0 profileBar"
    >
      <v-list-item
        v-for="(recommendedPerson, index) in recommendedPeople"
        :key="index"
      >
        <v-list-item-avatar>
          <v-img
            v-if="recommendedPerson.userImg"
            class="v-avatar image"
            :src="recommendedPerson.userImg"
          />
          <v-img
            v-else
            class="v-avatar image"
            src="https://www.gravatar.com/avatar/default?s=200&r=pg&d=mm"
          />
        </v-list-item-avatar>

        <v-list-item-content>
          <v-list-item-title v-text="recommendedPerson.userNick"></v-list-item-title>

          <v-list-item-subtitle v-text="`@${recommendedPerson.userId}`"></v-list-item-subtitle>
        </v-list-item-content>

        <v-list-item-action>
          <follow-btn
            :userNick="recommendedPerson.userNick"
            :isFollowed="isFollowed"
            :userCode="recommendedPerson.userCode"
          ></follow-btn>
        </v-list-item-action>
      </v-list-item>
    </v-list>
  </div>
</template>

<script>
import { mapState } from 'vuex'
import FollowBtn from '@/components/Commons/FollowBtn.vue'


export default {
  components: { FollowBtn },
  data: () => ({
    isFollowed: false
  }),
  methods: {

  },
  computed: {
    ...mapState([
      'recommendedPeople',
    ])
  },
}
</script>
<style scoped>
.profileBar {
  background-color: #f2f2f4;
}
</style>