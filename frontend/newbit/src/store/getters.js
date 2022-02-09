// import _ from 'lodash'
import { keywords } from '@/plugins/Keywords'

export default {
  categorizedKeywords: function () {
    const categorizedKeywords = {
      '개발언어': {icon: 'mdi-code-tags', data: {}},
      'Front-end': {icon: 'mdi-monitor', data: {}},
      'Back-end': {icon: 'mdi-database', data: {}},
      '일반': {icon: 'mdi-human-male-female-child', data: {}},
    }
    for (let key in keywords) {
      const category = keywords[key].category
      categorizedKeywords[category]['data'][key] = {
        shownName: keywords[key].shownName,
        isSelected: false,
        isActive: false,
      }
    }
    console.log(categorizedKeywords)
    return categorizedKeywords
  },
  selectableKeyword: function () {
    return keywords
  }
}
