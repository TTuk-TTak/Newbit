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
          content="2"
          offset-x="38"
          offset-y="23"
        >
        <v-btn
          icon
          class="mr-4"
          v-bind="attrs"
          v-on="on"
        >
          <v-icon>mdi-bell</v-icon>
        </v-btn>
        </v-badge>
      </template>
      <v-list min-width=300px>
        
        <v-list-item 
          v-if="!$store.state.user">
          <v-list-item-title style="width:300px">알림이 존재하지 않습니다.</v-list-item-title>
        </v-list-item>
        <v-list-item 
          v-else-if="notiCenter.notifications<1">
          <v-list-item-title style="width:300px">알림이 존재하지 않습니다.</v-list-item-title>
        </v-list-item>

        <v-list-item v-else
          v-for="(notification, index) in notiCenter.notifications"
          :key="index"
        >
          <v-list-item-content style="width:300px; white-space:nowrap; overflow:visible">{{ notification.userNick | follow }}</v-list-item-content>
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
  filters: {
    follow(nickName) {
      return ("'"+nickName + "' 님이 팔로우 했습니다.")
    },
  }
}
</script>

<style scoped>
#searchModal {
  position: absolute !important;
  top: 48px !important;
}

.v-list-item {
  background-color: white;
  width: 200px;
}
</style>