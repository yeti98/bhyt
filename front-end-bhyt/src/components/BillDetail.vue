<template>
    <div>
        <Form :label-width="200" label-position="left">
            <FormItem label="ID">
                <p>{{bill.id}}</p>
            </FormItem>
            <FormItem label="Họ tên">
                <p>{{bill.name}}</p>
            </FormItem>
            <FormItem label="CMT/CCCD">
                <p>{{bill.personalID}}</p>
            </FormItem>
            <FormItem label="Ngày sinh">
                <p>{{bill.dateOfBirth}}</p>
            </FormItem>
            <FormItem label="Giới tính">
                <p>{{bill.gender}}</p>
            </FormItem>
            <FormItem label="Mã thẻ">
                <p>{{bill.code}}</p>
            </FormItem>
            <FormItem label="Thời gian đóng">
                <p>{{bill.timeStamp}}</p>
            </FormItem>
            <FormItem label="Số tiền đóng (VNĐ)">
                <p>{{bill.amount}}</p>
            </FormItem>
            <FormItem label="Nhóm">
                <p>{{bill.category}}</p>
            </FormItem>
            <FormItem label="Tiền lương cơ sở (VNĐ)">
                <p>{{bill.baseSalary}}</p>
            </FormItem>
            <FormItem label="Ngày bắt đầu">
                <p>{{bill.startDate}}</p>
            </FormItem>
            <FormItem label="Ngày hết hạn">
                <p>{{bill.expiryDate}}</p>
            </FormItem>
            <FormItem label="Nơi ĐKKCBBĐ">
                <p>{{bill.firstRegistrationPlace}}</p>
            </FormItem>
        </Form>
    </div>
</template>

<script>
  export default {
    name: "BillDetail",
    props: {
      bill: {
        type: Object,
        default: {}
      }
    }
  }
</script>

<style scoped>

</style>
