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
        <v-img 
          class="rounded-l mb-0"
          height="100%"
          :src="content.contentImg ? content.contentImg : defaultImg"
        ></v-img>
      </v-col>
      <v-col 
        class="align-between"
        cols=8>
        <v-row class="px-2 pt-3">
          <v-col class='py-0' cols=12>
            <h3
              class="pa-0"
              @click="openContent()"
            >{{ content.contentTitle }}</h3>
          </v-col>
          <v-col class='py-0 my-0 mt-1' cols=12>
            <v-card-text 
              class="content-text mb-0 pa-0"
              @click="openContent()"
              >{{ content.contentText }}</v-card-text>
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
                rounded
                size="20"
              >
                <img
                  :src="content.techBlogImg"
                  @error="defaultBlogImg"
                  alt="John"
                  >
              </v-avatar>
              <span class="ml-2">{{ content.techBlogName }}</span>
            </div>
            <div class="pr-4 pb-1 mt-2 pt-0">
              <v-btn 
                icon
                @click="copyLink()"
                >
                <v-icon>mdi-share</v-icon>
              </v-btn>
              <v-btn icon>
                <v-icon>mdi-cards-heart-outline</v-icon>
              </v-btn>        
            </div>
          </v-row>
        </v-row>
      </v-col>
    </v-row>
    <!-- 링크 복사 및 좋아요 팝업 -->
    <v-snackbar
      v-model="snackbar.show"
      :timeout="snackbar.timeout"
    >
      {{ snackbar.message }}
      <template v-slot:action="{ attrs }">
        <v-btn
          color="blue"
          text
          v-bind="attrs"
          @click="snackbar = false"
        >
          Close
        </v-btn>
      </template>
    </v-snackbar>
  </v-card>
</template>

<script>
// import _ from 'lodash'
import { mapGetters } from 'vuex'
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
    ...mapGetters([
      'keywordDict',
    ]),
    parsedKeywords () {
      return this.$parseKeyword(this.content.contentKeyword)
    }
  },
  methods: {
    openContent: function () {
      // 수정 필요
      window.open(this.content.contentUrl)
    },
    defaultBlogImg(e) {
      e.target.src = `https://cdn.vuetifyjs.com/images/john.jpg`
    },
    copyLink: function () {
      this.$copyText(this.content.contentUrl)
      this.snackbar.message = '컨텐츠 주소를 클립보드에 복사했습니다.'
      this.snackbar.show = true
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
      console.log(this.chips)
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


</style>