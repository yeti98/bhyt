<template>
    <div>
        <Button type="primary" @click="modal1 = true">Display dialog box</Button>
        <Modal
                v-model="modal1"
                title="Common Modal dialog box title"
                @on-ok="ok"
                @on-cancel="cancel">
            <p>Content of dialog</p>
            <p>Content of dialog</p>
            <p>Content of dialog</p>
        </Modal>
        <p>Group setting</p>
        <Table :columns="columns12" :data="categories" border :no-data-text="`Không có dữ liệu`" :loading="loading"
               :no-filtered-data-text="`Không có dữ liệu phù hợp`">
            <template slot="name" slot-scope="{ row }">
                <strong>{{ row.name }}</strong>
            </template>
            <template slot="action" slot-scope="{ row, index }">
                <Button @click="show(index)" size="small" style="margin-right: 5px" type="primary">View</Button>
                <Button @click="remove(index)" size="small" type="error">Delete</Button>
            </template>
        </Table>
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
        columns12: [
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
            key: 'description'
          },
          {
            title: 'Giới hạn dưới',
            key: 'minValue'
          },
          {
            title: '#',
            slot: 'action',
            width: 150,
            align: 'center'
          }
        ],
        categories: [
          {
            name: 'John Brown',
            age: 18,
            address: 'New York No. 1 Lake Park'
          },
          {
            name: 'Jim Green',
            age: 24,
            address: 'London No. 1 Lake Park'
          },
          {
            name: 'Joe Black',
            age: 30,
            address: 'Sydney No. 1 Lake Park'
          },
          {
            name: 'Jon Snow',
            age: 26,
            address: 'Ottawa No. 2 Lake Park'
          }
        ]
      }
    },
    methods: {
      ok(){

      },
      cancel(){

      },
      async getAllGroup() {
        this.loading = true
        const res = await GroupRepository.fetchCategories();
        if (res.status === 200) {
          this.categories = res.data._embedded.categories
          this.loading = false
        }
      },
      show(index) {
        this.$Modal.confirm({
          okText: 'Lưu',
          cancelText: 'Hủy',
          width: 500,
          title: 'Hóa đơn chi tiết',
          scrollable: true,
          closable: true,
          render: (h) => {
            return h(GroupDetail, {
              props: {
                group: this.categories[index],
              }
            })
          }
        })
      },
      remove(index) {
        this.categories.splice(index, 1);
      }
    }
  }
</script>

<style scoped>

</style>
