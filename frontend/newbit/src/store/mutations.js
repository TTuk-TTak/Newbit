import state from './state'
// import _ from 'lodash'

export default {
  // 1. 회원 관련 기능 
  // 1) 유저 정보 초기화
  RESET_USER_INFORMATION: function (state) {
    state.user = null
    state.recommendedPeople = []
    state.favoredKeyword = []
  },
  // 2) 유저 정보 저장
  SAVE_USER_INFORMATION: function (state, userData) {
    state.user = userData
  },
  // 3) 유저 키워드 저장
  SAVE_USER_KEYWORD: function (state, keywordString) {
    state.user.userKeyword = keywordString
  },
  // 4) 유저 선호 키워드 파싱 후 저장
  SAVE_FAVORED_KEYWORD: function (state, parsedKeyword) {
    state.favoredKeyword = parsedKeyword
  },
  // 5) 유저 추천 목록 저장
  SAVE_RECOMMENDED_PEOPLE: function (state, recommended) {
    state.recommendedPeople = recommended
  },


  // 2. SocialFeed 조작
  // 1) 포스트 로딩
  // LOAD_POSTS: function (state, data) {
  //   // (1) 선언
  //   // a. pageNum: 인덱싱을 위한 변수
  //   // b. lastPostCode: postCode 중 가장 작은 값.  
  //   const pageNum = state.socialFeed.pageNum
  //   const lastPostCode = _.last(data).postCode

  //   // (2) State 갱신
  //   state.socialFeed.pageNum += 1
  //   state.socialFeed.posts[pageNum + 1] = data
  //   state.socialFeed.lastPostCode = lastPostCode
  //   console.log('LOADPOST', state.socialFeedLoadedAt)
  //   state.socialFeedLoadedAt = Date.now()
  //   console.log('LOADPOST', state.socialFeedLoadedAt)
  // },
  // // 2) 마지막 포스트 도달.
  // REACHED_LAST_POST: function (state) {
  //   state.socialFeed.isAtLast = true
  // },

  // 3. 컴포넌트 조작
  TURN_POST_CREATE_MODAL_ON: function (state, payload) {
    state.modals.postCreateModal = true
    state.embeddedContent.contentCode = payload.contentCode
  },
  TURN_POST_CREATE_MODAL_OFF: function () {
    state.modals.postCreateModal = false
    state.embeddedContent.contentCode = 0
  },
  TURN_FIRST_LOGIN_MODAL_ON: function (state) {
    state.modals.firstLoginModal = true
  },
  TURN_FIRST_LOGIN_MODAL_OFF: function (state) {
    state.modals.firstLoginModal = false
  },
  TURN_LOGIN_MODAL_ON: function (state) {
    state.modals.loginModal = true
  },
  TURN_LOGIN_MODAL_OFF: function (state) {
    state.modals.loginModal = false
  },
  // 1) 스낵바
  TURN_SNACKBAR_ON: function (state, snackbarText) {
    state.snackbar.isRendered = false
    state.snackbar.text = snackbarText
    setTimeout(state.snackbar.isRendered = true, 100)
  },
  SET_SEARCH_INPUT: function (state, searchInput) {
    state.searchModal.input = searchInput
    console.log('검색어 도착!', searchInput, state.searchModal.input)
  },


  //4. 추천 피드
  // 1) 인기콘텐츠 로딩
  SET_CONTENTS: function (state, data) {
    // (1) 선언
    // a. pageNum: 인덱싱을 위한 변수
    // b. lastPostCode: postCode 중 가장 작은 값.  
    // const pageNum = state.curationFeed.pageNum
    // const page = state.curationFeed.page
    // const lastPostCode = _.last(data).contentCode
    // (2) State 갱신
    // state.curationFeed.page += 1
    state.curationFeed.contents = data
    // state.curationFeed.lastPostCode = lastPostCode
  },
  PRESET_CURATION_KEYWORD: function (state, keyword) {
    state.curationFeed.preSelectedKeyword = keyword
  }


}
