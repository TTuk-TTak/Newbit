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

  // 2. Feed조작
  //
  loadPosts: function () {
    const size = 8
    axios({
      method: 'get',
      url: `${serverURL}/post?`
        + `uid=${state.userCode}`
        + `&lastpostcode=${state.lastPostCode}`
        + `&size=${size}`,
    })
      .then(res => {
        console.log(res)
      })
      .catch((err) => {
        console.log(err)
      })
  },
  // 3. 로그인, 로그아웃

  Login: function ({ commit }) {
    commit('LOGIN')
  },
  Logout: function ({ commit }) {
    commit('LOGOUT')
  }
}
