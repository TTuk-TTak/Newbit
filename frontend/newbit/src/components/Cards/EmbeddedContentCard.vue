<template>
  <v-card
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
          :src="content.thumbnail"
        ></v-img>
      </v-col>
      <v-col 
        class="align-between"
        cols=8>
        <v-row class="px-2 pt-3">
          <v-col class='py-0' cols=12>
            <h3
              class="pa-0"
            >{{ content.contentTitle }}</h3>
          </v-col>
          <v-col class='py-0 my-0 mt-1' cols=12>
            <v-card-text class="content-text mb-0 pa-0 ">{{ content.text }}</v-card-text>
          </v-col>
          <v-col class='pa-0 mt-0' cols=12>
          <v-chip-group
            class="py-0 ml-2" 
          >
            <keyword-chip
              class="embeddedkeyword"
              v-for="keyword in keywordSample"
              :key="keyword"
              :text="keyword"
              :isActive="false"
            >
            </keyword-chip>
          </v-chip-group>
          </v-col>
          <!-- 하단 블로그 소개 및 아이콘 -->
          <v-row
            class="pl-0 pt-0 ml-0 container justify-space-between bloginfo "
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
    isVertical: true,
    content: {
      contentTitle: '카드 타이틀',
      text: '이 편지는 영국에서 최초로 시작되어 일년에 한바퀴를 돌면서 받는 사람에게 행운을 주었고 지금은 당신에게로 옮겨진 이 편지는 4일 안에 당신 곁을 떠나야 합니다. 이 편지를 포함해서 7통을 행운이 필요한 사람에게 보내 주셔야 합니다. 이 편지를 포함해서 7통을 행운이 필요한 사람에게 보내 주셔야 합니다.이 편지를 포함해서 7통을 행운이 필요한 사람에게 보내 주셔야 합니다.이 편지를 포함해서 7통을 행운이 필요한 사람에게 보내 주셔야 합니다.',
      url: 'https://picsum.photos/500/300?image=55',
      thumbnail: 'https://cdn.vuetifyjs.com/images/cards/cooking.png',
      likes: 0,
      date: '2022-01-24',
    },
    keywordSample: ['UI/UX', 'JS', '생산성'],
  }),
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
.embeddedkeyword {
  font-family: 'KoPub Dotum';
  font-weight: 400;
}

/* 블로그명 */
.bloginfo {
  font-family: 'KoPub Dotum';
  font-weight: 400;
  color : #7E7E7E;
  -webkit-line-clamp: 3;
  -webkit-box-orient: vertical;
  overflow: hidden;
}


</style>