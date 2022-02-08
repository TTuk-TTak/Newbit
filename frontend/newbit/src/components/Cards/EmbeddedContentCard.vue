<template>
  <v-card
    v-if='content'
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
            class="py-0 ml-2" 
          >
            <keyword-chip
              class="embeddedKeyword"
              v-for="(value, key) in keywords"
              :key="key"
              :text="value"
              :isActive="false"
            >
            </keyword-chip>
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
              <span>20</span>
              </v-avatar>
              <span class="ml-2">어썸한 블로그</span>
            </div>
            <div class="pr-4 pb-1 mt-2 pt-0">
              <v-btn icon>
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
  </v-card>
</template>

<script>
import KeywordChip from '@/components/Keyword/KeywordChip.vue'

export default {
  name: 'EmbeddedContentCard',
  props: {
    content: Object
  },
  components: {
    KeywordChip,
  },
  data: () => ({
    defaultImg: 'https://cdn.vuetifyjs.com/images/cards/cooking.png',
    // content: {
    //   contentUrl: 'https://picsum.photos/500/300?image=55',
    //   contentImg: 'https://cdn.vuetifyjs.com/images/cards/cooking.png',
    //   contentLike: 0,
    //   contentKeyword: '',
    //   date: '2022-01-24',
    // },
    keywords: [],
  }),
  methods: {
    openContent: function () {
      // 수정 필요
      window.open(this.content.contentUrl)
    },
    // 테스트 필요
    shareContent: function () {
      this.$copyText(this.content.contentUrl)
    }
  },
  watch: {
    content: function () {
      let parsedKeyword = this.$parseKeyword(this.content.contentKeyword)
      this.keywords = this.$makeKeywordDict(parsedKeyword)
    }
  }

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