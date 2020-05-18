<template>
    <div>
        <Form :label-width="210" :model="group" :rules="ruleCustom" label-position="left" ref="tblGroup">
            <FormItem label="Mã nhóm" prop="code" v-if="!isNew">
                <p>{{group.code}}</p>
            </FormItem>
            <FormItem label="Mã nhóm" prop="code" v-else>
                <Input number type="text" v-model="group.code"></Input>
            </FormItem>
            <FormItem label="Tên nhóm" prop="name">
                <Input number type="text" v-model="group.name"></Input>
            </FormItem>
            <FormItem label="Cách tính bảo hiểm" prop="type">
                <CalculatorType :selected="group.type" @changeCalcType="updateCalcType"></CalculatorType>
            </FormItem>
            {{group.type}} - {{group.defaultBaseSalary}}
            <FormItem :label="`Giá trị (${this.customValue} - 100 %)`" prop="value">
                <InputNumber :min="customValue" v-model="group.value"></InputNumber>
            </FormItem>
            <FormItem label="Mô tả" prop="valueDescription">
                <Input number type="text" v-model="group.valueDescription"></Input>
            </FormItem>
            <FormItem label="Giới hạn dưới (0 - 100%)" prop="customValue" v-show="group.type==='SUPPORTED'">
                <InputNumber :max="100" :min="0" v-model="customValue"></InputNumber>
            </FormItem>
            <FormItem label="Lương cơ sở mặc định (VNĐ)" prop="defaultBaseSalary" v-show="group.type==='SUPPORTED'">
                <InputNumber style="width: 200px;" :min="0" v-model="group.defaultBaseSalary"></InputNumber>
            </FormItem>
            <FormItem>
                <Button @click="handleSubmit('tblGroup')" type="primary">Lưu</Button>
            </FormItem>

        </Form>
    </div>
</template>

<script>
  import CalculatorType from "./CalculatorType";
  import {RepositoryFactory} from '@/repositories/RepositoryFactory'
  const GroupRepository = RepositoryFactory.get('group');
  export default {
    name: "GroupDetail",
    components: {CalculatorType},
    props: {
      group: {
        type: Object,
        default: {}
      },
      isNew: {
        type: Boolean,
        default: false
      }
    },
    data() {
      const requiredValidator = {required: true, message: 'Không được để trống', trigger: 'change'};
      return {
        customValue: this.group.minSupportedPercent || 0,
        ruleCustom: {
          code: [
            requiredValidator,
            {required: true, type: 'string', min: 1, max: 15, message: 'Chiều dài từ 1 đến 15 ký tự', trigger: 'change'}
          ],
          name: [
            requiredValidator,
            {type: 'string', min: 1, max: 100, message: 'Chiều dài từ 1 đến 100 ký tự', trigger: 'change'}
          ],
          valueDescription: [
            {type: 'string', min: 0, max: 100, message: 'Chiều dài nhỏ hơn 100 ký tự', trigger: 'change'}
          ],
          type: [
            requiredValidator
          ],
          value: [
            {required: true, type: 'number', message: 'Không được để trống', trigger: 'change'},
            {required: true, type: 'number', max: 100, message: 'Số dương nhỏ hơn hoặc bằng 100', trigger: 'change'}
          ],
          defaultBaseSalary: [
            {required: true, type: 'number', message: 'Không được để trống', trigger: 'change'},
          ],
        }
      }
    },
    methods: {
      updateCalcType(calcType) {
        if (calcType === 'SUPPORTED') {
          this.group.minSupportedPercent = this.group.minSupportedPercent || 0;
          this.group.defaultBaseSalary = this.group.defaultBaseSalary || 0
        }
        this.group.type = calcType
      },
      handleSubmit(name) {
        this.$refs[name].validate((valid) => {
          if (valid) {
            if (this.isNew) {
              this.insertCategory()
            } else {
              this.updateCategory()
            }
          } else {
            this.$Message.error('Dữ liệu chưa hợp lệ!');
          }
        })
      },
      async updateCategory() {
        const res = await GroupRepository.updateCategory(this.group.id, this.group)
        if (res.status < 200 || res.status > 299) {
          this.$Message.error(`Vui lòng thử lại! ${res.status}`);
        } else {
          this.$Message.success('Thành công!');
          this.$emit('updateGroupSuccess')
          this.$Modal.remove();
        }
      },
      async insertCategory() {
        const res = await GroupRepository.insertCategory(this.group)
        if (res.status < 200 || res.status > 299) {
          this.$Message.error(`Vui lòng thử lại! ${res.status}`);
        } else {
          this.$Message.success('Thành công!');
          this.$emit('insertGroupSuccess')
          this.$Modal.remove();
        }
      }
    },
    watch: {
      customValue: function () {
        if (!this.customValue) {
          this.customValue = 0
        }
        this.group.minSupportedPercent = this.customValue;
        if (this.group.value < this.group.minSupportedPercent) {
          this.group.value = this.group.minSupportedPercent
        }
      }
    },
    mounted() {
      window.addEventListener('keydown', (e) => {
        if (e.key === 'Enter') {
          console.log("ENter")
          this.handleSubmit('tblGroup');
        }
      });
    }
  }
</script>

<style scoped>

</style>
