<template>
    <GroupSetting></GroupSetting>
</template>

<script>
  import {RepositoryFactory} from '@/repositories/RepositoryFactory'
  import GroupSetting from "./GroupSetting";
  import {ON_FAILURE_MESSAGE, ON_SAVED_MESSAGE} from "../javascript/api_messages";

  const SettingRepository = RepositoryFactory.get('setting');
  export default {
    name: "Setting",
    components: {GroupSetting},
    methods: {
      handleSubmit(name) {
        this.$refs[name].validate((valid) => {
          if (valid) {
            this.$Message.success(ON_SAVED_MESSAGE);
          } else {
            this.$Message.error(ON_FAILURE_MESSAGE);
          }
        })
      }
    }
  }
</script>

<style scoped>

</style>
