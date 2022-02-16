<template>
  <v-card>
    <v-container class="pa-10">
      <v-row>
        <v-col
          cols="2"
          align-self="center"
        >
          <v-img
            v-if="user.userImg"
            class="v-avatar image"
            :src="user.userImg"
          />
          <v-img
            v-else
            class="v-avatar image"
            src="https://www.gravatar.com/avatar/default?s=200&r=pg&d=mm"
          />
        </v-col>
        <v-col
          cols="7"
          align-self="center"
        >
          <span class="text-h6 font-weight-bold">{{ user.userNick }}</span>
          <span class="text-h7 grey--text font-weight-bold"> {{ `@${user.userId}` }}</span>
          <div class="text-subtitle-1 font-weight-bold">{{ user.userIntro }}</div>
          <div>
            <v-btn
              class="font-weight-black"
              elevation="0"
              plain
              rounded
              shaped
            >
              게시물 {{ user.userPostCount }}
            </v-btn>
            <v-dialog
              v-model="dialog1"
              width="500"
            >
              <template v-slot:activator="{ on, attrs }">
                <v-btn
                  class="font-weight-black"
                  elevation="0"
                  v-bind="attrs"
                  v-on="on"
                  plain
                  rounded
                  shaped
                  @click="openFollowerList($route.params.userCode)"
                >
                  팔로워 {{ user.userFollowerCount }}
                </v-btn>
              </template>
              <follow-modal
                :myUserCode="myUserCode"
                :following_list="following_list"
                :follower_list_origin="follower_list_origin"
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
                  class="font-weight-black"
                  elevation="0"
                  v-bind="attrs"
                  v-on="on"
                  plain
                  rounded
                  shaped
                  @click="openFollowingList($route.params.userCode)"
                >
                  팔로잉 {{ user.userFollowingCount }}
                </v-btn>
              </template>
              <follow-modal
                :myUserCode="myUserCode"
                :following_list="following_list"
                :following_list_origin="following_list_origin"
                :dialog2="dialog2"
                @props-status-change="onClickChange"
                category="following"
              ></follow-modal>
            </v-dialog>
          </div>
        </v-col>
        <v-col
          v-if="$route.params.userCode != myUserCode"
          cols="2"
          align-self="center"
        >
          <v-btn
            v-if="following_list.includes(Number($route.params.userCode))"
            rounded
            depressed
            outlined
            block
            @click="[$unFollow($route.params.userCode, user.userNick), popFollow(Number($route.params.userCode)), minusOne()]"
          >언팔로우</v-btn>
          <v-btn
            v-else
            rounded
            depressed
            outlined
            block
            @click="[$follow($route.params.userCode, user.userNick), pushFollow(Number($route.params.userCode)), plusOne()]"
          >
            팔로우
          </v-btn>
        </v-col>
        <v-col
          v-else
          cols="2"
          align-self="center"
        >
          <v-btn
            rounded
            depressed
            outlined
            block
            @click="$goToProfileEdit()"
          >프로필 수정</v-btn>
        </v-col>
      </v-row>
      <favored-keyword-bar2 :parsed="$parseKeyword(user.userKeyword)"></favored-keyword-bar2>
      <v-tabs>
        <v-tabs-slider color="primary"></v-tabs-slider>
        <v-tab @click="changeToArticle">게시물</v-tab>
        <v-tab @click="changeToActivity">내 활동</v-tab>
      </v-tabs>
      <v-divider></v-divider>
      <div
        v-if="toggle ==='article'"
        class="mt-9"
      >
        <!-- SocialFeed.vue code 복붙 -->
        <v-row
          class="pa-2 px-4 fill-height"
          align='start'
        >
          <v-row
            class="pt-2"
            justify='center'
            align='start'
            id='socialFeed'
          >
            <v-col
              v-for="(post, index) in posts"
              :key="`social` + index"
              class="pa-1 pb-1"
              cols=12
            >
              <post-card :post='post'></post-card>
            </v-col>
          </v-row>

          <v-row class="mt-5 pt-5 justify-self-center align-self-end">
            <v-spacer></v-spacer>
            <infinite-loading
              v-if='user'
              class="mt-5 pt-5 justify-self-center align-self-center"
              @infinite="infiniteHandler"
            >
              <template slot="no-more">
                2022 - Newbit
              </template>
            </infinite-loading>
            <v-spacer></v-spacer>
          </v-row>
        </v-row>
      </div>
      <div v-else-if="toggle ==='activity'">
        <profile-detail-daily-graph :week="week"></profile-detail-daily-graph>
        <v-row
          align="center"
          justify="space-around"
        >
          <v-col cols="5">
            <profile-detail-radar-graph
              :category="matchArray(radarGraphData.category)"
              :preference="radarGraphData.preference"
            ></profile-detail-radar-graph>
          </v-col>
          <v-col cols="5">
            <v-sheet>
              <v-list>
                <div class="d-flex justify-space-between px-4">
                  <div class="font-weight-bold">키워드</div>
                  <div class="font-weight-bold">관심도</div>
                </div>

                <v-divider></v-divider>
                <v-list-item
                  v-for="item in changedRadarGraphData"
                  :key="item.category"
                >
                  <v-list-item-content>
                    <v-list-item-title v-text="matchName(item.category)"></v-list-item-title>
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
import axios from 'axios'
import _ from 'lodash'
import InfiniteLoading from 'vue-infinite-loading'
import { mapGetters } from 'vuex'

