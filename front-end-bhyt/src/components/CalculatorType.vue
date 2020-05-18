<template>
    <Select v-model="raw" filterable not-found-text="Không có kết quả">
        <Option value="BY_PERCENT">Theo phần trăm TLCS/Lương hưu/Trợ cấp</Option>
        <Option value="FAMILY">Theo Hộ Gia Đình</Option>
        <Option value="SUPPORTED">Ngân sách Nhà nước hỗ trợ</Option>
    </Select>
</template>

<script>
  export default {
    name: "CalculatorType",
    props: {
      selected: {
        type: String,
        default: {}
      }
    },
    data () {
      return {
        raw: this.selected
      }
    },
    watch : {
      raw: function () {
        this.$emit("changeCalcType", this.raw)
      }
    }
  }
</script>

<style scoped>

</style>
