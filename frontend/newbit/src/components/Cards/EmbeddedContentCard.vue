<template>
  <v-card
    v-if="content"
    class="ma-1 pt-3 pb-3" 
    outlined
  >
    <v-row>
      <v-col 
        class="py-0"
        cols=4 
        >
        <a href="#none">
          <v-img 
            class="embeddedContentCardImg rounded-l mb-0"
            height="100%"
            :src="content.contentImg ? content.contentImg : defaultImg"
          ></v-img>
        </a>
      </v-col>
      <v-col 
        class="align-between"
        cols=8>
        <v-row class="px-2 pt-3 pr-6">
          <v-col class='py-0' cols=12>
            <a href="#none" class="textDecoOff ma-0 pa-0">
              <h3
                class="text-truncate pa-0"
                @click="openContent()"
              >{{ content.contentTitle }}</h3>
            </a>
          </v-col>
          <v-col class='py-0 my-0 mt-1' cols=12>
            <a href="#none" class="textDecoOff ma-0 pa-0">
              <v-card-text 
                class="content-text mb-0 pa-0"
                @click="openContent()"
                >{{ content.contentText }}</v-card-text>
            </a>
          </v-col>
          <v-col class='pa-0 mt-0' cols=12>
          <v-chip-group
            v-if='renderChip'
            class="py-0 ml-2" 
          >
            <keyword-chip
              class="embeddedKeyword"
              v-for="(value, key) in chips.keywords"
              :key="key"
              :text="value"
              :isActive="false"
            >
            </keyword-chip>
            <hashtag
              v-for='i in chips.hashtags'
              :key='i'
              :text='i'
            ></hashtag>
          </v-chip-group>
          </v-col>
          <!-- 하단 블로그 소개 및 아이콘 -->
          <v-row
            class="pl-0 pt-0 ml-0 container justify-space-between blogInfo "
          >
            <div
              class="pl-3 mt-4 mb-3"
            >
              <v-avatar
                color="primary"
                size="20"
              >
                <img
                  :src="content.techblogImg"
                  @error="defaultBlogImg"
                  alt="John"
                  >
              </v-avatar>
              <span class="ml-2">{{ content.techblogName }}</span>
            </div>
            <div class="pr-4 pb-1 mt-2 pt-0">
              <v-btn
                @click="clickShareBtn()" 
                icon
                >
                <v-icon>mdi-autorenew</v-icon>
              </v-btn>
              <v-btn 
                @click="clickArchiveBtn()" 
                icon>
                <v-icon v-if="content.scrapped">mdi-bookmark</v-icon>
                <v-icon v-else>mdi-bookmark-outline</v-icon>
              </v-btn>        
              <v-btn 
                @click="clickLikeBtn()"
                icon>
                <v-icon v-if="content.liked">mdi-heart</v-icon>
                <v-icon v-else>mdi-heart-outline</v-icon>
              </v-btn>        
            </div>
          </v-row>
        </v-row>
      </v-col>
    </v-row>
  </v-card>
</template>

<script>
// import _ from 'lodash'
import axios from 'axios'
import { mapState, mapGetters } from 'vuex'
import KeywordChip from '@/components/Keyword/KeywordChip.vue'
import Hashtag from '@/components/Keyword/Hashtag.vue'

