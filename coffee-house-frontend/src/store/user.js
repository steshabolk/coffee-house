import instance from '@/store/axiosInstance'
import { authHeader, userFromAuthHeader } from '@/services/userService'
import { UNKNOWN_ERROR } from '@/services/messages'
import { requests } from '@/_config'

export default {
	namespaced: true,
	state: {
		user: null,
		isOrdersLoaded: false,
		orders: []
	},
	actions: {
		async setUser({ commit }, user) {
			commit('setUser', user)
		},
		async requestOrders({ commit }) {
			console.log('requestOrders')
			await instance
				.get(requests.requestOrders, {
					headers: {
						...authHeader()
					}
				})
				.then(response => {
					commit('setOrders', response.data.orders)
					commit('setIsOrdersLoaded', true)
				})
				.catch(error => {
					console.log(error)
				})
		},
		async cancelOrder({ commit, dispatch }, cancelledOrder) {
			dispatch('request/setErrMsg', '', { root: true })
			dispatch('request/setIsRequesting', true, { root: true })
			await instance
				.patch(
					requests.requestOrders,
					{
						...cancelledOrder
					},
					{
						headers: {
							...authHeader()
						}
					}
				)
				.then(response => {
					dispatch('request/setIsRequesting', false, { root: true })
					dispatch('user/setOrders', response.data.orders, { root: true })
				})
				.catch(error => {
					dispatch('request/setIsRequesting', false, { root: true })
					dispatch('request/setErrMsg', UNKNOWN_ERROR, { root: true })
				})
		},
		async setOrders({ commit }, orders) {
			commit('setOrders', orders)
		},
		async changeName({ commit, dispatch }, name) {
			dispatch('request/setErrMsg', '', { root: true })
			dispatch('request/setIsRequesting', true, { root: true })
			await instance
				.post(
					requests.changeName,
					{
						...name
					},
					{
						headers: {
							...authHeader()
						}
					}
				)
				.then(response => {
					dispatch('request/setIsRequesting', false, { root: true })
					userFromAuthHeader(response.headers.authorization)
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
		async changePassword({ commit, dispatch }, password) {
			dispatch('request/setErrMsg', '', { root: true })
			dispatch('request/setIsRequesting', true, { root: true })
			await instance
				.post(
					requests.changePassword,
					{
						...password
					},
					{
						headers: {
							...authHeader()
						}
					}
				)
				.then(response => {
					dispatch('request/setIsRequesting', false, { root: true })
				})
				.catch(error => {
					dispatch('request/setIsRequesting', false, { root: true })
					if (error.response.status === 400) {
						dispatch('request/setErrMsg', error.response.data.message, { root: true })
					} else {
						dispatch('request/setErrMsg', UNKNOWN_ERROR, { root: true })
					}
				})
		}
	},
	getters: {
		getUser(state) {
			return state.user
		},
		isLogged(state) {
			return state.user ? true : false
		},
		getOrders(state) {
			return state.orders
		},
		isOrdersLoaded(state) {
			return state.isOrdersLoaded
		}
	},
	mutations: {
		setUser(state, payload) {
			state.user = payload
		},
		setOrders(state, payload) {
			state.orders = payload.reverse()
		},
		setIsOrdersLoaded(state, payload) {
			state.isOrdersLoaded = payload
		}
	}
}
