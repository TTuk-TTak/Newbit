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
    <v-btn
      icon
      class="mr-4"
    >
      <v-icon>mdi-bell</v-icon>
    </v-btn>
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
      v-if="$store.state.user"
      left
      bottom
    >
      <template v-slot:activator="{ on, attrs }">
        <v-btn
          icon
          v-bind="attrs"
          v-on="on"
        >
          <v-avatar
            color="indigo"
            size="36"
          >
            <span>36</span>
          </v-avatar>
        </v-btn>
      </template>
      <v-list>
        <!-- 1) 프로필 사진 -->
        <v-list-item @click="$goToProfileDetail()">
          <v-avatar
            color="indigo"
            size="24"
          >
            <span>SL</span>
          </v-avatar>
          <v-list-item-content class="ml-2">
            <v-list-item-title>{{$store.state.user.userNick}}</v-list-item-title>
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

export default {
  name: 'TheNavBarIcons',
  components: {
    SearchModal,
  },
  data: () => {
    return {

    }
  },
}
</script>

<style scoped>
#searchModal {
  position: absolute !important;
  top: 48px !important;
}

.v-list-item{
  background-color: white;
  width:200px; 
}

</style>