export default {
  name: 'EmbeddedContentCard',
  props: {
    content: Object
  },
  components: {
    KeywordChip,
    Hashtag,
  },
  data: () => ({
    defaultImg: 'https://cdn.vuetifyjs.com/images/cards/cooking.png', 
    chips: {},
    snackbar: {
      show: false,
      message: '',
      timeout: '1000'
    },
    renderChip: false
  }),
  computed: {
    ...mapState([
      'user',
    ]),

    ...mapGetters([
      'keywordDict',
    ]),
    parsedKeywords () {
      return this.$parseKeyword(this.content.contentKeyword)
    }
  },
  methods: {
    openContent: function () {
      window.open(this.content.contentUrl)
      axios({
        method: 'POST',
        url: `${this.$serverURL}/content/read`,
        data: {
          'uid': this.user.userCode,
          'cid': this.content.contentCode,
        },
      })
      .then((res) => {
        console.log('컨텐츠 읽음!', res)
        // 읽음 처리.
        this.content.read = true
      })
      .catch((err) => {
        console.log(err)
      })  
    },
    defaultBlogImg(e) {
      e.target.src = `https://cdn.vuetifyjs.com/images/john.jpg`
    },

    // 1. 아카이빙 버튼 클릭 
    clickArchiveBtn() {
      // 분기 1) 아카이빙 되지 않은 컨텐츠
      if (!this.content.scrapped) {
        this.archiveContent()
      // 분기 2) 아카아빙 된 컨텐츠
      } else {
        this.unarchiveContent()
      }
    },
    // 2. 좋아요 버튼 클릭
    clickLikeBtn() {
      if (!this.content.liked) {
        this.likeContent()
      // 분기 2) 아카아빙 된 컨텐츠
      } else {
        this.unlikeContent()
      }
    },
    // 3. 공유 버튼 클릭
    clickShareBtn() {
      if (this.user) {
        console.log(this.content.contentCode)
        const payload = {'contentCode': this.content.contentCode}
        console.log(payload)
        this.$store.dispatch('turnPostCreateModalOn', payload)
      }
    },
    // 컨텐츠 아카이브 요청
    archiveContent() {
      axios({
        method: 'POST',
        url: `${this.$serverURL}/content/scrap`,
        data: {
          'uid': this.user.userCode,
          'cid': this.content.contentCode,
        },
      })
      .then((res) => {
        console.log('archived', res)
        if (res.data === 'success') {
          this.content.scrapped = true
          const snackbarText = '컨텐츠를 아카이빙했습니다.'
          this.$store.dispatch('turnSnackBarOn', snackbarText)
        }
      })
      .catch((err) => {
        console.log(err)
      })  
    },
    // 컨텐츠 아카이브 취소 요청
    unarchiveContent() {
      axios({
        method: 'DELETE',
        url: `${this.$serverURL}/content/scrap?`
              + `uid=${this.user.userCode}`
              + `&cid=${this.content.contentCode}`,
      })
      .then((res) => {
        console.log('unarchived', res)
        if (res.data === 'success') {
          this.content.scrapped = false
          const snackbarText = '컨텐츠 아카이빙을 취소했습니다.'
          this.$store.dispatch('turnSnackBarOn', snackbarText)
        }
      })
      .catch((err) => {
        console.log(err)
      })
    },
    // 컨텐츠 좋아요 요청
    likeContent() {
      axios({
        method: 'POST',
        url: `${this.$serverURL}/content/like`,
        data: {
          'uid': this.user.userCode,
          'cid': this.content.contentCode,
        },
      })
      .then((res) => {
        console.log('likedContent', res)
        if (res.data === 'success') {
          const snackbarText = '컨텐츠를 좋아요 했습니다.'
          this.$store.dispatch('turnSnackBarOn', snackbarText)
          this.content.liked = true
          this.content.contentLike ++
        }
      })
      .catch((err) => {
        console.log(err)
      })  
    },
    // 컨텐츠 좋아요 취소 요청
    unlikeContent() {
      axios({
        method: 'DELETE',
        url: `${this.$serverURL}/content/like?`
              + `uid=${this.user.userCode}`
              + `&cid=${this.content.contentCode}`,
      })
      .then((res) => {
        console.log('unliked', res)
        if (res.data === 'success') {
          const snackbarText = '컨텐츠 좋아요를 취소했습니다.'
          this.$store.dispatch('turnSnackBarOn', snackbarText)
          this.content.liked = false
          this.content.contentLike --
        }
      })
      .catch((err) => {
        console.log(err)
      })
    },


    makeKeywordChip: function () {
      const chips = {keywords:{}, hashtags:{}}
      for (let keyword of this.parsedKeywords) {
        if (keyword in this.keywordDict) {
          chips.keywords[keyword] = this.keywordDict[keyword]
        } else {
          chips.hashtags[keyword] = keyword
        }
      }
      this.chips = chips
      this.renderChip = true
    }
  },
  watch: {
    content: {
      immediate: true,
      handler () {
        if (this.content) {
          this.makeKeywordChip()
        }
      },
    }
  },
}
</script>

<style scope>
.content-text {
  font-family: 'KoPub Dotum';
  font-weight: 400;
  color : #272727;
  -webkit-line-clamp: 3;
  -webkit-box-orient: vertical;
  overflow: hidden;
}

/* 키워드칩 */
.embeddedKeyword {
  font-family: 'KoPub Dotum';
  font-weight: 400;
}

/* 블로그명 */
.blogInfo {
  font-family: 'KoPub Dotum';
  font-weight: 400;
  color : #7E7E7E;
  -webkit-line-clamp: 3;
  -webkit-box-orient: vertical;
  overflow: hidden;
}

.textDecoOff {
  text-decoration: none;
}

.embeddedContentCardImg {
  border-right: 1px solid rgb(221, 221, 221);
}

</style>