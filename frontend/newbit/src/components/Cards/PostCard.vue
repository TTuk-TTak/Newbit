<template>
    <!-- depressed -->
  <v-card
    :elevation='0'
    v-if="post"
    class="px-2 pl-8 pr-8"
  >
    <!-- 1. 카드 상단부 -->
    <div
      class='pt-4 pa-3 pb-0 justify-space-between align-end'
    >
      <div>
        <a href="#none">
        <v-avatar
          @click="$goToProfile(post.userCode)"
          size='42'
        >
          <img
            :src="post.userImg"
            @error='defaultProfile'
            style="object-fit:cover"
          >
        </v-avatar>
        </a>
      <a href="#none" class="underlineOff"><span class="ml-3 writer">{{ post.userNick }}</span></a>
      <a href="#none" class="underlineOff"><span class="ml-2 date">{{ '@' + post.userId }}</span></a>
      <span class="ml-2 date">·{{ $createdAt(post.postDate) }}</span>
      <span class ="ml-2 date" v-if="post.postEdit">(수정됨)</span>
      </div>
    </div>
    <!-- 임베드 된 경우 임베드된 컨텐츠 -->
    <embedded-content-card
      v-if="content"
      :content="content"
      class="mt-3 mx-3"
    ></embedded-content-card>
    
    <!-- 본문 -->
    <v-card-text
      class="post-text mb-0 mt-0 pt-3 pb-0"
      @click="$goToPostDetail(post.postCode)"
    >
      {{ post.postText }}
    </v-card-text>
    <!--  -->
    <v-card-actions>
      <v-btn 
        @click="$goToPostDetail(post.postCode)"
        icon
      >
        <v-icon>mdi-message-outline</v-icon>
      </v-btn>
      <span class="post-btn-nums" >{{ post.postComment }}</span>
      <v-btn 
        class="px-0"
        icon>
        <v-icon v-if="post.scrapped">mdi-bookmark</v-icon>
        <v-icon v-else>mdi-bookmark-outline</v-icon>
      </v-btn>
      <v-btn
        class="ml-0"
        @click="toggleLike()"
        icon>
        <v-icon v-if="post.liked === true">mdi-heart</v-icon>
        <v-icon v-else>mdi-heart-outline</v-icon>
      </v-btn>
      <span class="post-btn-nums">{{ post.postLike }}</span>
    </v-card-actions>
  </v-card>
</template>

<script>
import axios from 'axios'
import EmbeddedContentCard from '@/components/Cards/EmbeddedContentCard.vue'
import { mapState } from 'vuex'

export default {
  name: 'PostCard',
  props: {
    post: Object,
  },
  components: {
    EmbeddedContentCard,
  },
  data: () => {
    return {
      content: null,
    }
  },
  computed: {
    ...mapState([
      'user',
    ])
  },
  methods: {
    embedPost(contentCode) {
      axios.get(`${this.$serverURL}/content?uid=${this.user.userCode}&cid=${contentCode}`)
        .then(response => {
          this.content = response.data
          console.log(response.data)
        })
        .catch((err) => {
          console.log(err)
      })
    },
    defaultProfile(e) {
      e.target.src = `https://cdn.vuetifyjs.com/images/john.jpg`
    },
    toggleLike() {
      if (!this.post.liked) {
        this.likePost()
      } else {
        this.unlikePost()
      }
    },
    likePost() {      
      axios({
        method: 'POST',
        url: `${this.$serverURL}/post/like`,
        data: {
          'uid': this.user.userCode,
          'pid': this.post.postCode,
        },
      })
      .then((res) => {
        console.log('liked', res)
        if (res.data === 'success') {
          const snackbarText = '게시글을 좋아요 했습니다.'
          this.$store.dispatch('turnSnackBarOn', snackbarText)
          this.post.postLike++
          this.post.liked = !this.post.liked
        }
      })
      .catch((err) => {
        console.log(err)
      })  
    },
    unlikePost() {
      axios({
        method: 'DELETE',
        url: `${this.$serverURL}/post/like?uid=${this.user.userCode}&pid=${this.post.postCode}`,
      })
      .then((res) => {
        console.log('unliked', res)
        if (res.data === 'success') {
          const snackbarText = '게시글 좋아요를 취소했습니다.'
          this.$store.dispatch('turnSnackBarOn', snackbarText)
          this.post.liked = !this.post.liked
          this.post.postLike--
        }
      })
      .catch((err) => {
        console.log(err)
      })  
    },
  },
  created () {
    if (this.post.contentCode){
      this.embedPost(this.post.contentCode)
    }
  }
}
</script>

<style scoped>
.post-btn-nums {
  color : #272727;
  font-family: 'KoPub Dotum';
  font-weight: 100;
  font-size : 0.9em;
}

.underlineOff {
  text-decoration: none;
}


</style>