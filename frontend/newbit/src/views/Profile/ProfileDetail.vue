<template>
  <v-container class="pa-10">
    <v-row>
      <v-col
        cols="2"
        align-self="center"
      >
        <v-img
          class="v-avatar image"
          src="https://www.gravatar.com/avatar/default?s=200&r=pg&d=mm"
        />
      </v-col>
      <v-col
        cols="8"
        align-self="center"
      >
        <p>개발자 천모씨</p>
        <p>재밌게 협업 진행해보고 싶습니다. 자기소개 하는 부분.</p>
        <div>
          <span class="mr-6">게시물</span>
          <span class="mr-6">팔로워</span>
          <span>팔로잉</span>
        </div>

      </v-col>
      <v-col
        cols="2"
        align-self="center"
      >
        <v-btn
          rounded
          block
        >팔로잉</v-btn>
      </v-col>
    </v-row>
    <favored-keyword-bar :is-vertical="isVertical"></favored-keyword-bar>
    <v-tabs>
      <v-tabs-slider color="primary"></v-tabs-slider>
      <v-tab @click="changeToArticle">게시물</v-tab>
      <v-tab @click="changeToActivity">내 활동</v-tab>
    </v-tabs>
    <v-divider></v-divider>
    <div v-if="toggle ==='article'">
      내가 쓴 게시물 나열
    </div>
    <div v-else-if="toggle ==='activity'">
      <profile-detail-daily-graph></profile-detail-daily-graph>
      <v-row
        align="center"
        justify="space-around"
      >
        <v-col cols="5">
          <profile-detail-radar-graph></profile-detail-radar-graph>
        </v-col>
        <v-col cols="5">
          <v-sheet>
            <v-list>
              <div class="d-flex justify-space-between px-4">
                <v-text class="font-weight-bold">키워드</v-text>
                <v-text class="font-weight-bold">관심도</v-text>
              </div>

              <v-divider></v-divider>
              <v-list-item
                v-for="item in items"
                :key="item.category"
                class="p"
              >
                <v-list-item-content>
                  <v-list-item-title v-text="item.category"></v-list-item-title>
                </v-list-item-content>

                <v-list-item-content>
                  <v-list-item-title
                    class="d-flex justify-end pr-5"
                    v-text="item.preference"
                  ></v-list-item-title>
                </v-list-item-content>

              </v-list-item>
            </v-list>
          </v-sheet>
        </v-col>
      </v-row>
    </div>
  </v-container>
</template>

<script>
import FavoredKeywordBar from '@/components/Keyword/FavoredKeywordBar.vue'
import ProfileDetailRadarGraph from '@/views/Profile/Detail/ProfileDetailRadarGraph.vue'
import ProfileDetailDailyGraph from '@/views/Profile/Detail/ProfileDetailDailyGraph.vue'


export default {
  components: {
    FavoredKeywordBar,
    ProfileDetailRadarGraph,
    ProfileDetailDailyGraph,
  },
  data: () => ({
    isVertical: false,
    toggle: 'article',

    items: [
      { category: '프론트엔드', preference: 8 },
      { category: '모바일', preference: 1 },
      { category: '알고리즘', preference: 9 },
      { category: '백엔드', preference: 3 },
      { category: '데이터', preference: 2 },
      { category: 'AI', preference: 6 },
    ],

  }),
  methods: {
    changeToArticle () {
      if (this.toggle === 'activity') {
        this.toggle = 'article'
      }
    },
    changeToActivity () {
      if (this.toggle === 'article') {
        this.toggle = 'activity'
      }

    }
  }
}
</script>

<style>
</style>