<template>
  <div>
    <!-- 1. 검색 아이콘 및 다이얼로그 -->
    <v-dialog overlay-opacity=0.1>
      <template v-slot:activator="{ on, attrs }">
        <v-btn
          class="mr-3"
          elevation="0"
          v-bind="attrs"
          v-on="on"
          icon
        >
          <v-icon>mdi-magnify</v-icon>
        </v-btn>
      </template>
      <!-- color="rgba(26, 26, 26, 0.2)">마지막으로 본 게시글 중 가장 낮은 번호 혹은 0,  -->
      <v-card>
        <v-card-text>
          <search-modal></search-modal>
        </v-card-text>
      </v-card>

    </v-dialog>
    <!-- 2. 알람 아이콘 -->
    <v-menu offset-y left >
      <template v-slot:activator="{ on, attrs }">
        <v-badge
          color="grey"
          offset-x="51"
          offset-y="-1"
        >
          <span slot="badge"> {{ notiCenter.notifications.length }} </span>
        </v-badge>
        <v-btn
          icon
          class="mr-7"
          v-bind="attrs"
          v-on="on"
        >
          <v-icon>mdi-bell</v-icon>
        </v-btn>
      </template>
      <v-list class="scrollbox" min-width=380px style="height: 600px;">
        
        <v-list-item
          v-if="!$store.state.user">
          <v-list-item-content style="width:380px; white-space:nowrap; overflow:visible">로그인 후 Newbit의 모든 기능을 이용해보세요!</v-list-item-content>
        </v-list-item>
        <v-list-item 
          v-else-if="notiCenter.notifications<1">
          <v-list-item-content style="width:380px; white-space:nowrap; overflow:visible">알림이 존재하지 않습니다.</v-list-item-content>
        </v-list-item>

        <v-list-item v-else
          v-for="(notification, index) in notiCenter.notifications"
          :key="index"
          class ="itemhover ma-0 pa-0 pl-1"
        >
          <v-list-item-content class="my-1 ml-2 px-4" @click="goTo(notification.moving, notification.type)" style="width:380px; font-size:1.05em; white-space:nowrap; overflow:visible">'{{ notification.userNick}}'{{notification.type | doing}} · {{$createdAt(notification.date)}}
          <div class="singleline-ellipsis" style="color : rgb(170 170 170); font-weight: 100;"> {{ notification.text}}</div>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-menu>


    <!-- 3-1. 비 로그인 사용자: 로그인 버튼 -->
    <v-btn
      v-if="!$store.state.user"
      icon
      @click="$goToLoginPage()"
    >
      <v-icon>mdi-login</v-icon>
    </v-btn>
    <!-- 3-2. 로그인 된 사용자: 메뉴 확인 -->
    <v-menu 
      offset-y
      v-if="user"
      left
      bottom
    >
      <template v-slot:activator="{ on, attrs }">
        <v-btn
          icon
          v-bind="attrs"
          v-on="on"
        >
          <v-avatar size='36'>
            <img :src="user.userImg">
          </v-avatar>
        </v-btn>
      </template>
      <v-list>
        <!-- 1) 프로필 사진 -->
        <v-list-item @click="$goToMyProfile()">
          <v-avatar size='24'>
            <img :src="user.userImg">
          </v-avatar>
          <v-list-item-content class="ml-2">
            <v-list-item-title>{{user.userNick}}</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-divider></v-divider>
        <!-- 2. 설정 -->
        <v-list-item>
          <v-icon>mdi-cog</v-icon>
          <v-list-item-content class="ml-2">
            <v-list-item-title>설정</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <!-- 3) 로그아웃 -->
        <v-list-item @click="$logout()">
          <v-icon>mdi-logout</v-icon>
          <v-list-item-content class="ml-2">
            <v-list-item-title>로그아웃</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-menu>
  </div>
</template>

<script>

import SearchModal from '@/components/Modals/SearchModal.vue'
import { mapState } from 'vuex'

export default {
  name: 'TheNavBarIcons',
  components: {
    SearchModal,
  },
  data: () => {
    return {
       notifications: [
        { title: '알림이 존재하지 않습니다.' },
      ],

    }
  },
  created() {
    this.$store.dispatch('getNotification')
  },
  computed: {
    ...mapState([
      'user', 'notiCenter'
    ]),
  },
  methods: {
    goTo(moving, type) {
      if(type=="follow") this.$router.push({ name: 'ProfileDetail', params: { userCode: moving } })
      else this.$router.replace({name: 'PostDetail', params: {id: moving}})
    },
    getNotification() {
      this.$store.dispatch('getNotification')
    }
  },
  filters: {
    doing(type) {
      if (type == "follow") return "님이 나를 팔로우 했습니다."
      else if (type == "comment") return "님이 내 글에 댓글을 남겼습니다."
      else if (type == "like") return "님이 내 글에 좋아요 했습니다."
    },
  },
  mounted() {
    setInterval(this.getNotification, 60000); //1분마다 실행
  },
}
</script>

<style scoped>
.v-menu__content--fixed::-webkit-scrollbar {
  display: none !important; /* Chrome, Safari, Opera*/
}

#searchModal {
  position: absolute !important;
  top: 48px !important;
}

.v-list-item {
  background-color: white;
  width: 200px;
}

.singleline-ellipsis {
  white-space: nowrap;
  width:320px;
  overflow: hidden;
  text-overflow: ellipsis;
}

.itemhover :hover{
  background-color:#f3f3f3 !important;
}
</style>