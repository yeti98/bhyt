<template>
    <div>
        <GroupSetting></GroupSetting>
        <p>Cài đặt mức đóng (%)</p>
        <div style="width: 500px; margin:0 auto;">
            <Form :label-width="200" :model="settingValues" :rules="ruleCustom" label-position="left" ref="formCustom">
                <FormItem label="Mức đóng theo tiền lương cơ sở " prop="BASE_BY_PERCENT">
                    <Input number type="text" v-model="settingValues.BASE_BY_PERCENT"></Input>
                </FormItem>
                <hr>
                <p><b> Nhóm do ngân sách Nhà nước hỗ trợ mức đóng</b></p><br>
                <p>Mức hỗ trợ tối thiểu</p>
                <FormItem label="Người thuộc hộ cận nghèo" prop="MIN_POORER_SUPPORT_IN_PERCENT">
                    <Input number type="text" v-model="settingValues.MIN_POORER_SUPPORT_IN_PERCENT"></Input>
                </FormItem>
                <FormItem label="Học sinh, sinh viên." prop="MIN_STUDENT_SUPPORT_IN_PERCENT">
                    <Input number type="text" v-model="settingValues.MIN_STUDENT_SUPPORT_IN_PERCENT"></Input>
                </FormItem>
                <FormItem label="Hộ GĐ có thu nhập trung bình" prop="MIN_HAVE_AVERAGE_INCOME_SUPPORT_IN_PERCENT">
                    <Input number type="text" v-model="settingValues.MIN_HAVE_AVERAGE_INCOME_SUPPORT_IN_PERCENT"></Input>
                </FormItem>
                <hr>
                <p><b>Hộ gia đình </b></p>
                <FormItem label="Mức đóng người thứ nhất theo TLCS" prop="FIRST_PERSON_RATE">
                    <Input number type="text" v-model="settingValues.FIRST_PERSON_RATE"></Input>
                </FormItem>
                <FormItem label="Người thứ hai" prop="SECOND_PERSON_RATE">
                    <Input number type="text" v-model="settingValues.SECOND_PERSON_RATE"></Input>
                </FormItem>
                <FormItem label="Người thứ ba" prop="THIRD_PERSON_RATE">
                    <Input number type="text" v-model="settingValues.THIRD_PERSON_RATE"></Input>
                </FormItem>
                <FormItem label="Người thứ tư" prop="FOURTH_PERSON_RATE">
                    <Input number type="text" v-model="settingValues.FOURTH_PERSON_RATE"></Input>
                </FormItem>
                <FormItem label="Từ người thứ 5 trở đi" prop="FROM_FIFTH_PERSON_RATE">
                    <Input number type="text" v-model="settingValues.FROM_FIFTH_PERSON_RATE"></Input>
                </FormItem>
            </Form>
            <Form>
                <FormItem>
                    <Button @click="handleSubmit('formCustom')" type="primary">Lưu</Button>
                </FormItem>
            </Form>
        </div>
    </div>
</template>

<script>
  import {RepositoryFactory} from '@/repositories/RepositoryFactory'
  import GroupSetting from "./GroupSetting";

  const SettingRepository = RepositoryFactory.get('setting');
  export default {
    name: "Setting",
    components: {GroupSetting},
    mounted() {
      this.getSetting();
    },
    data() {
      const validatePercent = (rule, value, callback) => {
        if (!value && value !== 0) {
          return callback(new Error('Không được để trống'));
        }
        if (isNaN(value)) {
          callback(new Error('Vui lòng nhập số'));
        } else {
          if (value < 0 || value > 100) {
            callback(new Error('Vui lòng nhập số dương < 100'));
          } else {
            callback();
          }
        }
      };
      const percentValidator = [
        {validator: validatePercent, trigger: 'blur'}
      ]
      return {
        settingValues: {},
        ruleCustom: {
          BASE_BY_PERCENT: percentValidator,
          SECOND_PERSON_RATE: percentValidator,
          THIRD_PERSON_RATE: percentValidator,
          MIN_POORER_SUPPORT_IN_PERCENT: percentValidator,
          FIRST_PERSON_RATE: percentValidator,
          MIN_HAVE_AVERAGE_INCOME_SUPPORT_IN_PERCENT: percentValidator,
          FROM_FIFTH_PERSON_RATE: percentValidator,
          MIN_STUDENT_SUPPORT_IN_PERCENT: percentValidator,
          FOURTH_PERSON_RATE: percentValidator,
        }
      }
    },
    methods: {
      async getSetting() {
        const res = await SettingRepository.fetchSettings();
        if (res.status === 200) {
          this.settingValues = res.data
        }
      },
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
