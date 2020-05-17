<template>
    <Form ref="formInline" :model="formInline" :rules="ruleInline" inline>
        <FormItem prop="user">
            <Input type="text" v-model="formInline.user" placeholder="email@example.com">
                <Icon type="ios-person-outline" slot="prepend"></Icon>
            </Input>
        </FormItem>
        <FormItem prop="password">
            <Input type="password" v-model="formInline.password" placeholder="Mật khẩu">
                <Icon type="ios-lock-outline" slot="prepend"></Icon>
            </Input>
        </FormItem>
        <FormItem>
            <Button type="primary" @click="handleSubmit('formInline')">Đăng nhập</Button>
        </FormItem>
    </Form>
</template>
<script>
  export default {
    data () {
      return {
        formInline: {
          user: '',
          password: ''
        },
        ruleInline: {
          user: [
            { required: true, message: 'Vui lòng điền địa chỉ email', trigger: 'blur' },
            { type: 'email', message: 'Email không hợp lệ', trigger: 'blur' }
          ],
          password: [
            { required: true, message: 'Vui lòng điền mật khẩu', trigger: 'blur' },
            { type: 'string', min: 6, message: 'Mật khẩu có độ dài từ 6 ký tự', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      handleSubmit(name) {
        this.$refs[name].validate((valid) => {
          if (valid) {
            this.$Message.success('Thành công');
          } else {
            this.$Message.error('Sai tài khoản hoặc mật khẩu');
          }
        })
      },
      login: function () {
        let email = this.email
        let password = this.password
        this.$store.dispatch('login', { email, password })
          .then(() => this.$router.push('/'))
          .catch(err => console.log(err))
      }
    }
  }
</script>
