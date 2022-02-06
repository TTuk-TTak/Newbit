<template>
  <div class="px-10">
    {{ KEYWORDS }}
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
        <keyword-chip
          v-for="frontendTag in frontendTags"
          :key="frontendTag"
          :text="frontendTag"
          :isToggleAvailable='true'
          :isSmall='false'
          :isLabel='true'
          :isFixed='true'
        ></keyword-chip>
      </div>
      <div v-else-if="toggle === 'backend'">
        <keyword-chip
          v-for="backendTag in backendTags"
          :key="backendTag"
          :text="backendTag"
          :isToggleAvailable='true'
          :isSmall='false'
          :isLabel='true'
          :isFixed='true'
        ></keyword-chip>
      </div>
      <div v-else-if="toggle === 'cooperation'">
        <keyword-chip
          v-for="cooperationTag in cooperationTags"
          :key="cooperationTag"
          :text="cooperationTag"
          :isToggleAvailable='true'
          :isSmall='false'
          :isLabel='true'
          :isFixed='true'
        ></keyword-chip>
      </div>
    </v-chip-group>
  </div>
</template>

<script>
import KeywordChip from '@/components/Keyword/KeywordChip.vue'

export default {
  name: 'KeywordSelector',
  components: {
    KeywordChip,
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
</style>