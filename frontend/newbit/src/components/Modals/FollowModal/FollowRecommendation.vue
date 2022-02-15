<template>
  <v-list two-line>
    <v-list-item
      v-for="(recommendedPerson, index) in recommendedPeople"
      :key="index"
      class="border mb-3 ml-10 firstlogin-follow"
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
          :isFollowed="isFollowed"
          :userCode="recommendedPerson.userCode"
        ></follow-btn>
      </v-list-item-action>
    </v-list-item>
  </v-list>
</template>

<script>
import axios from 'axios'
import FollowBtn from '@/components/Commons/FollowBtn.vue'


export default {
  props: {
    user: Object
  },
  components: { FollowBtn },
  data: () => ({
    recommendedPeople: [],
    isFollowed: false
  }),
  methods: {
    fetchFollowRecommendation (user_code) {
      axios({
        url: `${this.$serverURL}/follow/recommendation?uid=${user_code}`,
        method: 'get',
      })
        .then((res) => {
          this.recommendedPeople = res.data
        })
    },
  },
  created () {
    this.fetchFollowRecommendation(this.user.userCode)
  },

}
</script>

<style scope>
.border {
  border: 1px solid rgba(0, 0, 0, 0.1);
  border-radius: 15px;
}

.firstlogin-follow {
  width: 85%;
}
</style>