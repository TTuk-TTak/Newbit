<template>
  <div id="chart">
    <apexchart
      type="radar"
      height="300"
      width="300"
      :options="chartOptions"
      :series="series"
    ></apexchart>
  </div>
</template>

<script>

export default {
  props: {
    category: Array,
    preference: Array,
  },

  data: function () {
    return {
      series: [{
        name: 'Series 1',
        data: this.preference
      }],
      chartOptions: {
        chart: {
          type: 'radar',
          toolbar: {
            show: false
          },
        },
        yaxis: {
          // 최대, 최소 및 간격 개수
          // tickAmount: 5,
          // max: 5,
          // min: 0,
        },
        xaxis: {
          categories: this.category
        }
      },
    }
  },
}
</script>

<style>
</style>