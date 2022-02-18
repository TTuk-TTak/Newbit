import state from './state'
import axios from 'axios'

const serverURL = process.env.VUE_APP_SERVER_URL

export default {
  // 1. 모달 조작
  // 1) PostCreateModal
  turnPostCreateModalOn: function ({ commit }, payload) {
    commit('TURN_POST_CREATE_MODAL_ON', payload)
  },
  turnPostCreateModalOFF: function ({ commit }) {
    commit('TURN_POST_CREATE_MODAL_OFF')
  },

  // 2) FirstLoginModal
  turnFirstLoginModalOn: function ({ commit }) {
    commit('TURN_FIRST_LOGIN_MODAL_ON')
  },
  turnFirstLoginModalOFF: function ({ commit }) {
    commit('TURN_FIRST_LOGIN_MODAL_OFF')
  },
  // 3) LoginModal
  turnLoginModalOn: function ({ commit }) {
    commit('TURN_LOGIN_MODAL_ON')
  },
  turnLoginModalOFF: function ({ commit }) {
    commit('TURN_LOGIN_MODAL_OFF')
  },
  // 4) SnackBar
  turnSnackBarOn: function ({ commit }, snackbarText) {
    console.log(snackbarText, '스낵스낵')
    commit('TURN_SNACKBAR_ON', snackbarText)
  },


  // // 2. Feed조작
  socialFeedLoadedAt: function ({ commit }) {
    commit('SOCIAL_FEED_LOADED_AT')
  },
  contentFeedLoadedAt: function ({ commit }) {
    commit('CONTENT_FEED_LOADED_AT')
  },
  archivingFeedLoadedAt: function ({ commit }) {
    commit('ARCHIVING_FEED_LOADED_AT')
  },

  // 3. 유저 정보 저장 & 초기화

  saveUserInformation: function ({ commit }, userData) {
    commit('SAVE_USER_INFORMATION', userData)
  },

  resetUserInformation: function ({ commit }) {
    commit('RESET_USER_INFORMATION')
  },

  saveUserKeyword: function ({ commit }, keywordString) {
    commit('SAVE_USER_KEYWORD', keywordString)
  },

  saveFavoredKeyword: function ({ commit }, parsedKeyword) {
    commit('SAVE_FAVORED_KEYWORD', parsedKeyword)
  },

  saveRecommendedPeople: function ({ commit }, recommended) {
    commit('SAVE_RECOMMENDED_PEOPLE', recommended)
  },
  // 4. 추천피드 조작
  getContentsHot: ({ commit }) => {
    const size = 10
    const keywordchip = "react"
    console.log(state.curationFeed)
    axios({
      method: 'get',
      url: `${serverURL}/content/list?sorting=hot&`
        // + `uid=${state.userCode}`
        // 로그인 안 한 사용자 테스트용 1
        + `uid=1`
        + `&lastcontentcode=0`
        + `&size=${size}`
        + `&keyword=${keywordchip}`
    })
      .then(res => {
        if (res.data.length) {
          commit('SET_CONTENTS', res.data)
          console.log(res)
        } else {
          commit('REACHED_LAST_CONTENT')
        }
        console.log('Actions-loadContent', res)
      })
      .catch((err) => {
        console.log(err)
      })
  },
  
  // 5. 추천 피드 - 키워드 미리 선택
  presetCurationKeyword: ({ commit }, keyword) => {
    commit('PRESET_CURATION_KEYWORD', keyword)
  },
  // 4) SearchModal
  presetSearchKeyword: function ({ commit }, searchKeyword) {
    console.log(searchKeyword, '검색어 이동!')
    commit('PRESET_SEARCH_KEYWORD', searchKeyword)
  },


  //6. 알림
  getNotification: ({ commit }) => {
    const user_code = localStorage.getItem('user_code')
    axios({
      method: 'get',
      url: `${serverURL}/noti?`
        + `uid=${user_code}`
        // + `uid=1`
    })
      .then(res => {
        if (res.data.length) {
          commit('SET_NOTIFICATION', res.data)
          console.log('알림센터', res)
        }
      })
      .catch((err) => {
        console.log(err)
      })
  },

  deleteNotifications: function ({ commit }) {
    commit('DELETE_NOTIFICATION')
  },
}
