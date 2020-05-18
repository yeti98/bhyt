<template>
    <div>
        <p><b>Quản lý nhóm tham gia BHYT</b></p>
        <div style="margin: 1%">
            <Table :columns="headers" :data="categories" :loading="loading" :no-data-text="`Không có dữ liệu`"
                   :no-filtered-data-text="`Không có dữ liệu phù hợp`"
                   border>
                <template slot="name" slot-scope="{ row }">
                    <strong>{{ row.name }}</strong>
                </template>
                <template slot="action" slot-scope="{ row, index }">
                    <Button @click="show(index)" size="small" style="margin-right: 5px" type="primary">Sửa</Button>
                    <Button @click="remove(index)" size="small" type="error">Xóa</Button>
                </template>
            </Table>
        </div>
        <Button @click="handleAddGroup" icon="md-add" long style="width: 15%; margin: 1%" type="dashed">Thêm nhóm
        </Button>
    </div>
</template>

<script>
  import {RepositoryFactory} from '@/repositories/RepositoryFactory'
  import GroupDetail from "./GroupDetail";

  const GroupRepository = RepositoryFactory.get('group');
  export default {
    name: "GroupSetting",
    mounted() {
      this.getAllGroup();
    },
    data() {
      return {
        loading: true,
        modal1: false,
        newGroup: this.initNewGroup(),
        headers: [
          {
            title: 'Mã nhóm',
            key: 'code',
            width: 100
          },
          {
            title: 'Tên nhóm',
            key: 'name'
          },
          {
            title: 'Giá trị (%)',
            key: 'value'
          },
          {
            title: 'Mô tả',
            key: 'valueDescription'
          },
          {
            title: 'Giới hạn dưới',
            key: 'minSupportedPercent'
          },
          {
            title: '#',
            slot: 'action',
            width: 150,
            align: 'center'
          }
        ],
        categories: [],
      }
    },
    methods: {
      initNewGroup() {
        return {
          code: "",
          name: "",
          type: "BY_PERCENT",
          defaultBaseSalary: 0,
          value: 0,
          minSupportedPercent: 0,
          valueDescription: ''
        }
      },
      ok() {

      },
      cancel() {

      },
      async getAllGroup() {
        this.loading = true;
        const res = await GroupRepository.fetchCategories();
        if (res.status < 200 || res.status > 299) {
          this.$Message.error(`Vui lòng thử lại! ${res.status}`);
        } else {
          this.categories = res.data._embedded.categories;
          this.loading = false
        }
      },
      show(index) {
        this.$Modal.info({
          okText: 'Đóng',
          width: 700,
          title: 'Thay đổi thông tin nhóm',
          scrollable: true,
          closable: true,
          render: (h) => {
            return h(GroupDetail, {
              props: {
                group: this.categories[index],
                isNew: false
              }
            })
          }
        })
      },
      remove(index) {
        this.categories.splice(index, 1);
      },
      handleAddGroup() {
        this.$Modal.info({
          okText: 'Đóng',
          width: 700,
          title: 'Thêm nhóm',
          scrollable: true,
          closable: true,
          render: (h) => {
            return h(GroupDetail, {
              props: {
                group: this.newGroup,
                isNew: true
              },
              on: {
                'insertGroupSuccess': () => {
                  this.categories.push(this.newGroup);
                  this.newGroup = this.initNewGroup();
                }
              }
            })
          }
        })
      }
    }
  }
</script>

<style scoped>

</style>
