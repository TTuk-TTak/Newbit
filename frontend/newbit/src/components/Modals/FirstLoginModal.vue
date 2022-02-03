<template>
  <div class="text-center">
    <v-dialog
      v-model="dialog"
      width="800"
    >
      <v-card class="rounded-xl ma-0 pa-0">
        <v-container>
          <v-card-title class="d-flex justify-center text-h5 font-weight-bold pb-0">
            처음 오셨네요!
          </v-card-title>

          <v-card-text
            v-if="page === 1"
            class="d-flex justify-center font-weight-bold pb-1"
          >
            관심있는 키워드를 골라주세요!
          </v-card-text>
          <v-card-text
            v-else-if="page === 2"
            class="d-flex justify-center font-weight-bold pb-1"
          >
            관심사가 비슷한 사용자들을 팔로우 해보세요!
          </v-card-text>
          <v-card-text
            v-else-if="page === 3"
            class="d-flex justify-center font-weight-bold pb-1"
          >
            당신이 어떤분인지 저희에게 알려주세요!
          </v-card-text>

          <div v-if="page === 1">
            <keyword-selector></keyword-selector>
          </div>
          <div v-else-if="page === 2">
            <follow-list></follow-list>
          </div>
          <div
            v-else-if="page === 3"
            class="ma-6"
          >
            <div class="d-flex justify-center">
              <img
                class="v-avatar image"
                src="https://www.gravatar.com/avatar/default?s=200&r=pg&d=mm"
                width="150"
              />
            </div>
            <v-textarea
              v-model.trim="credentials.introduction"
              name="introduction"
              type="text"
              outlined
              rounded
              solo
            ></v-textarea>
          </div>

          <div
            v-if="page === 1 || page === 2"
            class="text-center mb-3"
          >
            <v-btn
              @click="nextPage"
              color="black"
              dark
              large
              rounded
              width="40%"
              class="font-weight-bold"
            >다음으로
            </v-btn>
          </div>
          <div
            v-else-if="page === 3"
            class="text-center"
          >
            <v-btn
              @click="dialog = false"
              color="black"
              dark
              large
              rounded
              width="40%"
              class="font-weight-bold mb-3"
            >입력 완료
            </v-btn>
          </div>
          <div
            v-if="page === 2"
            class="text-center"
          >
            <v-btn
              @click="previousPage"
              large
              rounded
              width="40%"
              class="font-weight-bold"
              outlined
            >이전으로
            </v-btn>
          </div>
          <div
            v-else-if="page === 3"
            class="text-center"
          >
            <v-btn
              @click="dialog = false"
              large
              rounded
              width="40%"
              class="font-weight-bold"
              outlined
            >다음에 할께요!
            </v-btn>
          </div>
        </v-container>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
import KeywordSelector from '@/components/Keyword/KeywordSelector.vue'
import FollowList from '@/components/Commons/FollowList.vue'


export default {
  name: 'FirstLoginModal',
  props: {
    isLogged: Boolean,
  },
  components: { KeywordSelector, FollowList },
  data () {
    return {
      credentials: {
        introduction: '',
      },
      page: 1,
      dialog: this.isLogged
    }
  },
  methods: {
    nextPage () {
      if (this.page === 1 || this.page === 2) {
        this.page += 1
      }
    },
    previousPage () {
      if (this.page === 2) {
        this.page -= 1
      }
    }
  }
}
</script>

<style>
</style>