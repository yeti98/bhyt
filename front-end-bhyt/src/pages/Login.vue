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
    import {
        ON_INVALID_DATA,
        ON_LOGIN_FAIL,
        ON_NOINTERNET_CONNECTION,
        ON_SUCCESS_MESSAGE
    } from "../javascript/api_messages";

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
                        this.$Message.error(ON_INVALID_DATA);
                    }
                })
            },
            login: function () {
                let email = this.formInline.user
                let password = this.formInline.password
                this.$store.dispatch("login", {email, password})
                    .then(() => {
                            this.$Message.success(ON_SUCCESS_MESSAGE);
                        }
                    )
                    .catch(err => {
                        if (err.response.status === 403) {
                            this.$Message.error(ON_LOGIN_FAIL)
                        } else {
                            this.$Message.error(ON_NOINTERNET_CONNECTION)
                        }
                    });
            },
            onEnterKeyPressed(event) {
                if (event.key === 'Enter') {
                    this.handleSubmit('formInline');
                }
            }
        },
        mounted() {
            window.addEventListener('keydown', this.onEnterKeyPressed);
        },
        beforeDestroy() {
            window.removeEventListener('keydown', this.onEnterKeyPressed)
        }
    }
</script>
