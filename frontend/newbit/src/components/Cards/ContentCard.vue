<template>
  <v-card
    outlined
    class="mx-auto mt-1 contentCard-Item"
    style="position: relative; "
  >
    <!-- 상단 이미지 -->
    <v-img
      max-height="170"
      :src="`${content.contentImg}`"
      @click="selectContent()"
    ></v-img>
    <!-- 1. 컨텐츠 타이틀 -->
    <v-card-title
      class="py-1 mt-2"
      @click="selectContent()"
    >{{ content.contentTitle }}</v-card-title>
    <!-- 컨텐츠 본문 -->
    <v-card-text 
      class="px-4 mb-0 pb-0"
      @click="selectContent()"
      >
      <div class="content-text">{{ content.contentText }}</div>
    </v-card-text>
    <!-- 키워드 -->
    <v-card-text
      class="pt-2 pl-3 pb-0"
    >
      <v-chip-group
        v-if='renderChip'
        column
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
    </v-card-text>
    <!-- 하단 소개 및 공유버튼 -->
    <v-card-actions
      class='pt-0 justify-space-between'
    >
      <div class="pl-3 mt-0">
        <v-avatar
          rounded
          size="20"
        >
          <v-img :src="content.techblogImg"></v-img>
        </v-avatar>
        <span class="ml-2">{{ content.techblogName }}</span>
      </div>

      <div>
        <v-btn 
          @click="clickShareBtn()"
          icon>
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
    </v-card-actions>
  </v-card>
</template>

<script>
// 3rd party
import axios from 'axios'
// Vue
import { mapState, mapGetters } from 'vuex'
// Local
import KeywordChip from '@/components/Keyword/KeywordChip.vue'
import Hashtag from '@/components/Keyword/Hashtag.vue'

export default {
  name: 'ContentCard',
  components: {
    KeywordChip,
    Hashtag,
  },
  data() {
    return {
      isVertical: true,
      chips: null,
      renderChip: false,
    }
  },
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
  props: {
    content: Object
  },
  methods: {
    selectContent(){
      window.open(this.content.contentUrl);
      // 컨텐츠가 열릴 때 '읽음 처리' 요청을 함게 보냄. 
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
      console.log(this.content.contentCode)
      const payload = {'contentCode': this.content.contentCode}
      console.log(payload)
      this.$store.dispatch('turnPostCreateModalOn', payload)
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
          this.content.liked = true
          this.content.contentLike ++
        }
      })
      .catch((err) => {
        console.log(err)
      })  
    },
    // 컨텐츠 아카이브 취소 요청
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
  created () {
    this.makeKeywordChip()
  }
}
</script>

<style>
.content-text {
  display: -webkit-box;
  -webkit-line-clamp: 3;
  -webkit-box-orient: vertical;
  overflow: hidden;
}

/* 콘텐츠 카드 마우스 오버 애니메이션 - 필요없으시면 빼고 사용해주세요!!! */
.contentCard-Item:hover {
  box-shadow: 1px 1px 10px 3px rgb(230, 230, 230);
  transform: scale( 1.01 );
}

</style>