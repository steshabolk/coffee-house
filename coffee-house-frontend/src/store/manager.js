import instance from '@/store/axiosInstance'
import { authHeader } from '@/services/userService'
import { getActiveOrdersParams, getOrdersBySearchParams } from '@/services/requestBody'
import { UNKNOWN_ERROR } from '@/services/errorMessages'
import { requests } from '@/_config'

export default {
	namespaced: true,
	state: {
		manager: null,
		address: null,
		activeOrders: [],
		isActiveOrdersLoaded: false,
		searchOrders: [],
		isSearchOrdersLoaded: false
	},
	actions: {
		async setManager({ commit }, manager) {
			commit('setManager', manager)
		},
		async logoutManager({ commit, dispatch }) {
			commit('logoutManager')
			dispatch('menu/resetManagerMenu', null, { root: true })
		},
		async requestAddress({ commit, state }) {
			if (!state.address) {
				await instance
					.get(requests.requestManagerAddress, {
						headers: authHeader()
					})
					.then(response => {
						commit('setAddress', response.data)
					})
					.catch(error => {
						// console.log(error)
					})
			}
		},
		async requestActiveOrders({ commit, state }) {
			if (!state.isActiveOrdersLoaded) {
				await instance
					.get(requests.requestManagerOrders, {
						headers: authHeader(),
						params: getActiveOrdersParams()
					})
					.then(response => {
						commit('setActiveOrders', response.data.orders)
					})
					.catch(error => {
						// console.log(error)
					})
			}
		},
		async updateActiveOrders({ commit, state }) {
			if (state.isActiveOrdersLoaded) {
				await instance
					.get(requests.requestManagerOrders, {
						headers: authHeader(),
						params: getActiveOrdersParams()
					})
					.then(response => {
						commit('setActiveOrders', response.data.orders)
					})
					.catch(error => {
						// console.log(error)
					})
			}
		},
		async requestSearchOrders({ commit, state }, searchParams) {
			await instance
				.get(requests.requestManagerOrders, {
					headers: authHeader(),
					params: getOrdersBySearchParams(searchParams)
				})
				.then(response => {
					commit('setSearchOrders', response.data.orders)
				})
				.catch(error => {
					// console.log(error)
				})
		},
		async resetSearchOrders({ commit }) {
			commit('resetSearchOrders')
		},
		async updateOrderStatus({ commit, dispatch, state }, updatedOrder) {
			dispatch('request/aipRequest', null, { root: true })
			await instance
				.patch(requests.requestManagerOrders, updatedOrder, {
					headers: authHeader()
				})
				.then(response => {
					dispatch('request/setIsRequesting', false, { root: true })
					commit('setActiveOrders', response.data.orders)
				})
				.catch(error => {
					dispatch('request/setIsRequesting', false, { root: true })
					dispatch('request/setErrMsg', UNKNOWN_ERROR, { root: true })
				})
		}
	},
	getters: {
		getManager(state) {
			return state.manager
		},
		isLogged(state) {
			return state.manager ? true : false
		},
		getAddress(state) {
			return state.address
		},
		isAddressLoaded(state) {
			return state.address ? true : false
		},
		getActiveOrders(state) {
			return state.activeOrders
		},
		isActiveOrdersLoaded(state) {
			return state.isActiveOrdersLoaded
		},
		getSearchOrders(state) {
			return state.searchOrders
		},
		isSearchOrdersLoaded(state) {
			return state.isSearchOrdersLoaded
		}
	},
	mutations: {
		setManager(state, payload) {
			state.manager = payload
		},
		logoutManager(state, payload) {
			state.manager = null
			state.address = null
			state.activeOrders = []
			state.isActiveOrdersLoaded = false
			state.searchOrders = []
			state.isSearchOrdersLoaded = false
		},
		setAddress(state, payload) {
			state.address = payload
		},
		setActiveOrders(state, payload) {
			state.activeOrders = payload
			state.isActiveOrdersLoaded = true
		},
		setSearchOrders(state, payload) {
			state.searchOrders = payload
			state.isSearchOrdersLoaded = true
		},
		resetSearchOrders(state, payload) {
			state.searchOrders = []
			state.isSearchOrdersLoaded = false
		}
	}
}
