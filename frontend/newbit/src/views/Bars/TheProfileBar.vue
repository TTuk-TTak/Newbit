<template>
  <div>
    <profile-own-info></profile-own-info>
    <favored-keyword-bar :is-vertical="isVertical"></favored-keyword-bar>
    <profile-follow-rec></profile-follow-rec>
  </div>
</template>

<script>
import ProfileOwnInfo from '@/components/Bars/ProfileBar/ProfileOwnInfo.vue'
import ProfileFollowRec from '@/components/Bars/ProfileBar/ProfileFollowRec.vue'
import FavoredKeywordBar from '@/components/Keyword/FavoredKeywordBar.vue'

export default {
  name: 'TheProfileBar',
  components: {
    ProfileOwnInfo,
    FavoredKeywordBar,
    ProfileFollowRec
  },
  data: () => ({
    isVertical: true
  }),
}
</script>

<style>
</style>