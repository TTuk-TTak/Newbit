<template>
  <div class="text-center">
    <v-dialog
      v-model="dialog"
      width="700"
    >
      <v-card class="rounded-xl ma-0 pa-0 firstlogin pb-4">
        <v-container>
          <v-card-title class="d-flex justify-center text-h5 font-weight-bold pb-0 modal-title">
            처음 오셨네요!
          </v-card-title>

          <v-card-text
            v-if="page === 1"
            class="d-flex justify-center font-weight-bold pb-1 modal-subtitle"
          >
            관심있는 키워드를 골라주세요!
          </v-card-text>
          <v-card-text
            v-else-if="page === 2"
            class="d-flex justify-center font-weight-bold pb-1 modal-subtitle"
          >
            관심사가 비슷한 사용자들을 팔로우 해보세요!
          </v-card-text>
          <v-card-text
            v-else-if="page === 3"
            class="d-flex justify-center font-weight-bold pb-1 modal-subtitle"
          >
            당신이 어떤분인지 저희에게 알려주세요!
          </v-card-text>

          <div v-if="page === 1">
            <keyword-selector></keyword-selector>
          </div>
          <div v-else-if="page === 2">
            <follow-recommendation></follow-recommendation>
          </div>
          <div
            v-else-if="page === 3"
          >
            <div class="d-flex justify-center">
              <img
                class="v-avatar image"
                src="https://www.gravatar.com/avatar/default?s=200&r=pg&d=mm"
                width="130"
              />
            </div>
            <div class="justify-center">
            <v-textarea
              v-model.trim="credentials.introduction"
              name="introduction"
              type="text"
              outlined
              rounded
              flat
              solo
              class="mt-7 px-10"
            ></v-textarea>
            </div>
          </div>

          <div
            v-if="page === 1 || page === 2"
            class="text-center mb-3 firstlogin"
          >
            <v-btn
              @click="nextPage"
              color="keywordChipText"
              dark
              depressed
              large
              rounded
              width="88%"
              class="font-weight-bold btn"
            >다음으로
            </v-btn>
          </div>
          <div
            v-else-if="page === 3"
            class="text-center"
          >
            <v-btn
              @click="dialog = false"
              color="keywordChipText"
              dark
              depressed
              large
              rounded
              width="88%"
              class="font-weight-bold mb-3 btn"
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
              width="88%"
              class="font-weight-bold btn"
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
              width="88%"
              class="font-weight-bold btn"
              outlined
            >다음에 할게요!
            </v-btn>
          </div>
        </v-container>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
import KeywordSelector from '@/components/Keyword/KeywordSelector.vue'
import FollowRecommendation from '@/components/Modals/FollowModal/FollowRecommendation.vue'


export default {
  name: 'FirstLoginModal',
  props: {
    isLogged: Boolean,
  },
  components: { KeywordSelector, FollowRecommendation },
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

<style scope>
/* 모달창 타이틀 (가장 큰 글씨) */
.v-application .modal-title{
  font-size : 2.0em !important;
  margin-bottom: 0.3em;
}

/* 모달창 서브타이틀 (설명) */
.v-application .modal-subtitle{
  font-size : 1.4em !important;
  margin-bottom: 1em;
}

/* 첫번째 모달 탭 메뉴 */
.v-application .firstlogin .v-tab {
  font-size: 1.2em;
  font-weight: 300  !important;
  color : #818181  !important;
}

/* 첫번째 모달 탭 메뉴(활성화) */
.v-application .firstlogin .v-tab--active {
  color : #0d0e23  !important;
  font-weight: 600  !important;
}

/* 버튼 글씨 */
.v-application .firstlogin .btn {
  font-size: 1.3em;
  font-weight: 500  !important;
}
</style>

