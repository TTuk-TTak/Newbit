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
          // height: 350,
          toolbar: {
            show: false
          },
        },
        // title: {
        //   text: 'Basic Radar Chart'
        // },
        yaxis: {
          tickAmount: 5,
          max: 5,
          min: 0,
        },
        xaxis: {
          categories: this.category
          // categories: ['FrontEnd', 'BackEnd', 'Data', 'AI', 'Algorithm', 'Mobile']
        }
      },
    }
  },
}
</script>

<style>
</style>