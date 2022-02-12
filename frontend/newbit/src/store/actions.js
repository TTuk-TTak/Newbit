import state from './state'
import axios from 'axios'

const serverURL = process.env.VUE_APP_SERVER_URL

export default {
  // 1. 모달 조작
  // 1) PostCreateModal
  turnPostCreateModalOn: function ({ commit }) {
    commit('TURN_POST_CREATE_MODAL_ON')
  },
  turnPostCreateModalOFF: function ({ commit }) {
    commit('TURN_POST_CREATE_MODAL_OFF')
  },
  // // 2. Feed조작
  // loadPosts: function ({ commit }) {
  //   const size = 8
  //   axios({
  //     method: 'get',
  //     url: `${serverURL}/post/list?`
  //       + `uid=${state.user.userCode}`
  //       + `&lastpostcode=${state.socialFeed.lastPostCode}`
  //       + `&size=${size}`,
  //   })
  //     .then(res => {
  //       if (res.data.length) {
  //         commit('LOAD_POSTS', res.data)
  //       } else {
  //         commit('REACHED_LAST_POST')
  //       }
  //       console.log('Actions-loadPost', res)
  //     })
  //     .catch((err) => {
  //       console.log(err)
  //     })
  // },

  // 3. 유저 정보 저장 & 초기화

  saveUserInformation: function ({ commit }, userData) {
    commit('SAVE_USER_INFORMATION', userData)
  },

  resetUserInformation: function ({ commit }) {
    commit('RESET_USER_INFORMATION')
  },

  // 4. 추천피드 조작
  getContentsHot: ({commit}) => {
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
}
