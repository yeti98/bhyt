<template>
    <div id="barChart"></div>
</template>

<script>
  export default {
    name: "Test",
    data () {
      return {
      }
    },
    mounted() {
    }
  }
</script>

<style scoped>
    body {
        font: 10px sans-serif;
    }

    .axis path,
    .axis line {
        fill: none;
        stroke: #000;
        shape-rendering: crispEdges;
    }

    .x.axis path {
        display: none;
    }
</style>
