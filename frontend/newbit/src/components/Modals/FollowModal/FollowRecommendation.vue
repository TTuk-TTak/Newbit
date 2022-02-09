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
import FollowBtn from '@/components/Commons/FollowBtn.vue'


export default {
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
      {
        color: 'amber',
        icon: 'mdi-gesture-tap-button',
        subtitle: 'Jan 10, 2014',
        title: 'Kitchen remodel',
      },
      {
        color: 'amber',
        icon: 'mdi-gesture-tap-button',
        subtitle: 'Jan 10, 2014',
        title: 'Kitchen remodel',
      },
      {
        color: 'amber',
        icon: 'mdi-gesture-tap-button',
        subtitle: 'Jan 10, 2014',
        title: 'Kitchen remodel',
      },
      {
        color: 'amber',
        icon: 'mdi-gesture-tap-button',
        subtitle: 'Jan 10, 2014',
        title: 'Kitchen remodel',
      },
      {
        color: 'amber',
        icon: 'mdi-gesture-tap-button',
        subtitle: 'Jan 10, 2014',
        title: 'Kitchen remodel',
      },

    ],
  })
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