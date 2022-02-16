<template>
  <v-card
    class="pa-2"
    color="feedBackground"
  >
    <!-- 검색창  -->
    <v-card
      outlined
      class="pa-5 my-2 mx-2"
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
            class="mb-2 mx-2"
            hide-details
            type="String"
            v-model="search"      
          ></v-text-field>  
        </v-col>
      </v-row>
    </v-card>
    <!-- 탭전환 -->
    <div class="mx-2" style="border-bottom:1px solid lightgray">
      <v-tabs
        class="ml-1 mr-3"
        slider-color='#0d0e23'
      >
        <v-tab class="contentTab" @click="[setHot(), changeType()]">게시글</v-tab>
        <v-tab class="contentTab" @click="[setNew(), changeType()]">컨텐츠</v-tab>
        <v-tab class="contentTab" @click="[setNew(), changeType()]">사용자</v-tab>
        <hr>
      </v-tabs>
    </div>

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
  <!-- 무한 스크롤 -->
  <v-row
    class="mt-5 pt-5 justify-self-center align-self-end"
  >
    <v-spacer></v-spacer>
    <infinite-loading
      v-if='user && infinityHandlerRendered'
      class="mt-5 pt-5 justify-self-center align-self-center"
      @infinite="infiniteHandler" 
      >
      <template slot="no-more">
        2022 - Newbit
      </template>
      </infinite-loading>
      <v-spacer></v-spacer>
  </v-row>
</v-card>
</template>

<script>
// 3rd party
// import _ from 'lodash'
import axios from 'axios'
import InfiniteLoading from 'vue-infinite-loading'

// Vue
import { mapState } from 'vuex'

// Local
import PostCard from '@/components/Cards/PostCard.vue'
// import ContentCard from '@/components/Cards/ContentCard.vue'

export default {
  name: 'Search',       
  components: {
    PostCard,
    //ContentCard,
    InfiniteLoading,
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