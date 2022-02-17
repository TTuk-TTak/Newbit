<template>
  <apexchart
    type="heatmap"
    height="225"
    :options="chartOptions"
    :series="series"
  ></apexchart>
</template>

<script>
export default {
  name: "Heatmap",
  props: {
    week: Object,
  },
  data: function () {
    return {
      chartOptions: {
        dataLabels: {
          enabled: false,
        },
        chart: {
          toolbar: {
            show: false,
          },
        },
        colors: ["#008FFB"],
        stroke: {
          show: true,
          width: 0.03,
          colors: ['#000000'],
        },
        // xaxis: {
        //   type: "category",
        // },
        xaxis: {
          labels: {
            show: false
          }
          // categories: ['7월', '8월', '9월', '10월', '11월', '12월']
        },
        plotOptions: {
          heatmap: {
            shadeIntensity: -100,
            radius: 0,
            useFillColorAsStroke: false,
          }
        },
      },
      series: [
        {
          name: "Sun",
          data: this.week['sun']
        },
        {
          name: "Sat",
          data: this.week['sat']
        },
        {
          name: "Fri",
          data: this.week['fri']
        },
        {
          name: "Thu",
          data: this.week['thu']
        },
        {
          name: "Wed",
          data: this.week['wed']
        },
        {
          name: "Tue",
          data: this.week['tue']
        },
        {
          name: "Mon",
          data: this.week['mon']
        },
      ],
    };
  },
  // methods: {
  //   generateData (count, yrange) {
  //     var i = 0;
  //     var series = [];
  //     while (i < count) {
  //       var x = `${i}:00`.toString();
  //       var y =
  //         Math.floor(Math.random() * (yrange.max - yrange.min + 1)) +
  //         yrange.min;

  //       series.push({
  //         x: x,
  //         y: y,
  //       });
  //       i++;
  //     }
  //     return series;
  //   },
  // },
};
</script>
