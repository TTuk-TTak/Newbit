<template>
  <v-card
    class="pa-2"
    color="feedBackground"
  >
      <!-- 탭전환 -->
      <v-tabs
        slider-color='#C4C4C4'
      >
        <v-tab>소셜피드</v-tab>
        <v-tab>큐레이션</v-tab>
        <v-tab>아카이빙</v-tab>
      </v-tabs>

  <!-- 소셜피드-->
  <v-row
    class=" pa-2 px-4"
  >
  {{ $store.getters.categorizedKeyword }}
    <v-row
      class="pt-2"
    >
      <v-col
        v-for="i in 10"
        :key="`archived` + i"    
        class="pa-1 pb-1"
        cols=12
      >
        <post-card></post-card>
      </v-col>
    </v-row>
  </v-row>

  <!-- 추천피드-->
  <!--v-row
    class='pa-2 pt-3'
  >
      <v-col
        class="pa-2"
        v-for="n in 21"
        :key="n"
        cols="6"
      >
        <content-card></content-card>
      </v-col>
  </!--v-row-->


</v-card>
</template>

<script>
import { mapState } from 'vuex'
import PostCard from '@/components/Cards/PostCard.vue'
///import ContentCard from '@/components/Cards/ContentCard.vue'
import axios from 'axios'
//import _ from 'lodash'

export default {
  name: 'Search',       
  components: {
    PostCard,     // post 카드를 표출함 
    //ContentCard,
  },
  data: function() {
      return{
          // 변수 선언
          search: this.$route.params.search,
          params: this.$route.params,

      }
  },
  methods: {
    loadPost () {
      if (!this.searchFeed.isAtLast)
      this.$store.dispatch('loadPosts')
    },
    
    getAllSearched() {
      //const postId = _.split(this.$route.path, '/')[2]
      // 일단 처음 띄우기로 한 검색 페이지를 띄움 
      axios.get(`${this.$serverURL}/post/search`, {params:this.params})   //`http://localhost:9999/post/search`
      .then((response) => {
        console.log("도달~")
        console.log(response.data)
      })
      .catch((err) => {
        console.log(err)
      })
    },
  },

  created () {
    this.getAllSearched()     /// ???? 맞음??
    console.log(this.search);
    //console.log(this.params);
  },
  computed: {
    ...mapState([
      'socialFeedLoadedAt',
      'socialFeed',
    ]),
  },
  watch: {
      /*
    socialFeedLoadedAt: {
      handler () {
          console.log('렌더링하는 함수 구현')
      }
    },*/
  },
}
</script>

<style>
</style>