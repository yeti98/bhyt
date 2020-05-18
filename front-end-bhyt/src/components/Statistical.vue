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
        }
      }
    },
    methods: {
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
