<template>
  <v-container
    :min-height='150'
    justify='center'
    color='feedBackground'
  >
    <v-row
      align='center'
    >
      <v-col
        cols=auto
      >
        <v-icon
          class="align-self-center"
          large
        >mdi-magnify</v-icon>
      </v-col>
      <v-col>
        <v-text-field
          class="mb-2"
          hide-details
          v-model="searchword"
          @keyup.enter="submit"
        ></v-text-field>
        <span>추천 키워드</span>
        <v-chip-bar
          class="ml-2"
        >
          <keyword-chip
            v-for="tag in tags"
            :key="`searchbar` + tag"
            :text="tag"
            :isToggleAvailable='false'
          ></keyword-chip>
        </v-chip-bar>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import KeywordChip from '@/components/Keyword/KeywordChip.vue'

export default {
  name: 'SearchModal',
  components: {
    KeywordChip,
  },
  data: () => {
    return {
      tags: [
        'UI/UX',
        'Vue.js',
        'Frontend',
        'Backend',
      ],
    }
  },
  methods: {
    submit() {
      if (this.searchword) {
        this.$store.dispatch('setSearchWord', this.searchword)
        this.$router.push({ name: 'Search', params: { keyword: this.searchword } })
      }
    }
  },
}
</script>

<style scoped>


</style>