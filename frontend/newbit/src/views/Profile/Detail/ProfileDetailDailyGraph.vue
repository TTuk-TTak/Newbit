<template>
  <apexchart
    type="heatmap"
    height="300"
    :options="chartOptions"
    :series="series"
  ></apexchart>
</template>

<script>
export default {
  name: "Heatmap",
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
        // xaxis: {
        //   type: "category",
        // },
        xaxis: {
          categories: ['7월', '8월', '9월', '10월', '11월', '12월']
        }
      },
      series: [
        {
          name: "Sun",
          data: this.generateData(24, {
            min: 0,
            max: 90,
          }),
        },
        {
          name: "Sat",
          data: this.generateData(24, {
            min: 0,
            max: 90,
          }),
        },
        {
          name: "Fri",
          data: this.generateData(24, {
            min: 0,
            max: 90,
          }),
        },
        {
          name: "Thu",
          data: this.generateData(24, {
            min: 0,
            max: 90,
          }),
        },
        {
          name: "Wed",
          data: this.generateData(24, {
            min: 0,
            max: 90,
          }),
        },
        {
          name: "Tue",
          data: this.generateData(24, {
            min: 0,
            max: 90,
          }),
        },
        {
          name: "Mon",
          data: this.generateData(24, {
            min: 0,
            max: 90,
          }),
        },
      ],
    };
  },
  methods: {
    generateData (count, yrange) {
      var i = 0;
      var series = [];
      while (i < count) {
        var x = `${i}:00`.toString();
        var y =
          Math.floor(Math.random() * (yrange.max - yrange.min + 1)) +
          yrange.min;

        series.push({
          x: x,
          y: y,
        });
        i++;
      }
      return series;
    },
  },
};
</script>
