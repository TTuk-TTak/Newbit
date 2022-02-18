export default {
  user: null,
  modals: {
    postCreateModal: false,
    firstLoginModal: false,
    loginModal: false,
  },
  snackbar:{
    isRendered: false,
    timeout: 1000,
    text: '',
    // messagePresets: {
    //   content: {
    //     "liked": '컨텐츠를 좋아요 했습니다.',
    //     "unliked": '컨텐츠 좋아요를 취소했습니다.',
    //     'archived': '컨텐츠를 아카이빙했습니다.',
    //     'unarchived': '컨텐츠 아카이빙을 취소했습니다.',
    //   },
    //   post: {
    //     "liked": '게시글을 좋아요 했습니다.',
    //     "unliked": '게시글 좋아요를 취소했습니다.',
    //     'archived': '게시글에 댓글을 남겼습니다.',
    //     'unarchived': '게시글 아카이빙을 취소했습니다.',
    //     'wrote': '게시글을 작성했습니다.',
    //     'deleted': '게시글을 삭제했습니다.',
    //   },
    //   comment: {
    //     'wrote': '댓글을 작성했습니다.',
    //     'deleted': '댓글을 삭제했습니다.',
    //   },
    //   reply: {
    //     'wrote': '답글을 작성했습니다.',
    //     'deleted': '답글을 삭제했습니다.',
    //   }
    // }
  }, 

  //  
  socialFeedLoadedAt: null,
  contentFeedLoadedAt: null,
  archivingFeedLoadedAt: null,

  preSelectedKeyword: null,
  searchKeywordPreset: null,

  curationFeed: {

    lastContentCode: 0,
    page: 0,
    sorting: "hot",
    isAtLast: false,
    contents: [],
    preSelectedKeyword: null,
  },


  archivingFeed: {

  },

  embeddedContent: {
    contentCode: 0
  },


  favoredKeyword: [],
  recommendedPeople: [],

  notiCenter:{
    notifications : []
  }
}