import FavoredKeywordBar2 from '@/components/Keyword/FavoredKeywordBar2.vue'
import ProfileDetailRadarGraph from '@/views/Profile/Detail/ProfileDetailRadarGraph.vue'
import ProfileDetailDailyGraph from '@/views/Profile/Detail/ProfileDetailDailyGraph.vue'
import FollowModal from '@/components/Modals/FollowModal/FollowModal.vue'
import PostCard from '@/components/Cards/PostCard.vue'


const myUserCode = localStorage.getItem('user_code')


export default {
  components: {
    FavoredKeywordBar2,
    ProfileDetailRadarGraph,
    ProfileDetailDailyGraph,
    FollowModal,
    InfiniteLoading,
    PostCard,
  },
  data: () => ({
    toggle: 'article',

    myUserCode: myUserCode,

    dialog1: false,
    dialog2: false,

    user: {},
    following_list: [],
    following_list_origin: [],
    follower_list_origin: [],

    page: 1,
    posts: [],
    lastPostCode: 0,

    radarGraphData: {
      category: [],
      preference: [],
    },
    daily_data: {},
    changedRadarGraphData: [],
    week: {
      mon: '',
      tue: '',
      wed: '',
      thu: '',
      fri: '',
      sat: '',
      sun: ''
    }
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
      axios({
        url: `${this.$serverURL}/user?uid=${user_code}`,
        method: 'get',
      })
        .then((res) => {
          this.user = res.data

        })
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
    openFollowingList (user_code) {
      axios({
        url: `${this.$serverURL}/follow/following?uid=${user_code}`,
        method: 'get',
      })
        .then((res) => {
          this.following_list_origin = res.data
        })
    },
    openFollowerList (user_code) {
      axios({
        url: `${this.$serverURL}/follow/follower?uid=${user_code}`,
        method: 'get',
      })
        .then((res) => {
          this.follower_list_origin = res.data
        })
    },
    pushFollow (user) {
      this.following_list.push(user)
    },
    popFollow () {
      this.following_list.pop()
    },
    plusOne () {
      this.user.userFollowerCount += 1
    },
    minusOne () {
      this.user.userFollowerCount -= 1
    },
    // SocialFeed.vue 함수 복붙
    infiniteHandler ($state) {
      const size = 8
      axios({
        method: 'get',
        url: `${this.$serverURL}/post/user?`
          + `uid=${myUserCode}`
          + `&userid=${this.$route.params.userCode}`
          + `&lastpostcode=${this.lastPostCode}`
          + `&size=${size}`,
      })
        .then(res => {
          if (res.data.length !== 0) {
            this.page += 1;
            this.lastPostCode = _.last(res.data).postCode
            for (let key in res.data) {
              this.posts.push(res.data[key])
            }

            $state.loaded();
          } else {
            $state.complete();
          }
        })
        .catch((err) => {
          console.log(err)
        })
    },
    matchName (string) {
      let found = false
      for (let keyword in this.keywordDict) {
        if (string === keyword) {
          found = true
          return this.keywordDict[keyword]
        }
      }
      if (!found) {
        return string
      }
    },
    matchArray (keywords) {
      return keywords.map((keyword) => {
        if (this.matchName(keyword)) {
          return this.matchName(keyword)
        }
        else {
          return keyword
        }
      })
    },
    fetchRadarGraphData (user_code) {
      axios({
        url: `${this.$serverURL}/graph/radar?uid=${user_code}`,
        method: 'get',
      })
        .then((res) => {
          this.radarGraphData.category = res.data.category
          this.radarGraphData.preference = res.data.data
          this.changedRadarGraphData = this.radardataChange(this.radarGraphData)
        })
    },
    fetchDailyGraphData (user_code) {
      axios({
        url: `${this.$serverURL}/graph/daily?uid=${user_code}`,
        method: 'get',
      })
        .then((res) => {
          this.daily_data = res.data
          const maxLength = this.dailydataLength(this.daily_data)
          for (let day in this.daily_data) {
            if (day === "mon") {
              this.week.mon = this.dailydataChange(this.daily_data[day])
              if (this.daily_data[day].length < maxLength) {
                this.week.mon.push({ 'x': maxLength, 'y': 0 })
              }
            }
            else if (day === "tue") {
              this.week.tue = this.dailydataChange(this.daily_data[day])
              if (this.daily_data[day].length < maxLength) {
                this.week.tue.push({ 'x': maxLength, 'y': 0 })
              }
            }
            else if (day === "wed") {
              this.week.wed = this.dailydataChange(this.daily_data[day])
              if (this.daily_data[day].length < maxLength) {
                this.week.wed.push({ 'x': maxLength, 'y': 0 })
              }
            }
            else if (day === "thu") {
              this.week.thu = this.dailydataChange(this.daily_data[day])
              if (this.daily_data[day].length < maxLength) {
                this.week.thu.push({ 'x': maxLength, 'y': 0 })
              }
            }
            else if (day === "fri") {
              this.week.fri = this.dailydataChange(this.daily_data[day])
              if (this.daily_data[day].length < maxLength) {
                this.week.fri.push({ 'x': maxLength, 'y': 0 })
              }
            }
            else if (day === "sat") {
              this.week.sat = this.dailydataChange(this.daily_data[day])
              if (this.daily_data[day].length < maxLength) {
                this.week.sat.push({ 'x': maxLength, 'y': 0 })
              }
            }
            else if (day === "sun") {
              this.week.sun = this.dailydataChange(this.daily_data[day])
              if (this.daily_data[day].length < maxLength) {
                this.week.sun.push({ 'x': maxLength, 'y': 0 })
              }
            }
          }
        })
    },
    radardataChange (object) {
      return object['category'].map((e, idx) => {
        return { 'category': e, 'preference': object['preference'][idx] }
      })
    },
    dailydataChange (array) {
      return array.map((value, idx) => {
        return { 'x': idx + 1, 'y': value }
      })
    },
    dailydataLength (object) {
      let long = 0
      for (let key in object) {
        if (object[key].length > long) {
          long = object[key].length
        }
      }
      return long
    }
  },
  computed: {
    ...mapGetters([
      'keywordDict',
    ]),
  },
  created () {
    this.fetchUserInformation(this.$route.params.userCode)
    this.fetchUserFollowingList(myUserCode)
    this.fetchRadarGraphData(this.$route.params.userCode)
    this.fetchDailyGraphData(this.$route.params.userCode)
  }
}

</script>

<style>
</style>