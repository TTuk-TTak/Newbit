<template>
    <div class="text-center">
    <v-dialog
      v-model="isOpened"
      max-width="800"
    >
      <v-card
        class="rounded-xl py-1"
      >
        <!-- 1. 카드 상단부 -->
        <v-card-title
          class='justify-space-between'
        >
          <div>
            <v-avatar>
              <img
                src="https://cdn.vuetifyjs.com/images/john.jpg"
                alt="John"
              >
            </v-avatar>
            <span class="ml-2">어썸한 닉네임</span>
          </div>
          <!-- 닫기 버튼 -->
          <v-btn 
            class="align-self-start"
            icon 
            right 
            @click="isOpened=false">
            <v-icon>mdi-close</v-icon>
          </v-btn>
        </v-card-title>
        <!--input-box-->
        <v-textarea
          v-model='postText'
          class="px-5"
          placeholder="게시글을 작성해주세요."
          rows='8'
          maxlength='500'
          :hint='`(` + postText.length + ` /500)`'
          persistent-hint
          no-resize
          flat
          solo
        >
        </v-textarea>
        <v-container>
          <embedded-content-card></embedded-content-card>
        </v-container>

        <v-card-actions>
          <btn-dark
            class='grow mb-3'
            message='글 작성'
          ></btn-dark>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
import BtnDark from '@/components/Commons/BtnDark.vue'
import EmbeddedContentCard from '@/components/Cards/EmbeddedContentCard.vue'

export default {
  name: 'PostCreateModal',
  props: {
    isOpened: Boolean,
  },
  components: {
    BtnDark,
    EmbeddedContentCard,
  },
  data: () => {
    return {
      btnSize: 'large',
      postText: '',
    }
  },
}
</script>

<style>

</style>