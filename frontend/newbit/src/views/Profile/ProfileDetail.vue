<template>
  <v-card>
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
          <div>{{ user.userNick }}</div>
          <div>{{ user.userIntro }}</div>
          <div>
            <v-btn
              elevation="0"
              v-bind="attrs"
              v-on="on"
              plain
              rounded
              shaped
            >
              게시물
            </v-btn>
            <v-dialog
              v-model="dialog1"
              width="500"
            >
              <template v-slot:activator="{ on, attrs }">
                <v-btn
                  elevation="0"
                  v-bind="attrs"
                  v-on="on"
                  plain
                  rounded
                  shaped
                >
                  팔로워
                </v-btn>
              </template>
              <follow-modal
                :dialog1="dialog1"
                @props-status-change="onClickChange"
                category="follower"
              ></follow-modal>
            </v-dialog>
            <v-dialog
              v-model="dialog2"
              width="500"
            >
              <template v-slot:activator="{ on, attrs }">
                <v-btn
                  elevation="0"
                  v-bind="attrs"
                  v-on="on"
                  plain
                  rounded
                  shaped
                >
                  팔로잉
                </v-btn>
              </template>
              <follow-modal
                :dialog2="dialog2"
                @props-status-change="onClickChange"
                category="following"
              ></follow-modal>
            </v-dialog>
          </div>
        </v-col>
        <v-col
          v-if="$route.params.userCode != $store.state.user.userCode"
          cols="2"
          align-self="center"
        >
          <v-btn
            v-if="following_list.includes(Number($route.params.userCode))"
            rounded
            block
            @click="[$unFollow($route.params.userCode), popFollow(Number($route.params.userCode))]"
          >팔로우 취소</v-btn>
          <v-btn
            v-else
            rounded
            block
            @click="[$follow($route.params.userCode), pushFollow(Number($route.params.userCode))]"
          >
            팔로우
          </v-btn>
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
  </v-card>
</template>

<script>
import FavoredKeywordBar from '@/components/Keyword/FavoredKeywordBar.vue'
import ProfileDetailRadarGraph from '@/views/Profile/Detail/ProfileDetailRadarGraph.vue'
import ProfileDetailDailyGraph from '@/views/Profile/Detail/ProfileDetailDailyGraph.vue'
import FollowModal from '@/components/Modals/FollowModal/FollowModal.vue'
import axios from 'axios'


const myUserCode = localStorage.getItem('user_code')


export default {
  components: {
    FavoredKeywordBar,
    ProfileDetailRadarGraph,
    ProfileDetailDailyGraph,
    FollowModal,
  },
  data: () => ({
    isVertical: false,
    toggle: 'article',

    dialog1: false,
    dialog2: false,

    user: {},
    following_list: [],

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
    },
    onClickChange (changedStatus, category) {
      if (category === 'follower') {
        this.dialog1 = changedStatus
      }
      else if (category === 'following') {
        this.dialog2 = changedStatus
      }
    },
    fetchUserInformation (user_code) {
      if (this.$route.params.userCode !== myUserCode) {
        axios({
          url: `${this.$serverURL}/user?uid=${user_code}`,
          method: 'get',
        })
          .then((res) => {
            this.user = res.data
          })
      }
      else {
        this.user = this.$store.state.user
      }
    },
    fetchUserFollowingList (user_code) {
      axios({
        url: `${this.$serverURL}/follow/following?uid=${user_code}`,
        method: 'get',
      })
        .then((res) => {
          this.following_list = res.data.map((object) => {
            return object['userCode']
          })
        })
    },
    pushFollow (user) {
      this.following_list.push(user)
    },
    popFollow () {
      this.following_list.pop()
    }
  },
  created () {
    if (this.$route.params.userCode !== myUserCode) {
      this.fetchUserInformation(this.$route.params.userCode)
    }
    this.fetchUserFollowingList(myUserCode)
  }
}
</script>

<style>
</style>