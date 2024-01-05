import instance from '@/store/axiosInstance'
import { authHeader } from '@/services/userService'
import { getActiveOrdersParams, getOrdersBySearchParams } from '@/services/requestBody'
import { UNKNOWN_ERROR } from '@/services/errorMessages'
import { requests } from '@/_config'

export default {
	namespaced: true,
	state: {
		manager: null,
		coffeeHouse: null,
		activeOrders: [],
		isActiveOrdersLoaded: false,
		searchOrders: [],
		isSearchOrdersLoaded: false
	},
	actions: {
		async setManager({ commit }, manager) {
			commit('setManager', manager)
		},
		async setCoffeeHouse({ commit }, coffeeHouse) {
			commit('setCoffeeHouse', coffeeHouse)
		},
		async logoutManager({ commit, dispatch }) {
			commit('logoutManager')
			dispatch('menu/resetManagerMenu', null, { root: true })
		},
		async requestCoffeeHouse({ commit, state }) {
			if (!state.coffeeHouse) {
				await instance
					.get(requests.requestManagerCH, {
						headers: authHeader()
					})
					.then(response => {
						localStorage.setItem('coffeeHouse', response.data.address)
						commit('setCoffeeHouse', response.data.address)
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
						commit('setActiveOrders', response.data)
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
						commit('setActiveOrders', response.data)
					})
					.catch(error => {
						// console.log(error)
					})
			}
		},
		async requestSearchOrders({ commit, dispatch, state }, searchParams) {
			dispatch('request/aipRequest', null, { root: true })
			await instance
				.get(requests.requestManagerOrders, {
					headers: authHeader(),
					params: getOrdersBySearchParams(searchParams)
				})
				.then(response => {
					dispatch('request/setIsRequesting', false, { root: true })
					commit('setSearchOrders', response.data)
				})
				.catch(error => {
					dispatch('request/setIsRequesting', false, { root: true })
					dispatch('request/setErrMsg', UNKNOWN_ERROR, { root: true })
				})
		},
		async resetSearchOrders({ commit }) {
			commit('resetSearchOrders')
		},
		async closeActiveOrder({ commit, dispatch, state }, { id, status }) {
			dispatch('request/aipRequest', null, { root: true })
			await instance
				.patch(
					requests.requestManagerOrders + '/' + id,
					{},
					{
						headers: authHeader(),
						params: { status: status }
					}
				)
				.then(response => {
					dispatch('request/setIsRequesting', false, { root: true })
					commit('setActiveOrders', response.data)
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
		getCoffeeHouse(state) {
			return state.coffeeHouse
		},
		isCHLoaded(state) {
			return state.coffeeHouse ? true : false
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
			if (localStorage.getItem('coffeeHouse')) {
				localStorage.removeItem('coffeeHouse')
			}
			state.manager = null
			state.coffeeHouse = null
			state.activeOrders = []
			state.isActiveOrdersLoaded = false
			state.searchOrders = []
			state.isSearchOrdersLoaded = false
		},
		setCoffeeHouse(state, payload) {
			state.coffeeHouse = payload
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
