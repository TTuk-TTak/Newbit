import state from './state'
import _ from 'lodash'

export default {
  // 1. 회원 관련 기능 
  // 1) 유저 정보 초기화
  RESET_USER_INFORMATION: function (state) {
    state.user = null
  },
  // 2) 유저 정보 저장
  SAVE_USER_INFORMATION: function (state, userData) {
    state.user = userData
    console.log(state.user)
  },


  // 2. SocialFeed 조작
  // 1) 포스트 로딩
  LOAD_POSTS: function (state, data) {
    // (1) 선언
    // a. pageNum: 인덱싱을 위한 변수
    // b. lastPostCode: postCode 중 가장 작은 값.  
    const pageNum = state.socialFeed.pageNum
    const lastPostCode = _.last(data).postCode

    // (2) State 갱신
    state.socialFeed.pageNum += 1
    state.socialFeed.posts[pageNum + 1] = data
    state.socialFeed.lastPostCode = lastPostCode
    console.log('LOADPOST', state.socialFeedLoadedAt)
    state.socialFeedLoadedAt = Date.now()
    console.log('LOADPOST', state.socialFeedLoadedAt)
  },
  // 2) 마지막 포스트 도달.
  REACHED_LAST_POST: function (state) {
    state.socialFeed.isAtLast = true
  },

  // 3. 컴포넌트 조작
  TURN_POST_CREATE_MODAL_ON: function () {
    state.modals.postCreateModal = true
  },
  TURN_POST_CREATE_MODAL_OFF: function () {
    state.modals.postCreateModal = false
  },

  //4. 추천 피드
  // 1) 인기콘텐츠 로딩
  SET_CONTENTS: function (state, data) {
    // (1) 선언
    // a. pageNum: 인덱싱을 위한 변수
    // b. lastPostCode: postCode 중 가장 작은 값.  
    // const pageNum = state.curationFeed.pageNum
    const lastPostCode = _.last(data).postCode

    // (2) State 갱신
    state.curationFeed.pageNum += 1
    state.curationFeed.contents = data
    state.curationFeed.lastPostCode = lastPostCode
  },

}
