<template>
  <v-row
    class=" pa-2 px-4"
  >
    <v-row
      class="pt-2"
      id='socialFeed'
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

</template>

<script>
import { mapState } from 'vuex'
import PostCard from '@/components/Cards/PostCard.vue'

export default {
  name: 'SocialFeed',
  components: {
    PostCard,
  },  
  data: () => ({
    nextItem: 1,
    posts: []
  }),
  created () {

  },
  mounted () {
    // Detect when scrolled to bottom.
    const listElm = document.querySelector('#socialFeed');
    listElm.addEventListener('scroll', e => {
      if(listElm.scrollTop + listElm.clientHeight >= listElm.scrollHeight) {
        this.loadPost();
        console.log(e)
      } 
    });

    // Initially load some items.
    // this.loadPost();

  },

  computed: {
    ...mapState([
      'socialFeedLoadedAt',
      'socialFeed',
    ])
  },

  methods: {
    loadPost () {
      if (!this.socialFeed.isAtLast)
      this.$store.dispatch('loadPosts')
    },
    // loadMore () {
    //   this.loading = true;
    //   setTimeout(e => {
    //     for (var i = 0; i < 20; i++) {
    //       this.items.push('Item ' + this.nextItem++);
    //     }
    //     this.loading = false;
    //   }, 200);      
    // }



  },

  watch: {
    socialFeedLoadedAt: {
      handler () {
        console.log('렌더링하는 함수 구현')
      }
    },
  },
}
</script>

<style>
</style>