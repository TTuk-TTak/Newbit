<template>
  <div id="chart">
    <apexchart
      type="radar"
      height="400"
      width="400"
      :options="chartOptions"
      :series="series"
    ></apexchart>
  </div>
</template>

<script>
export default {
  data: () => ({
    series: [{
      name: 'Series 1',
      data: [8, 3, 2, 6, 9, 1],
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
      xaxis: {
        categories: ['FrontEnd', 'BackEnd', 'Data', 'AI', 'Algorithm', 'Mobile']
      }
    },
  }),
}
</script>

<style>
</style>