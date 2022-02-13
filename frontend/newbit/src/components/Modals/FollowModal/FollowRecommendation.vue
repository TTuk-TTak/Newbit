<template>
  <v-list two-line>
    <v-list-item
      v-for="file in files"
      :key="file.title"
      class="border mb-3 ml-10 firstlogin-follow"
    >
      <v-list-item-avatar>
        <v-icon
          :class="file.color"
          dark
          v-text="file.icon"
        ></v-icon>
      </v-list-item-avatar>

      <v-list-item-content>
        <v-list-item-title v-text="file.title"></v-list-item-title>

        <v-list-item-subtitle v-text="file.subtitle"></v-list-item-subtitle>
      </v-list-item-content>

      <v-list-item-action>
        <follow-btn></follow-btn>
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
    files: [
      {
        color: 'blue',
        icon: 'mdi-clipboard-text',
        subtitle: 'Jan 20, 2014',
        title: 'Vacation itinerary',
      },
      {
        color: 'amber',
        icon: 'mdi-gesture-tap-button',
        subtitle: 'Jan 10, 2014',
        title: 'Kitchen remodel',
      },
    ],
  }),
  methods: {
    fetchFollowRecommendation (user_code) {
      axios({
        url: `${this.$serverURL}/follow/recommendation?uid=${user_code}`,
        method: 'get',
      })
        .then((res) => {
          console.log(res)
        })
    }
  },
  created () {
    this.fetchFollowRecommendation(this.user.userCode)
  }
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