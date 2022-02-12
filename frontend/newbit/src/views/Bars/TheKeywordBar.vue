<template>
    <v-list
      class="pa-0"
    >
      <v-list-group
        class="background ml-0"
        active-class='feedBackground rounded-lg'
        v-for="(category, categoryName, index) of categorizedKeywords"
        :key="`keywordBarCategory` + category + index"
        :prepend-icon="category.icon"
        no-action
      >
        <template v-slot:activator>
          <v-list-item-content
            @click="toggle.categoryName = !toggle.categoryName"
          >
            <v-list-item-title v-text="categoryName"></v-list-item-title>
          </v-list-item-content>
        </template>
        <v-list-item
          v-for="keyword in category.data"
          :key="`keywordBar` + keyword.shownName"
        >
          <v-list-item-content>
            <v-list-item-title v-text="keyword.shownName"></v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list-group>
    </v-list>

</template>

<script>
import { mapGetters } from 'vuex'

export default {
  name: 'TheKeywordBar',
  data: () => ({
    toggle: {
      '개발언어': false, 
      'Front-end': false,
      'Back-end': false,
      '일반': false,
    },
  }),
  computed: {
    ...mapGetters([
      'categorizedKeywords',
    ])
  },
}
</script>

<style>

</style>