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
          @click="searchbtn()"   
        >mdi-magnify</v-icon>
      </v-col>
      <v-col>
        <v-text-field
          class="mb-2"
          hide-details
          type="String"
          v-model="search"      
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
    <btn-dark></btn-dark>
  </v-container>
</template>

<script>
import KeywordChip from '@/components/Keyword/KeywordChip.vue'
import BtnDark from '@/components/Commons/BtnDark.vue'

export default {
  name: 'SearchModal',
  components: {
    KeywordChip,
    BtnDark,
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
  methods:{
    searchbtn() { 
      this.$emit('close-search-modal')
      this.$goToSearchFeed(this.search)
    }
  } 
}
</script>

<style scoped>


</style>