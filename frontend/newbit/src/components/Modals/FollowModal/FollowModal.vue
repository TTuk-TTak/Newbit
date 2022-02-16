<template>
  <v-card>
    <v-card-title class="d-flex justify-space-between">
      <div v-if="category === 'follower'">
        팔로워
      </div>
      <div v-else-if="category === 'following'">
        팔로잉
      </div>
      <div>
        <v-btn
          icon
          right
          @click="onClick"
        >
          <v-icon>mdi-close</v-icon>
        </v-btn>
      </div>
    </v-card-title>

    <v-divider></v-divider>

    <v-card-text>
      <v-list
        v-if="category === 'follower'"
        two-line
      >
        <v-list-item
          v-for="person in follower_list_origin"
          :key="person.id"
        >
          <v-list-item-avatar>
            <v-img :src="person.userImg" />
          </v-list-item-avatar>

          <v-list-item-content>
            <v-list-item-title v-text="person.userNick"></v-list-item-title>
            <v-list-item-subtitle v-text="`@${person.userId}`"></v-list-item-subtitle>
          </v-list-item-content>

          <v-list-item-action>
            <follow-btn
              :userNick="person.userNick"
              v-if="myUserCode != person.userCode"
              :isFollowwed="following_list.includes(person.userCode)"
              :userCode="person.userCode"
            ></follow-btn>
          </v-list-item-action>
        </v-list-item>
      </v-list>
      <v-list
        v-else-if="category === 'following'"
        two-line
      >
        <v-list-item
          v-for="person in following_list_origin"
          :key="person.id"
        >
          <v-list-item-avatar>
            <v-img :src="person.userImg" />
          </v-list-item-avatar>

          <v-list-item-content>
            <v-list-item-title v-text="person.userNick"></v-list-item-title>
            <v-list-item-subtitle v-text="`@${person.userId}`"></v-list-item-subtitle>
          </v-list-item-content>

          <v-list-item-action>
            <follow-btn
              :userNick="person.userNick"
              v-if="myUserCode != person.userCode"
              :isFollowed="following_list.includes(person.userCode)"
              :userCode="person.userCode"
            ></follow-btn>
          </v-list-item-action>
        </v-list-item>
      </v-list>
    </v-card-text>
  </v-card>
</template>

<script>
import FollowBtn from '@/components/Commons/FollowBtn.vue'

export default {
  components: { FollowBtn },
  name: 'FollowModal',
  props: {
    dialog1: Boolean,
    dialog2: Boolean,
    category: String,
    following_list: Array,
    follower_list_origin: Array,
    following_list_origin: Array,
    myUserCode: Number
  },

  data () {
    return {

    }
  },

  methods: {
    onClick () {
      if (this.category === 'follower') {
        this.dialog1 = false
        this.$emit('props-status-change', this.dialog1, this.category)
      }
      else if (this.category === 'following') {
        this.dialog2 = false
        this.$emit('props-status-change', this.dialog2, this.category)
      }
    }
  },
}
</script>

<style>
</style>