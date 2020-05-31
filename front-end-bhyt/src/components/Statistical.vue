<template>
    <div>
        <DatePicker ref="datepick" format="dd/MM/yyyy" :options="options2" placeholder="Chọn ngày" placement="bottom-end" style="width: 400px"
                   type="daterange"></DatePicker>
        <Button @click="onClickStatis" type="primary">
            Thống kê
        </Button>
        <Table :columns="tableHeaders" :data="data" :loading="loading" :no-data-text="`Không có dữ liệu`"
               :no-filtered-data-text="`Không có dữ liệu phù hợp`"
               :show-summary="true" :summary-method="handleSummary" border
               ref="table"
        ></Table>
        <Button type="primary" @click="renderBarChart">Render Bar Chart</Button>
        <div id="barChart"></div>
    </div>
</template>

<script>
  import {RepositoryFactory} from '@/repositories/RepositoryFactory'
  const BillRepository = RepositoryFactory.get('bill');
  export default {
    name: "Statistical",
    data() {
      return {
        loading: false,
        startDate: Date(),
        endDate: Date(),
        tableHeaders: [
          {
            title: 'Loại KH',
            key: 'type',
          },
          {
            title: 'Số hoá đơn',
            key: 'size',
          },
          {
            title: 'Tỉ lệ (%)',
            key: 'sizePercentage',
          },
          {
            title: 'Số tiền (VNĐ)',
            key: 'money',
            sortable: true
          },
          {
            title: 'Tỉ lệ doanh thu(%)',
            key: 'moneyPercentage',
            sortable: true
          }
        ],
        data: [
        ],
        options2: {
          shortcuts: [
            {
              text: '1 tuần',
              value() {
                const end = new Date();
                const start = new Date();
                start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
                return [start, end];
              }
            },
            {
              text: '1 tháng',
              value() {
                const end = new Date();
                const start = new Date();
                start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
                return [start, end];
              }
            },
            {
              text: '3 tháng',
              value() {
                const end = new Date();
                const start = new Date();
                start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
                return [start, end];
              }
            }
          ]
        },
        dataBarChart: [
          {
            "categorie": "Student",
            "values": [
              {
                "value": 0,
                "rate": "Not at all"
              },
              {
                "value": 4,
                "rate": "Not very much"
              },
              {
                "value": 12,
                "rate": "Medium"
              },
              {
                "value": 6,
                "rate": "Very much"
              },
              {
                "value": 0,
                "rate": "Tremendously"
              }
            ]
          },
          {
            "categorie": "Liberal Profession",
            "values": [
              {
                "value": 1,
                "rate": "Not at all"
              },
              {
                "value": 21,
                "rate": "Not very much"
              },
              {
                "value": 13,
                "rate": "Medium"
              },
              {
                "value": 18,
                "rate": "Very much"
              },
              {
                "value": 6,
                "rate": "Tremendously"
              }
            ]
          },
          {
            "categorie": "Salaried Staff",
            "values": [
              {
                "value": 3,
                "rate": "Not at all"
              },
              {
                "value": 22,
                "rate": "Not very much"
              },
              {
                "value": 6,
                "rate": "Medium"
              },
              {
                "value": 15,
                "rate": "Very much"
              },
              {
                "value": 3,
                "rate": "Tremendously"
              }
            ]
          },
          {
            "categorie": "Employee",
            "values": [
              {
                "value": 12,
                "rate": "Not at all"
              },
              {
                "value": 7,
                "rate": "Not very much"
              },
              {
                "value": 18,
                "rate": "Medium"
              },
              {
                "value": 13,
                "rate": "Very much"
              },
              {
                "value": 6,
                "rate": "Tremendously"
              }
            ]
          },
          {
            "categorie": "Craftsman",
            "values": [
              {
                "value": 6,
                "rate": "Not at all"
              },
              {
                "value": 15,
                "rate": "Not very much"
              },
              {
                "value": 9,
                "rate": "Medium"
              },
              {
                "value": 12,
                "rate": "Very much"
              },
              {
                "value": 3,
                "rate": "Tremendously"
              }
            ]
          },
          {
            "categorie": "Inactive",
            "values": [
              {
                "value": 6,
                "rate": "Not at all"
              },
              {
                "value": 6,
                "rate": "Not very much"
              },
              {
                "value": 6,
                "rate": "Medium"
              },
              {
                "value": 2,
                "rate": "Very much"
              },
              {
                "value": 3,
                "rate": "Tremendously"
              }
            ]
          }
        ]

      }
    },
    methods: {
      renderBarChart () {
        var margin = {top: 20, right: 20, bottom: 30, left: 40},
          width = 960 - margin.left - margin.right,
          height = 500 - margin.top - margin.bottom;

        var x0 = d3.scale.ordinal()
          .rangeRoundBands([0, width], .1);

        var x1 = d3.scale.ordinal();

        var y = d3.scale.linear()
          .range([height, 0]);

        var xAxis = d3.svg.axis()
          .scale(x0)
          .tickSize(0)
          .orient("bottom");

        var yAxis = d3.svg.axis()
          .scale(y)
          .orient("left");

        var color = d3.scale.ordinal()
          .range(["#ca0020","#f4a582","#d5d5d5","#92c5de","#0571b0"]);

        var svg = d3.select('#barChart').append("svg")
          .attr("width", width + margin.left + margin.right)
          .attr("height", height + margin.top + margin.bottom)
          .append("g")
          .attr("transform", "translate(" + margin.left + "," + margin.top + ")");

        d3.json("./data.json", (error, data) => {

          var categoriesNames = this.dataBarChart.map(function(d) { return d.categorie; });
          var rateNames = this.dataBarChart[0].values.map(function(d) { return d.rate; });

          x0.domain(categoriesNames);
          x1.domain(rateNames).rangeRoundBands([0, x0.rangeBand()]);
          y.domain([0, d3.max(this.dataBarChart, function(categorie) { return d3.max(categorie.values, function(d) { return d.value; }); })]);

          svg.append("g")
            .attr("class", "x axis")
            .attr("transform", "translate(0," + height + ")")
            .call(xAxis);

          svg.append("g")
            .attr("class", "y axis")
            .style('opacity','0')
            .call(yAxis)
            .append("text")
            .attr("transform", "rotate(-90)")
            .attr("y", 6)
            .attr("dy", ".71em")
            .style("text-anchor", "end")
            .style('font-weight','bold')
            .text("Value");

          svg.select('.y').transition().duration(500).delay(1300).style('opacity','1');

          var slice = svg.selectAll(".slice")
            .data(this.dataBarChart)
            .enter().append("g")
            .attr("class", "g")
            .attr("transform",function(d) { return "translate(" + x0(d.categorie) + ",0)"; });

          slice.selectAll("rect")
            .data(function(d) { return d.values; })
            .enter().append("rect")
            .attr("width", x1.rangeBand())
            .attr("x", function(d) { return x1(d.rate); })
            .style("fill", function(d) { return color(d.rate) })
            .attr("y", function(d) { return y(0); })
            .attr("height", function(d) { return height - y(0); })
            .on("mouseover", function(d) {
              d3.select(this).style("fill", d3.rgb(color(d.rate)).darker(2));
            })
            .on("mouseout", function(d) {
              d3.select(this).style("fill", color(d.rate));
            });

          slice.selectAll("rect")
            .transition()
            .delay(function (d) {return Math.random()*1000;})
            .duration(1000)
            .attr("y", function(d) { return y(d.value); })
            .attr("height", function(d) { return height - y(d.value); });

          //Legend
          var legend = svg.selectAll(".legend")
            .data(this.dataBarChart[0].values.map(function(d) { return d.rate; }).reverse())
            .enter().append("g")
            .attr("class", "legend")
            .attr("transform", function(d,i) { return "translate(0," + i * 20 + ")"; })
            .style("opacity","0");

          legend.append("rect")
            .attr("x", width - 18)
            .attr("width", 18)
            .attr("height", 18)
            .style("fill", function(d) { return color(d); });

          legend.append("text")
            .attr("x", width - 24)
            .attr("y", 9)
            .attr("dy", ".35em")
            .style("text-anchor", "end")
            .text(function(d) {return d; });

          legend.transition().duration(500).delay(function(d,i){ return 1300 + 100 * i; }).style("opacity","1");

        });
      },
      async onClickStatis() {
        this.startDate = this.$refs.datepick.$data.internalValue[0]
        this.endDate = this.$refs.datepick.$data.internalValue[1]
        const res = await BillRepository.analyzeBills(this.startDate.getTime(), this.endDate.getTime())
        if (res.status < 200 || res.status > 299) {
          this.$Message.error(`Vui lòng thử lại! ${res.status}`);
        } else {
          this.data = res.data
        }
      },
      handleSummary({columns, data}) {
        const sums = {};
        columns.forEach((column, index) => {
          const key = column.key;
          if (index === 0) {
            sums[key] = {
              key,
              value: `Tổng`
            };
            return;
          } else if (key.indexOf("Percentage") > -1) {
            sums[key] = {
              key,
              value: '100 %'
            };
            return;
          }
          const values = data.map(item => Number(item[key]));
          if (!values.every(value => isNaN(value))) {
            const v = values.reduce((prev, curr) => {
              const value = Number(curr);
              if (!isNaN(value)) {
                return prev + curr;
              } else {
                return prev;
              }
            }, 0);
            sums[key] = {
              key,
              value: v
            };
          } else {
            sums[key] = {
              key,
              value: ''
            };
          }
        });
        return sums;
      }
    }
  }
</script>

<style scoped>

</style>
