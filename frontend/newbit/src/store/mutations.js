import state from './state'

export default {
  TURN_POST_CREATE_MODAL_ON: function () {
    state.modals.postCreateModal = true
  },
  TURN_POST_CREATE_MODAL_OFF: function () {
    state.modals.postCreateModal = false
  },

}
