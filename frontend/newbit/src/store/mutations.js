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

  // 2. Feed 조작
  // 1) 소셜 피드
  SOCIAL_FEED_LOADED_AT: function (state) {
    state.socialFeedLoadedAt = new Date()
    console.log(state.socialFeedLoadedAt)
  },
  // 2) 콘텐츠 피드
  CONTENT_FEED_LOADED_AT: function (state) {
    state.contentFeedLoadedAt = new Date()
    console.log(state.contentFeedLoadedAt)
  },
  // 3) 아카이빙 피드 
  ARCHIVING_FEED_LOADED_AT: function (state) {
    state.archivingFeedLoadedAt = new Date()
    console.log(state.archivingFeedLoadedAt)
  },

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
    console.log(state.preSelectedKeyword, '선택된 키워드')
    state.preSelectedKeyword = keyword
  },
  PRESET_SEARCH_KEYWORD: function (state, searchKeyword) {
    state.preSelectedKeyword = searchKeyword
    console.log('검색어 도착!', state.preSelectedKeyword)
  },


  //5. 알림센터
  SET_NOTIFICATION: function (state, data) {
    state.notiCenter.notifications = data
  },
  DELETE_NOTIFICATION: function (state) {
    state.notiCenter = null
  },


}
