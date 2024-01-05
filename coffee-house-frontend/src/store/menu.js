import instance from '@/store/axiosInstance'
import { authHeader } from '@/services/userService'
import { UNKNOWN_ERROR } from '@/services/errorMessages'
import { arraysEqual } from '@/services/helper'
import { requests } from '@/_config'

export default {
	namespaced: true,
	state: {
		userMenu: [],
		activeUserMenu: null,
		managerMenu: null,
		initialMenuAvailability: [],
		changedMenuAvailability: [],
		isMenuAvailabilityChanged: false
	},
	actions: {
		async requestUserMenu({ commit, state, rootGetters }) {
			if (state.userMenu.length === 0) {
				await instance
					.get(requests.requestUserMenu)
					.then(response => {
						commit('setUserMenu', response.data)
						for (const el of state.userMenu) {
							if (el.id === rootGetters['coffeeHouse/getActiveCHId']) {
								commit('setActiveUserMenu', el.products)
								break
							}
						}
					})
					.catch(error => {
						// console.log(error)
					})
			}
		},
		async setActiveUserMenu({ commit }, activeUserMenu) {
			commit('setActiveUserMenu', activeUserMenu)
		},
		async resetUserMenu({ commit }) {
			commit('resetUserMenu')
		},

		// MANAGER PART
		async requestManagerMenu({ commit, state }) {
			if (!state.managerMenu) {
				await instance
					.get(requests.requestManagerMenu, {
						headers: authHeader()
					})
					.then(response => {
						commit('setManagerMenu', response.data)
						commit('resetAvailability')
					})
					.catch(error => {
						// console.log(error)
					})
			}
		},
		async resetManagerMenu({ commit }) {
			commit('resetManagerMenu')
		},
		async toggleAvailability({ commit }, id) {
			commit('toggleAvailability', id)
		},
		async resetAvailability({ commit, state }) {
			if (state.isMenuAvailabilityChanged) {
				commit('resetAvailability')
			}
		},
		async updateAvailability({ commit, dispatch }, updatedAvailability) {
			dispatch('request/aipRequest', null, { root: true })
			await instance
				.patch(requests.requestManagerMenu, updatedAvailability, {
					headers: authHeader()
				})
				.then(response => {
					dispatch('request/setIsRequesting', false, { root: true })
					commit('setManagerMenu', response.data)
					commit('resetAvailability')
				})
				.catch(error => {
					dispatch('request/setIsRequesting', false, { root: true })
					dispatch('request/setErrMsg', UNKNOWN_ERROR, { root: true })
				})
		}
	},
	getters: {
		getUserMenu(state) {
			return state.userMenu
		},
		getUserActiveMenu(state) {
			return state.activeUserMenu
		},
		isUserMenuLoaded(state) {
			return state.activeUserMenu ? true : false
		},
		getManagerMenu(state) {
			return state.managerMenu
		},
		isManagerMenuLoaded(state) {
			return state.managerMenu ? true : false
		},
		getInitialMenuAvailability(state) {
			return state.initialMenuAvailability
		},
		getChangedMenuAvailability(state) {
			return state.changedMenuAvailability
		},
		isMenuAvailabilityChanged(state) {
			return state.isMenuAvailabilityChanged
		}
	},
	mutations: {
		setUserMenu(state, payload) {
			state.userMenu = payload
		},
		setActiveUserMenu(state, payload) {
			state.activeUserMenu = payload
		},
		resetUserMenu(state, payload) {
			state.userMenu = []
			state.activeUserMenu = null
		},
		// MANAGER PART
		setManagerMenu(state, payload) {
			if (state.initialMenuAvailability.length > 0) {
				state.initialMenuAvailability = []
			}
			state.managerMenu = payload
			Object.keys(state.managerMenu).forEach(menuCategory => {
				state.managerMenu[menuCategory].forEach(product => {
					state.initialMenuAvailability.push({ id: product.id, availability: product.availability })
				})
			})
		},
		toggleAvailability(state, id) {
			const index = state.changedMenuAvailability.findIndex(el => el.id === id)
			state.changedMenuAvailability[index].availability = !state.changedMenuAvailability[index].availability
			state.isMenuAvailabilityChanged = !arraysEqual(state.initialMenuAvailability, state.changedMenuAvailability)
		},
		resetAvailability(state) {
			state.changedMenuAvailability = state.initialMenuAvailability.map(obj => ({ ...obj }))
			state.isMenuAvailabilityChanged = false
		},
		resetManagerMenu(state) {
			state.managerMenu = null
			state.initialMenuAvailability = []
			state.changedMenuAvailability = []
			state.isMenuAvailabilityChanged = false
		}
	}
}
