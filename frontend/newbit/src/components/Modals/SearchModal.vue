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
          @click="clickSearchBtn()"   
        >mdi-magnify</v-icon>
      </v-col>
      <v-col>
        <v-text-field
          v-model="searchInput"
          @keydown.enter="clickSearchBtn()"
          class="mb-2"
          hide-details
          type="String"
          placeholder="게시글, 컨텐츠, 사용자 검색이 가능합니다."
        ></v-text-field>
        <!-- <span>추천 키워드</span>
        <v-chip-bar
          class="ml-2"
        >
          <keyword-chip
            v-for="tag in tags"
            :key="`searchbar` + tag"
            :text="tag"
            :isToggleAvailable='false'
          ></keyword-chip>
        </v-chip-bar> -->
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import { mapState } from 'vuex'
// import KeywordChip from '@/components/Keyword/KeywordChip.vue'

export default {
  name: 'SearchModal',
  components: {
    // KeywordChip,
  },
  data: () => {
    return {
      searchInput: '',
      // tags: [
      //   'UI/UX',
      //   'Vue.js',
      //   'Frontend',
      //   'Backend',
      // ],
    }
  },
  computed: {
    ...mapState([
      'searchModal',
    ])
  },
  methods:{
    clickSearchBtn() { 
      this.$emit('close-search-modal')
      this.$store.dispatch('setSearchInput', this.searchInput)
      if (this.$route.path !== '/search') {
        this.$goToSearchFeed()
      }
    }
  },
  watch: {
    searchModal: {
      deep: true,
      immediate: true,
      handler () {
        if (this.searchModal.input) {
          console.log(this.searchModal.input)
          this.searchInput = this.searchModal.input
          console.log(this.searchInput)
        }
      }
    }
  },
  created () {
    if (this.searchModal.input) {
      console.log(this.searchModal.input)
      this.searchInput = this.searchModal.input
      console.log(this.searchInput)
    }
  }
}
</script>

<style scoped>


</style>