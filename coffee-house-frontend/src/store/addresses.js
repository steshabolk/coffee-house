import instance from '@/store/axiosInstance'
import { requests } from '@/_config'

export default {
	namespaced: true,
	state: {
		addresses: [],
		activeAddressId: 1,
		openingHours: {
			beginning: '9:00',
			ending: '22:00'
		}
	},
	actions: {
		async requestAddresses({ commit, state }) {
			if (state.addresses.length === 0) {
				await instance
					.get(requests.requestAddresses)
					.then(response => {
						commit('setAddresses', response.data.coffeeHouses)
					})
					.catch(error => {
						// console.log(error)
					})
			}
		},
		async setActiveAddressId({ commit }, id) {
			commit('setActiveAddressId', id)
		}
	},
	getters: {
		isLoaded(state) {
			return state.addresses.length === 0 ? false : true
		},
		getAddresses(state) {
			return state.addresses
		},
		getActiveAddressId(state) {
			return state.activeAddressId
		},
		getActiveAddress(state) {
			for (const el of state.addresses) {
				if (el.id === state.activeAddressId) {
					return el.address
				}
			}
		},
		getOpeningHours(state) {
			return state.openingHours
		},
		getBeginningHours(state) {
			return Number(state.openingHours.beginning.split(':')[0])
		},
		getEndingHours(state) {
			return Number(state.openingHours.ending.split(':')[0])
		}
	},
	mutations: {
		setAddresses(state, payload) {
			state.addresses = payload
		},
		setActiveAddressId(state, payload) {
			state.activeAddressId = payload
		}
	}
}
