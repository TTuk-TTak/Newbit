<template>
  <v-card
    class="px-2 pl-8 pr-8"
    outlined
  >
    <!-- 1. 카드 상단부 -->
    <div
      class='pa-3 pb-0 justify-space-between align-end'
    >
      <div>
        <v-avatar
          size='32'
        >
          <img
            src="https://cdn.vuetifyjs.com/images/john.jpg"
            alt="John"
          >
        </v-avatar>
      <span class="ml-3 writer">{{ post.userCode }}</span>
      <span class="ml-2 date">·{{ $createdAt(post.postDate) }}</span>
      <span class ="ml-2 date" v-if="post.postEdit">(수정됨)</span>
      </div>
    </div>
    <!-- 임베드 된 경우 임베드된 컨텐츠 -->
    <embedded-content-card
      v-if="post.contentCode"
      class="mt-3 mx-3"
    ></embedded-content-card>
    
    <!-- 본문 -->
    <v-card-text
      class="post-text mb-0 mt-0 pt-3 pb-0"
    >
      {{ post.postText }}
    </v-card-text>
    <!--  -->
    <v-card-actions>
      <v-btn icon>
        <v-icon>mdi-cards-heart-outline</v-icon>
        <span>{{ post.postLike }}</span>
      </v-btn>
      <v-btn icon>
        <v-icon>mdi-message-outline</v-icon>
        <span>{{ post.postComment }}</span>
      </v-btn>
      <v-btn icon>
        <v-icon>mdi-share</v-icon>
      </v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
import EmbeddedContentCard from '@/components/Cards/EmbeddedContentCard.vue'

export default {
  name: 'PostCard',
  props: {
    // post: Object,
  },
// 포스트 샘플. 
// contentCode: 2
// liked: false
// postCode: 15
// postComment: 3
// postDate: "2022-01-26 04:49:47"
// postEdit: false
// postLike: 1
// postScrap: 0
// postText: "The past is in the past~~Let it go"
// scrapped: false
// techblogCode: 4
// userCode: 1


  components: {
    EmbeddedContentCard,
  },
  data: () => {
    return {
      post: {
        contentCode: '1',
        liked: false,
        postCode: '15',
        postComment: '3',
        postDate: "2022-01-26 04:49:47",
        postEdit: true,
        postLike: 1,
        postScrap: 0,
        postText: '하여도 불어 못하다 인생에 붙잡아 것이다. 그것을 평화스러운 전인 것이다. 인류의 굳세게 관현악이며, 쓸쓸한 미묘한 뜨거운지라, 두기 갑 것이다. 열매를 끓는 할지니, 풍부하게 예가 두기 만물은 내려온 이성은 것이다. 타오르고 관현악이며, 찾아 많이 아니한 놀이 피어나기 인간이 있다. 가는 얼마나 부패를 열락의 인간에 그러므로 그리하였는가? 날카로우나 품으며, 천지는 작고 보이는 때문이다. 가치를 얼마나 생명을 청춘의 석가는 못하다 철환하였는가? 위하여, 황금시대를 것이다.보라, 쓸쓸하랴?',
        scrapped: false,
        techblogCode: 4,
        userCode: '제임스',
        userNick: null,
        userId: null,
        userImg: null,
      }
    }
  },
}
</script>

<style scope>
.post-text {
  display: -webkit-box;
  -webkit-line-clamp: 3;
  -webkit-box-orient: vertical;
  overflow: hidden;
}

.date{
  color : #919191;
  font-family: 'KoPub Dotum';
  font-weight: 100;
  font-size : 0.9em;
}

.writer{
  font-size : 1.1em;
}

/* 본문 글씨체 */
.theme--light.v-card > .v-card__text {
  font-family: 'KoPub Dotum';
  font-weight: 400;
  color : #272727;
}
</style>