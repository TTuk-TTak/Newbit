<template>
  <v-card
    class="pb-5"
  >
    <!-- 1. 카드 상단부 -->
    <v-row
      class='pa-4 pb-0 justify-space-between'
    >
      <v-col class="pa-2">
        <v-btn icon>
          <v-icon>mdi-arrow-left</v-icon>
        </v-btn>
        <v-avatar
          size='32'
        >
          <img
            src="https://cdn.vuetifyjs.com/images/john.jpg"
            alt="John"
          >
        </v-avatar>
        <span class="ml-2">{{ post.userCode }}</span>
        <span class="ml-2">{{ post.date }}</span>
        <span v-if="post.edit"> (수정됨)</span>  
      </v-col>

      <v-col class="pa-2" cols=1>
        <!-- 1-1. 카드 상단부-팝업메뉴 -->
        <v-menu
          v-if="isLoggedIn === true"
          left
          bottom
        >
          <template v-slot:activator="{ on, attrs }">
            <v-btn
              icon
              v-bind="attrs"
              v-on="on"
            >
              <v-icon>mdi-dots-horizontal</v-icon>
            </v-btn>
          </template>
          <v-list>
            <!-- 1) 수정 -->
            <v-list-item>
              <v-icon>mdi-cog</v-icon>
              <v-list-item-content class="ml-2 mr-1">
                <v-list-item-subtitle>게시글 수정</v-list-item-subtitle>
              </v-list-item-content>
            </v-list-item>
            <!-- 2) 삭제 -->
            <v-list-item>
              <v-icon>mdi-delete</v-icon>
              <v-list-item-content class="ml-2 mr-1">
                <v-list-item-subtitle>게시글 삭제</v-list-item-subtitle>
              </v-list-item-content>
            </v-list-item>
          </v-list>
        </v-menu>
      </v-col>
    </v-row>
    <!-- 2-1. (임베드 된 경우) 임베드된 컨텐츠 -->
    <embedded-content-card
      v-if="post.contentCode"
      class="mt-5 mx-3"
    ></embedded-content-card>
    
    <!-- 2-2. 본문 -->
    <v-card-text
      class="mb-0 pb-0"
    >
      {{ post.text }}
    </v-card-text>
    <!-- 3. 카드 하단부 -->
    <v-row
      class="container justify-between mt-1 py-0"
    >
      <!-- 3-1. 카드 조작 -->
      <v-col class="py-1">
        <v-card-actions
          class="ml-2 "
        >
          <v-btn icon>
            <v-icon>mdi-cards-heart-outline</v-icon>
            <span>{{ post.like }}</span>
          </v-btn>
          <v-btn icon>
            <v-icon>mdi-message-outline</v-icon>
            <span>{{ post.comment }}</span>
          </v-btn>
          <v-btn icon>
            <v-icon>mdi-share</v-icon>
          </v-btn>
        </v-card-actions>
      </v-col>
      <!-- 3-2. 생성 시간 -->
      <v-col class="py-1 ma-0">
        <v-card-text
          class="text-end"
        >{{ post.date }} (수정됨)</v-card-text>
      </v-col>
    </v-row>

    <!-- 댓글 작성창 -->
    <v-divider
      class="mx-4"
    ></v-divider>

    <v-card-text
      v-if="isLoggedIn"
      class="pb-0"
    >
      <v-row 
        class="ml-3 my-2">
        <user-profile-icon
          :imgUrl="`https://avatars0.githubusercontent.com/u/9064066?v=4&s=460`"
        ></user-profile-icon>
        <v-textarea 
          class="ml-2 py-0"
          placeholder="댓글을 작성해주세요."
          rows=1
          counter='100'
          maxlength='100'
          no-resize
          auto-grow
          @keydown.enter="writeComment()"
          ></v-textarea>
        <v-btn 
          @click="writeComment()"
          icon
          >
          <v-icon>mdi-pencil</v-icon>
        </v-btn>
      </v-row>
      <v-divider></v-divider>
    </v-card-text>
    <post-detail-comment
      class="ml-2"
      v-for="i in 3"
      :key="i"
    ></post-detail-comment>

    <!-- 작성 완료 팝업 -->
    <v-snackbar
      v-model="snackbar.show"
      :timeout="snackbar.timeout"
    >
      {{ snackbar.message }}
      <template v-slot:action="{ attrs }">
        <v-btn
          color="blue"
          text
          v-bind="attrs"
          @click="snackbar = false"
        >
          Close
        </v-btn>
      </template>
    </v-snackbar>
  </v-card>
</template>

<script>
import EmbeddedContentCard from '@/components/Cards/EmbeddedContentCard.vue'
import PostDetailComment from '@/components/PostDetail/PostDetailComment.vue'
import UserProfileIcon from '@/components/Commons/UserProfileIcon.vue'

export default {
  name: 'PostDetail',
  components: {
    EmbeddedContentCard,
    PostDetailComment,
    UserProfileIcon,
  },
  methods: {
    writeComment () {
      this.snackbar.show = true
    },
  },
  data: () => {
    return {
      isLoggedIn: true,
      post: {
        userCode: '제임스',
        contentCode: '1',
        text: '하여도 불어 못하다 인생에 붙잡아 것이다. 그것을 평화스러운 전인 것이다. 인류의 굳세게 관현악이며, 쓸쓸한 미묘한 뜨거운지라, 두기 갑 것이다. 열매를 끓는 할지니, 풍부하게 예가 두기 만물은 내려온 이성은 것이다. 타오르고 관현악이며, 찾아 많이 아니한 놀이 피어나기 인간이 있다. 가는 얼마나 부패를 열락의 인간에 그러므로 그리하였는가? 날카로우나 품으며, 천지는 작고 보이는 때문이다. 가치를 얼마나 생명을 청춘의 석가는 못하다 철환하였는가? 위하여, 황금시대를 것이다.보라, 쓸쓸하랴?',
        like: '3',
        comment: '2',
        date: '13분 전',
        scrap: '1',
        report: '0',
        edit: false,
      },
      snackbar: {
        show: false,
        message: '댓글을 달았습니다.',
        timeout: '1000'
      },
    }
  },


  
}
</script>

<style>

</style>