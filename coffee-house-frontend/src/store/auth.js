import instance from '@/store/axiosInstance'
import router from '@/router/router'
import { requests } from '@/_config'
import { userFromAuthHeader } from '@/services/userService'
import { UNKNOWN_ERROR, AUTHORIZATION_ERROR } from '@/services/errorMessages'

export default {
	namespaced: true,
	state: {
		isRegisterSuccess: false
	},
	actions: {
		async register({ commit, dispatch }, user) {
			dispatch('request/aipRequest', null, { root: true })
			await instance
				.post(requests.register, user)
				.then(response => {
					dispatch('request/setIsRequesting', false, { root: true })
					commit('setIsRegisterSuccess', true)
				})
				.catch(error => {
					dispatch('request/setIsRequesting', false, { root: true })
					if (error.response && error.response.status && error.response.status === 400) {
						dispatch('request/setErrMsg', error.response.data.message, { root: true })
					} else {
						dispatch('request/setErrMsg', UNKNOWN_ERROR, { root: true })
					}
				})
		},
		async login({ commit, dispatch, rootGetters }, user) {
			dispatch('request/aipRequest', null, { root: true })
			await instance
				.post(requests.login, user)
				.then(response => {
					dispatch('request/setIsRequesting', false, { root: true })
					userFromAuthHeader(response.headers.authorization)
					if (rootGetters['user/isLogged']) {
						router.push({ name: 'menu' })
						// dispatch('user/requestOrders', null, { root: true })
					}
					if (rootGetters['manager/isLogged']) {
						router.push({ name: 'management' })
						// dispatch('manager/requestCoffeeHouse', null, { root: true })
					}
				})
				.catch(error => {
					dispatch('request/setIsRequesting', false, { root: true })
					if (error.response && error.response.status && (error.response.status === 401 || error.response.status === 400)) {
						dispatch('request/setErrMsg', AUTHORIZATION_ERROR, { root: true })
					} else {
						dispatch('request/setErrMsg', UNKNOWN_ERROR, { root: true })
					}
				})
		},
		async logout({ commit, dispatch, rootGetters }) {
			commit('logout')
			if (rootGetters['user/isLogged']) {
				dispatch('user/logoutUser', null, { root: true })
			}
			if (rootGetters['manager/isLogged']) {
				dispatch('manager/logoutManager', null, { root: true })
			}
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
