<template>
  <v-card
    class="ma-1 py-0" 
  >
    <v-row>
      <v-col 
        class="py-0"
        cols=4 
        >
        <v-img
          height='auto'
          class="rounded-l ml-auto"
          :src="content.thumbnail"
        ></v-img>
      </v-col>
      <v-col 
        class="d-flex align-between"
        cols=8>
        <v-row class="px-2 pt-3">
          <v-col class='py-0' cols=12>
            <h3
              class="pa-0"
            >{{ content.contentTitle }}</h3>
          </v-col>
          <v-col class='py-0' cols=12>
            <v-card-text class="content-text mb-0 pa-0">{{ content.contentText }}</v-card-text>
          </v-col>
          <v-col class='pa-0' cols=12>
          <v-chip-group
            class="py-0"
          >
            <keyword-chip
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
            class="pa-2 pt-0 container justify-space-between align-center"
          >
            <div
              class="pl-3 align-center"
            >
              <v-avatar
                color="primary"
                rounded
                size="20"
              >
              <span>20</span>
              </v-avatar>
              <span class="ml-1">어썸한 블로그</span>
            </div>
            <div class="pr-4">
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

<style>
.content-text {
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  overflow: hidden;
}
</style>