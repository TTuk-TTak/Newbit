<template>
  <div>
    <v-tabs
      class="backgroundDark"
    >
    <!-- 2. 추천 피드  -->
    <v-tab
      @click="clickContent()"
      class="backgroundDark"
      :ripple='false'
      text
      >
      추천피드
    </v-tab>

  <!-- 1. 소셜피드 -->
    <v-tooltip
      :disabled='user ? true : false '
      bottom>
      <template v-slot:activator="{ on, attrs }">
        <v-tab
          @click='clickSocial()'
          class="backgroundDark"
          :ripple='false'
          text
          v-bind="attrs"
          v-on="on"
          >
        소셜 피드
        </v-tab>
      </template>
      <span>로그인이 필요한 기능입니다.</span>
    </v-tooltip>




    <v-tooltip
      :disabled='user ? true : false '
      bottom>
      <template v-slot:activator="{ on, attrs }">
      <v-tab
        @click="clickArchive()"
        class="backgroundDark"
        :ripple='false'
        v-bind="attrs"
        v-on="on"
        text
        >
        아카이빙
      </v-tab>
      </template>
      <span>로그인이 필요한 기능입니다.</span>
    </v-tooltip>
    </v-tabs>
  </div>
</template>

<script>
import _ from 'lodash'
import { mapState } from 'vuex'

export default {
  name: 'TheNavBarMenu',
  components: {
  },
  computed: {
    ...mapState([
      'user',
    ])
  },
  methods: {
    clickSocial() {
      if (this.user && !_.startsWith('/social',this.$route.path, 0)) {
        this.$goToSocialFeed()
      } else if (this.user && _.startsWith('/social',this.$route.path, 0)) {
        this.$store.dispatch('socialFeedLoadedAt')
      }
    },
    clickContent() {
      if (!_.startsWith('/content',this.$route.path, 0)) {
        this.$goToCurationFeed()
      } else {
        this.$store.dispatch('contentFeedLoadedAt')
      }
    },
    clickArchive() {
      if (this.user && !_.startsWith('/archive',this.$route.path, 0)) {
        this.$goToArchivingFeed()
      } else if (this.user && _.startsWith('/archive',this.$route.path, 0)) {
        this.$store.dispatch('archivingFeedLoadedAt')
        console.log('archive에서 눌렀을 때 누르면 새로고침하는 함수')
      }


    }
  },

}
</script>

<style scope>
.v-tab.v-tab  {
  font-family: 'KoPub Dotum';
  font-weight: 500;
}
</style>