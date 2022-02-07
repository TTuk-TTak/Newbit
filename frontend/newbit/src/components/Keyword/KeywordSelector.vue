<template>
  <div class="px-10">
    <v-tabs>
      <v-tabs-slider></v-tabs-slider>
      <v-tab
        @click="changeToFrontend"
        class="font-weight-bold"
      >프론트엔드</v-tab>
      <v-tab
        @click="changeToBackend"
        class="font-weight-bold"
      >벡엔드</v-tab>
      <v-tab
        @click="changeToCooperation"
        class="font-weight-bold"
      >협업</v-tab>
    </v-tabs>
    <v-divider></v-divider>
    <v-chip-group
      column
      class="mt-3 mb-9"
    >
      <div v-if="toggle === 'frontend'">
        <keyword-chip2
          v-for="frontendTag in frontendTags"
          :key="frontendTag"
          :text="frontendTag"
        ></keyword-chip2>
      </div>
      <div v-else-if="toggle === 'backend'">
        <keyword-chip2
          v-for="backendTag in backendTags"
          :key="backendTag"
          :text="backendTag"
        ></keyword-chip2>
      </div>
      <div v-else-if="toggle === 'cooperation'">
        <keyword-chip2
          v-for="cooperationTag in cooperationTags"
          :key="cooperationTag"
          :text="cooperationTag"
        ></keyword-chip2>
      </div>
    </v-chip-group>
  </div>
</template>

<script>
import KeywordChip2 from '@/components/Keyword/KeywordChip2.vue'

export default {
  name: 'KeywordSelector',
  components: {
    KeywordChip2,
  },
  data: () => {
    return {
      toggle: 'frontend',
      frontendTags: [
        'UI/UX',
        'Vue.js',
        'Typescript',
        'SPA',
        'React'
      ],
      backendTags: [
        'Node.js',
        'Django',
        'Java',
        'Flask',
        'Kotlin'
      ],
      cooperationTags: [
        'Figma',
        'ERDCloud',
        'Draw.io',
        'Notion',
        'Git'
      ]
    }
  },
  methods: {
    changeToFrontend () {
      this.toggle = 'frontend'
    },
    changeToBackend () {
      this.toggle = 'backend'
    },
    changeToCooperation () {
      this.toggle = 'cooperation'
    }
  }
}
</script>

<style>
.v-list-item{
  background-color:#f2f2f4;
  /* 300으로 잡으면 1920x1080에서 딱 예쁘게 보이는데 반응형이 아니라서 아쉬운 부분...*/
  width:300px; 
}
</style>