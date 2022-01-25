<template>
  <div>
    <!-- 1. 검색 아이콘 -->
    <v-btn icon>
      <v-icon>mdi-magnify</v-icon>
    </v-btn>
    <!-- 2. 알람 아이콘 -->
    <v-btn icon>
      <v-icon>mdi-bell</v-icon>
    </v-btn>
    <!-- 3-1. 비 로그인 사용자: 로그인 버튼 -->
    <v-btn
      v-if="isLoggedIn === false"
      icon
    >
      <v-icon>mdi-login</v-icon>
    </v-btn>
    <!-- 3-2. 로그인 된 사용자: 메뉴 확인 -->
    <v-menu
      v-if="isLoggedIn === true"
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
        <!-- 1.프로필 사진 -->
        <v-list-item>
          <v-avatar
            color="indigo"
            size="24"
          >
            <span>SL</span>
          </v-avatar>
          <v-list-item-content>
            <v-list-item-title>Surim Lee</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <!-- 2. 설정 -->
        <v-list-item>
          <v-icon>mdi-cog</v-icon>
          <v-list-item-content>
            <v-list-item-title>설정</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <!-- 3. 로그아웃 -->
        <v-list-item>
          <v-icon>mdi-logout</v-icon>
          <v-list-item-content>
            <v-list-item-title>로그아웃</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-menu>
  </div>
</template>

<script>
export default {
  name: 'TheNavBarIcons',
  data: () => {
    return {
      isLoggedIn: true
    }
  },
}
</script>

<style>
</style>