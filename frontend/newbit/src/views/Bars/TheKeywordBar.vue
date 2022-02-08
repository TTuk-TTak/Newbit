<template>
    <v-list
      class="pa-0"
    >
      <v-list-group
        class="background ml-0"
        active-class='feedBackground rounded-lg'
        v-for="(item, index) of items"
        :key="item.title"
        v-model="item.active"
        :prepend-icon="iconArray[index]"
        no-action
      >
        <template v-slot:activator>
          <v-list-item-content>
            <v-list-item-title v-text="item.title"></v-list-item-title>
          </v-list-item-content>
        </template>
        <v-list-item
          v-for="child in item.items"
          :key="child.title"
        >
          <v-list-item-content>
            <v-list-item-title v-text="child.title"></v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list-group>
    </v-list>

</template>

<script>
export default {
  name: 'TheKeywordBar',
  data: () => ({
    // 수정 예정.
    // https://lodash.com/docs/4.17.15#invertBy
    iconArray: ['mdi-code-tags', 'mdi-monitor', 'mdi-database', 'mdi-human-male-female-child'],
    items: [
      {
        action: 'mdi-star',
        title: '내 키워드',
        items: [
          { title: 'Python' },
          { title: 'Vue.js' },
          { title: 'UI/UX' },
          { title: 'Kotlin' },
        ],
      },
      {
        action: 'mdi-code-tags',
        title: '언어',
        items: [
          { title: 'C' },
          { title: 'C#' },
          { title: 'Java' },
          { title: 'Python' },
          { title: 'Javascript' },
          { title: 'Kotlin' },
        ],
      },
      {
        action: 'mdi-monitor',
        title: '프론트엔드',
        active: true,
        items: [
          { title: 'React' },
          { title: 'Vue.js' },
          { title: 'Angular' },
        ],
      },
      {
        action: 'mdi-database',
        title: '백엔드',
        items: [
          { title: 'Database' },
          { title: '서버' },
          { title: 'Database' },
          { title: 'SQL' },
          ],
      },
      {
        action: 'mdi-human-male-female-child',
        title: '기술 일반',
        items: [{ title: 'List Item' }],
      },
    ],
  }),
  computed: {
    keywords: function () {
      return false
    }
  },
}
</script>

<style>

</style>