import instance from '@/store/axiosInstance'
import { requests } from '@/_config'
import { userFromAuthHeader } from '@/services/userService'
import { UNKNOWN_ERROR, AUTHORIZATION_ERROR } from '@/services/messages'

export default {
	namespaced: true,
	state: {
		isRegisterSuccess: false
	},
	actions: {
		async register({ commit, dispatch }, user) {
			dispatch('request/setErrMsg', '', { root: true })
			dispatch('request/setIsRequesting', true, { root: true })
			await instance
				.post(requests.register, {
					...user
				})
				.then(response => {
					dispatch('request/setIsRequesting', false, { root: true })
					commit('setIsRegisterSuccess', true)
				})
				.catch(error => {
					dispatch('request/setIsRequesting', false, { root: true })
					if (error.response.status === 400) {
						dispatch('request/setErrMsg', error.response.data.message, { root: true })
					} else {
						dispatch('request/setErrMsg', UNKNOWN_ERROR, { root: true })
					}
				})
		},
		async login({ commit, dispatch }, user) {
			dispatch('request/setErrMsg', '', { root: true })
			dispatch('request/setIsRequesting', true, { root: true })
			await instance
				.post(requests.login, {
					...user
				})
				.then(response => {
					dispatch('request/setIsRequesting', false, { root: true })
					userFromAuthHeader(response.headers.authorization)
				})
				.catch(error => {
					dispatch('request/setIsRequesting', false, { root: true })
					if (error.response.status === 401) {
						dispatch('request/setErrMsg', AUTHORIZATION_ERROR, { root: true })
					} else {
						dispatch('request/setErrMsg', UNKNOWN_ERROR, { root: true })
					}
				})
		},
		async logout({ commit, dispatch }) {
			commit('logout')
			dispatch('user/setUser', null, { root: true })
		},
		async setIsRegisterSuccess({ commit }, payload) {
			commit('setIsRegisterSuccess', payload)
		}
	},
	getters: {
		isRegisterSuccess(state) {
			return state.isRegisterSuccess
		}
	},
	mutations: {
		logout(state) {
			if (localStorage.getItem('token')) {
				localStorage.removeItem('token')
			}
		},
		setIsRegisterSuccess(state, payload) {
			state.isRegisterSuccess = payload
		}
	}
}
