<template>
  <div>
    <Form ref="formInline" :model="formInline" :rules="ruleInline" inline>
      <FormItem prop="user">
        <Input autofocus type="text" v-model="formInline.user" placeholder="email@example.com">
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
  </div>
</template>
<script>
    import {RepositoryFactory} from '@/repositories/RepositoryFactory'

    const AuthRepository = RepositoryFactory.get('auth');
    export default {
        data() {
            return {
                formInline: {
                    user: '',
                    password: ''
                },
                ruleInline: {
                    user: [
                        {required: true, message: 'Vui lòng điền địa chỉ email', trigger: 'change'},
                        {type: 'email', message: 'Email không hợp lệ', trigger: 'change'}
                    ],
                    password: [
                        {required: true, message: 'Vui lòng điền mật khẩu', trigger: 'change'},
                        {type: 'string', min: 6, message: 'Mật khẩu có độ dài từ 6 ký tự', trigger: 'change'}
                    ]
                }
            }
        },
        methods: {
            handleSubmit(name) {
                this.$refs[name].validate((valid) => {
                    if (valid) {
                        this.login();
                    } else {
                        this.$Message.error('Dữ liệu chưa hợp lệ!');
                    }
                })
            },
            login: async function () {
                let email = this.formInline.user
                let password = this.formInline.password
                if (email==='admin@gmail.com' && password==="123456") {
                    this.$emit("LoggedIn", email)
                    this.$Message.success('Thành công');
                    // this.$store.dispatch('login', {email, password})
                    //     .then(() => this.$emit("LoggedIn", email))
                    //     .catch(err => console.log(err))
                }
                // const res = await AuthRepository.login({email, password});
                // console.log(res.data, res.status)
                // this.$store.dispatch('login', {email, password})
                //     .then(() => this.$router.push('/'))
                //     .catch(err => console.log(err))
            }
        },
        mounted() {
            window.addEventListener('keydown', (e) => {
                if (e.key === 'Enter') {
                    this.handleSubmit('formInline');
                }
            });
        }
    }
</script>
