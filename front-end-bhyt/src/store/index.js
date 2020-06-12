import Vue from 'vue'
import Vuex from 'vuex'
import {RepositoryFactory} from '@/repositories/RepositoryFactory'

Vue.use(Vuex);

const AuthRepository = RepositoryFactory.get('auth');
export default new Vuex.Store({
    state: {
        token: localStorage.getItem('token') || '',
        user: {},
        status: 'loading'
    },
    mutations: {
        auth_request(state) {
            state.status = 'loading'
        },
        auth_success(state, { token, user }) {
            console.log("on Mutation", token, user);
            state.status = 'success';
            state.token = token;
            state.user = user
        },
        auth_error(state) {
            state.status = 'error'
        },
        logout(state) {
            state.status = '';
            state.token = ''
        },
    },
    actions: {
        login({commit}, user) {
            return new Promise((resolve, reject) => {
                commit('auth_request')
                AuthRepository.login(user)
                .then(resp => {
                    const token = resp.data.token
                    const email = resp.data.email
                    const role = resp.data.role
                    const user = {email: email, role: role}
                    localStorage.setItem('token', token)
                    localStorage.setItem('email', email)
                    commit('auth_success', {token, user})
                    resolve(resp)
                }).catch(reason => {
                    commit('auth_error')
                    localStorage.removeItem('token')
                    reject(reason)
                })
            })
        },
        register({commit}, user) {
            return new Promise((resolve, reject) => {
                commit('auth_request')
                axios({url: 'http://localhost:3000/register', data: user, method: 'POST'})
                    .then(resp => {
                        const token = resp.data.token
                        const user = resp.data.user
                        localStorage.setItem('token', token)
                        axios.defaults.headers.common['Authorization'] = token
                        commit('auth_success', token, user)
                        resolve(resp)
                    })
                    .catch(err => {
                        commit('auth_error', err)
                        localStorage.removeItem('token')
                        reject(err)
                    })
            })
        },
        logout({commit}) {
            return new Promise((resolve, reject) => {
                commit('logout')
                localStorage.removeItem('token')
                resolve()
            })
        }
    },
    modules: {},
    getters: {
        isLoggedIn: state => state.token,
        authStatus: state => state.status,
        user: state => state.user.email
    }
})
