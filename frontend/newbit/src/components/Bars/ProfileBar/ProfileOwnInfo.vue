<template>
  <v-container>
    <v-row>
      <v-col cols="6">
        <v-avatar></v-avatar>
        <v-img
          class="v-avatar image"
          src="https://www.gravatar.com/avatar/default?s=200&r=pg&d=mm"
        />
      </v-col>
    </v-row>
    <v-row>
      <v-col cols="6">
        <h1>hello</h1>
      </v-col>
      <v-col cols="6">
        <v-btn
          block
          rounded
        >
          hello
        </v-btn>
      </v-col>
    </v-row>
    <div class="d-flex justify-space-between">
      <span>게시물</span>
      <span>팔로워</span>
      <span>팔로잉</span>
    </div>
    <v-btn
      rounded
      block
      color="black"
      dark
      class="font-weight-bold"
    >
      글작성
    </v-btn>
  </v-container>
</template>

<script>
export default {

}
</script>

<style>
</style>