<template>
  <v-card
    outlined
    class="mx-auto mt-1 contentCard-Item"
    style="position: relative; "
  >
    <!-- 상단 이미지 -->
    <v-img
      max-height="170"
      :src="`${content.thumbnail}`"
    ></v-img>
    <!-- 1. 컨텐츠 타이틀 -->
    <v-card-title
      class="py-1 mt-2"
    >{{ content.title }}</v-card-title>
    <!-- 컨텐츠 본문 -->
    <v-card-text class="px-4 mb-0 pb-0">
      <div class="content-text">{{ content.text }}</div>
    </v-card-text>
    <!-- 키워드 -->
    <v-card-text
      class="pt-2 pl-3 pb-0"
    >
      <v-chip-group>
        <keyword-chip
          v-for="keyword in keywordSample"
          :key="keyword"
          :text="keyword"
        >
        </keyword-chip>
      </v-chip-group>
    </v-card-text>
    <!-- 하단 소개 및 공유버튼 -->
    <v-card-actions
      class='pt-0 justify-space-between'
    >
      <div class="pl-3 mt-0">
        <v-avatar
          color="primary"
          rounded
          size="20"
        >
        <span>20</span>
        </v-avatar>
        <span class="ml-2">어썸한 블로그</span>
      </div>
      <div>
        <v-btn icon>
          <v-icon>mdi-share</v-icon>
        </v-btn>
        <v-btn icon>
          <v-icon>mdi-cards-heart-outline</v-icon>
        </v-btn>        
      </div>
    </v-card-actions>
  </v-card>
</template>

<script>
import KeywordChip from '@/components/Keyword/KeywordChip.vue'

export default {
  name: 'ContentCard',
  components: {
    KeywordChip,
  },
  data: () => ({
    isVertical: true,
    content: {
      title: '[JavaScript] Virtual DOM 만들기',
      text: "React 공식 홈페이지에서는 Virtual DOM을 'UI로 표현될 객체를 가상 메모리에 저장하여 라이브러리에 의해 실제 DOM으로 동기화 하는 개념'으로 표현하고 있습니다. 여기서 UI로 표현될 객체는 DOM을 의미하며, 라이브러리는 VirtualDOM을 렌더링해주는 라이브러리를 의미합니다.",
      url: 'https://content.surfit.io/thumbs/image/5e2qw/00AyD/16489496246184bd1e70afe.webp/cover-center-1x.webp',
      thumbnail: 'https://content.surfit.io/thumbs/image/5e2qw/00AyD/16489496246184bd1e70afe.webp/cover-center-1x.webp',
      likes: 0,
      date: '2022-01-24',
    },
    keywordSample: ['UI/UX', 'JS', '생산성'],
  }),
  methods: {
  },
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