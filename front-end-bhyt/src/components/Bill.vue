<template>
    <div>
        <Input ref="searchInput" clearable :autofocus="true" placeholder="Họ tên/Số thẻ/CMND/Nơi ĐKKCBBĐ" style="width: 300px" v-model="searchText"/>
        <Table :columns="tableHeaders" :data="bills" :loading="loading" :no-data-text="`Không có dữ liệu`"
               :no-filtered-data-text="`Không có dữ liệu phù hợp`"
               :show-summary="true" :summary-method="handleSummary" border
               ref="table"
        >
        </Table>
        <hr>
        <Button @click="exportCurrentData" size="large" type="primary">
            <Icon type="ios-download-outline"></Icon>
            Xuất file csv
        </Button>
    </div>
</template>
<script>
  import {RepositoryFactory} from '@/repositories/RepositoryFactory'
  import BillDetail from "./BillDetail";

  const BillRepository = RepositoryFactory.get('bill');
  export default {
    data() {
      return {
        originData: [],
        loading: true,
        searchText: "",
        tableHeaders: [
          {
            title: 'Số thẻ',
            key: 'code',
            filters: [
              {
                label: 'Hộ GĐ',
                value: 'HGĐ'
              },
              {
                label: 'Cá Nhân',
                value: 'CN'
              },
              {
                label: 'Doanh Nghiệp',
                value: 'DN'
              }
            ],
            filterMultiple: false,
            filterMethod(value, row) {
              return row.code.indexOf(value) > -1;
            }
          },
          {
            title: 'Họ tên',
            key: 'name',
            sortable: true
          },
          {
            title: 'CMT/CCCD',
            key: 'personalID'
          },
          {
            title: 'Nhóm',
            key: 'category',
            sortable: true,
            width: 400,
          },
          {
            title: 'Nơi ĐKKCBBĐ',
            key: 'firstRegistrationPlace'
          },
          {
            title: 'Số tiền đóng (VNĐ)',
            key: 'amount',
            sortable: true
          },
          {
            title: 'Ngày hết hạn',
            key: 'expiryDate',
            sortable: true
          },
          {
            title: '',
            key: 'action',
            width: 150,
            align: 'center',
            render: (h, params) => {
              return h('div', [
                h('Button', {
                  props: {
                    type: 'primary',
                    size: 'small'
                  },
                  style: {
                    marginRight: '5px'
                  },
                  on: {
                    click: () => {
                      this.show(params.index)
                    }
                  }
                }, 'Chi tiết'),
                h('Button', {
                  props: {
                    type: 'error',
                    size: 'small'
                  },
                  on: {
                    click: () => {
                      this.remove(params.index)
                    }
                  }
                }, 'Xóa')
              ]);
            }
          }
        ],
        bills: []
      }
    },
    watch: {
      searchText: function () {
        if (this.searchText.length) {
          this.bills = this.originData.filter(value => {
            return value.name.includes(this.searchText) || value.code.includes(this.searchText)
              || value.personalID.includes(this.searchText) || value.firstRegistrationPlace.includes(this.searchText)
          })
        } else {
          this.bills = this.originData
        }
      }
    },
    methods: {
      handleSummary({columns, data}) {
        const sums = {};
        columns.forEach((column, index) => {
          const key = column.key;
          if (index === 0) {
            sums[key] = {
              key,
              value: `Tổng: ${data.length}`
            };
            return;
          } else if (key === "personalID") {
            sums[key] = {
              key,
              value: ''
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
              value: v + ' VNĐ'
            };
          } else {
            sums[key] = {
              key,
              value: ''
            };
          }
        });

        return sums;
      },
      show(index) {
        this.$Modal.info({
          okText: 'Ok',
          width: 500,
          title: 'Hóa đơn chi tiết',
          scrollable: true,
          closable: true,
          render: (h) => {
            return h(BillDetail, {
              props: {
                bill: this.bills[index],
              }
            })
          }
        })
      },
      remove(index) {
        this.$Modal.confirm({
          okText: 'Ok',
          cancelText: 'Không xóa',
          scrollable: true,
          closable: true,
          title: 'Xóa bản ghi',
          onOk: () => {
            this.bills.splice(index, 1)
          },
          content: `Bạn có muốn xóa bản ghi ${this.bills[index].id} ?`
        })
      },
      async getBillData() {
        this.loading = true;
        const res = await BillRepository.getAllBills();
        if (res.status < 200 || res.status > 299) {
          this.$Message.error(`Vui lòng thử lại! ${res.status}`);
        } else {
          this.originData = []
          this.bills = []
          for (const value of res.data._embedded.bills) {
            const cateRes = await BillRepository.getCategoryByBillID(value.id)
            if (cateRes.status < 200 || cateRes.status > 299) {
              this.$Message.error(`Vui lòng thử lại! ${cateRes.status}`);
            } else {
              value.category = cateRes.data.code + " - " + cateRes.data.name
            }
            this.originData.push(value)
            this.bills.push(value)
          }
          this.loading = false
        }
      },
      exportCurrentData() {
        this.$refs.table.exportCsv({
          filename: 'The original data',
          original: false
        });
      }
    },
    mounted() {
      this.getBillData()
    },
    created() {

    },
    activated() {
      this.$refs.searchInput.focus()
    }
  }
</script>
