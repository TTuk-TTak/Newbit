<template>
  <v-card
    class="mx-auto mt-3"
  >
    <!-- 상단 이미지 -->
    <v-img
      max-height="250"
      :src="`${content.thumbnail}`"
      class="my-2"
    ></v-img>
    <!-- 컨텐츠 타이틀 -->
    <v-card-title>{{ content.title }}</v-card-title>
    <!-- 컨텐츠 본문 -->
    <v-card-text
      class="content-text"
      >
      <div>{{ content.text }}</div>
    </v-card-text>
    <!-- 키워드 -->
    <v-card-text>
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
      class='justify-space-between'
    >
      <div>
        <v-avatar
          color="primary"
          rounded
          size="20"
        >
        <span>20</span>
        </v-avatar>
        <span>어썸한 블로그</span>
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
      title: '카드 타이틀',
      text: '이 편지는 영국에서 최초로 시작되어 일년에 한바퀴를 돌면서 받는 사람에게 행운을 주었고 지금은 당신에게로 옮겨진 이 편지는 4일 안에 당신 곁을 떠나야 합니다. 이 편지를 포함해서 7통을 행운이 필요한 사람에게 보내 주셔야 합니다. 복사를 해도 좋습니다. 혹 미신이라 하실지 모르지만 사실입니다.',
      url: 'https://cdn.vuetifyjs.com/images/cards/cooking.png',
      thumbnail: 'https://cdn.vuetifyjs.com/images/cards/cooking.png',
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

</style>