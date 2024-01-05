import instance from '@/store/axiosInstance'
import { authHeader, userFromAuthHeader } from '@/services/userService'
import { UNKNOWN_ERROR, PRODUCTS_NOT_AVAILABLE, PRODUCTS_NOT_AVAILABLE_MESSAGE } from '@/services/errorMessages'
import { requests } from '@/_config'

export default {
	namespaced: true,
	state: {
		user: null,
		isOrdersLoaded: false,
		orders: [],
		activeOrdersNum: 0
	},
	actions: {
		async setUser({ commit }, user) {
			commit('setUser', user)
		},
		async requestOrders({ commit, state }) {
			if (!state.isOrdersLoaded) {
				await instance
					.get(requests.requestOrders, {
						headers: authHeader()
					})
					.then(response => {
						commit('setOrders', response.data)
					})
					.catch(error => {
						// console.log(error)
					})
			}
		},
		async placeOrder({ commit, dispatch }, { order, orderDetails }) {
			dispatch('request/aipRequest', null, { root: true })
			await instance
				.post(
					requests.requestOrders,
					{
						order: order,
						orderDetails: orderDetails
					},
					{
						headers: authHeader()
					}
				)
				.then(response => {
					dispatch('request/setIsRequesting', false, { root: true })
					dispatch('cart/resetCart', null, { root: true })
					commit('setOrders', response.data)
				})
				.catch(error => {
					dispatch('request/setIsRequesting', false, { root: true })
					if (error.response.status === 400 && error.response.data.code === PRODUCTS_NOT_AVAILABLE) {
						dispatch('menu/resetUserMenu', null, { root: true })
						dispatch('menu/requestUserMenu', null, { root: true })
						setTimeout(() => {
							dispatch('request/setErrMsg', PRODUCTS_NOT_AVAILABLE_MESSAGE, { root: true })
						}, 100)
					} else {
						setTimeout(() => {
							dispatch('request/setErrMsg', UNKNOWN_ERROR, { root: true })
						}, 100)
					}
				})
		},
		async cancelOrder({ commit, dispatch }, cancelledId) {
			dispatch('request/aipRequest', null, { root: true })
			await instance
				.patch(
					requests.requestOrders + '/' + cancelledId,
					{},
					{
						headers: authHeader()
					}
				)
				.then(response => {
					dispatch('request/setIsRequesting', false, { root: true })
					commit('setOrders', response.data)
				})
				.catch(error => {
					dispatch('request/setIsRequesting', false, { root: true })
					dispatch('request/setErrMsg', UNKNOWN_ERROR, { root: true })
				})
		},
		async changeName({ commit, dispatch }, name) {
			dispatch('request/aipRequest', null, { root: true })
			await instance
				.post(requests.changeName, name, {
					headers: authHeader()
				})
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
			dispatch('request/aipRequest', null, { root: true })
			await instance
				.post(requests.changePassword, password, {
					headers: authHeader()
				})
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
		},
		async logoutUser({ commit }) {
			commit('logoutUser')
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
		getActiveOrdersNum(state) {
			return state.activeOrdersNum
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
			state.isOrdersLoaded = true
			const arr = state.orders.filter(el => el.status.toLowerCase() === 'active')
			state.activeOrdersNum = arr.length
		},
		logoutUser(state, payload) {
			state.user = null
			state.isOrdersLoaded = false
			state.orders = []
			state.activeOrdersNum = 0
		}
	}
}
