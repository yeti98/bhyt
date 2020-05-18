<template>
    <GroupSetting></GroupSetting>
</template>

<script>
  import {RepositoryFactory} from '@/repositories/RepositoryFactory'
  import GroupSetting from "./GroupSetting";

  const SettingRepository = RepositoryFactory.get('setting');
  export default {
    name: "Setting",
    components: {GroupSetting},
    methods: {
      handleSubmit(name) {
        this.$refs[name].validate((valid) => {
          if (valid) {
            this.$Message.success('Đã lưu!');
          } else {
            this.$Message.error('Không thành công!');
          }
        })
      }
    }
  }
</script>

<style scoped>

</style>
