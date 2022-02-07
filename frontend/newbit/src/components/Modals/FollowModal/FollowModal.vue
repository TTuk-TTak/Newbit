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
      <v-list v-if="category === 'follower'">
        <v-list-item
          v-for="person in followers"
          :key="person.id"
        >
          <v-list-item-avatar>
            <v-img :src="person.profileUrl" />
          </v-list-item-avatar>

          <v-list-item-content>
            <v-list-item-title v-text="person.name"></v-list-item-title>
          </v-list-item-content>

          <v-list-item-action>
            <follow-btn></follow-btn>
          </v-list-item-action>
        </v-list-item>
      </v-list>
      <v-list v-else-if="category === 'following'">
        <v-list-item
          v-for="person in followings"
          :key="person.id"
        >
          <v-list-item-avatar>
            <v-img :src="person.profileUrl" />
          </v-list-item-avatar>

          <v-list-item-content>
            <v-list-item-title v-text="person.name"></v-list-item-title>
          </v-list-item-content>

          <v-list-item-action>
            <follow-btn></follow-btn>
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
  },

  data () {
    return {
      followers: [
        {
          profileUrl: 'https://www.gravatar.com/avatar/default?s=200&r=pg&d=mm',
          name: 'Harry',
        },
        {
          profileUrl: "https://www.gravatar.com/avatar/default?s=200&r=pg&d=mm",
          name: 'Kevin',
        },
        {
          profileUrl: "https://www.gravatar.com/avatar/default?s=200&r=pg&d=mm",
          name: 'John',
        },
        {
          profileUrl: "https://www.gravatar.com/avatar/default?s=200&r=pg&d=mm",
          name: 'Steve',
        },
        {
          profileUrl: "https://www.gravatar.com/avatar/default?s=200&r=pg&d=mm",
          name: 'Marry',
        },
        {
          profileUrl: "https://www.gravatar.com/avatar/default?s=200&r=pg&d=mm",
          name: 'Alex',
        },
      ],

      followings: [
        {
          profileUrl: 'https://www.gravatar.com/avatar/default?s=200&r=pg&d=mm',
          name: '홍길동',
        },
        {
          profileUrl: "https://www.gravatar.com/avatar/default?s=200&r=pg&d=mm",
          name: '홍길동',
        },
        {
          profileUrl: "https://www.gravatar.com/avatar/default?s=200&r=pg&d=mm",
          name: '홍길동',
        },
        {
          profileUrl: "https://www.gravatar.com/avatar/default?s=200&r=pg&d=mm",
          name: '홍길동',
        },
        {
          profileUrl: "https://www.gravatar.com/avatar/default?s=200&r=pg&d=mm",
          name: '홍길동',
        },
        {
          profileUrl: "https://www.gravatar.com/avatar/default?s=200&r=pg&d=mm",
          name: '홍길동',
        },
      ],
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
  }
}
</script>

<style>
</style>