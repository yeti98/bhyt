<template>
    <div>
        <Form :label-width="200" :model="group" :rules="ruleCustom" label-position="left" ref="formCustom">
<!--            <FormItem label="Mức đóng theo tiền lương cơ sở " prop="BASE_BY_PERCENT">-->
<!--                <Input number type="text" v-model="settingValues.BASE_BY_PERCENT"></Input>-->
<!--            </FormItem>-->
<!--            <hr>-->
<!--            <p><b> Nhóm do ngân sách Nhà nước hỗ trợ mức đóng</b></p><br>-->
<!--            <p>Mức hỗ trợ tối thiểu</p>-->
<!--            <FormItem label="Người thuộc hộ cận nghèo" prop="MIN_POORER_SUPPORT_IN_PERCENT">-->
<!--                <Input number type="text" v-model="settingValues.MIN_POORER_SUPPORT_IN_PERCENT"></Input>-->
<!--            </FormItem>-->
<!--            <FormItem label="Học sinh, sinh viên." prop="MIN_STUDENT_SUPPORT_IN_PERCENT">-->
<!--                <Input number type="text" v-model="settingValues.MIN_STUDENT_SUPPORT_IN_PERCENT"></Input>-->
<!--            </FormItem>-->
<!--            <FormItem label="Hộ GĐ có thu nhập trung bình" prop="MIN_HAVE_AVERAGE_INCOME_SUPPORT_IN_PERCENT">-->
<!--                <Input number type="text" v-model="settingValues.MIN_HAVE_AVERAGE_INCOME_SUPPORT_IN_PERCENT"></Input>-->
<!--            </FormItem>-->
<!--            <hr>-->
<!--            <p><b>Hộ gia đình </b></p>-->
<!--            <FormItem label="Mức đóng người thứ nhất theo TLCS" prop="FIRST_PERSON_RATE">-->
<!--                <Input number type="text" v-model="settingValues.FIRST_PERSON_RATE"></Input>-->
<!--            </FormItem>-->
<!--            <FormItem label="Người thứ hai" prop="SECOND_PERSON_RATE">-->
<!--                <Input number type="text" v-model="settingValues.SECOND_PERSON_RATE"></Input>-->
<!--            </FormItem>-->
<!--            <FormItem label="Người thứ ba" prop="THIRD_PERSON_RATE">-->
<!--                <Input number type="text" v-model="settingValues.THIRD_PERSON_RATE"></Input>-->
<!--            </FormItem>-->
<!--            <FormItem label="Người thứ tư" prop="FOURTH_PERSON_RATE">-->
<!--                <Input number type="text" v-model="settingValues.FOURTH_PERSON_RATE"></Input>-->
<!--            </FormItem>-->
<!--            <FormItem label="Từ người thứ 5 trở đi" prop="FROM_FIFTH_PERSON_RATE">-->
<!--                <Input number type="text" v-model="settingValues.FROM_FIFTH_PERSON_RATE"></Input>-->
<!--            </FormItem>-->
        </Form>
        <Form>
        </Form>
    </div>
</template>

<script>
  export default {
    name: "GroupDetail",
    props:{
      group: {
        type: Object,
        default: {}
      }
    }
  }
</script>

<style scoped>

</style>
