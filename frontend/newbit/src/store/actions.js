import state from './state'
import axios from 'axios'

const serverURL = process.env.VUE_APP_SERVER_URL

export default {
  turnPostCreateModalOn: function ({commit}) {
    commit('TURN_POST_CREATE_MODAL_ON')
  },
  turnPostCreateModalOFF: function ({commit}) {
    commit('TURN_POST_CREATE_MODAL_OFF')
  },

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
  }
}
