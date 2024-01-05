import instance from '@/store/axiosInstance'
import { requests } from '@/_config'

export default {
	namespaced: true,
	state: {
		coffeeHouses: [],
		activeCHId: 1,
		openingHours: {
			beginning: '9:00',
			ending: '22:00'
		}
	},
	actions: {
		async requestCoffeeHouses({ commit, state }) {
			if (state.coffeeHouses.length === 0) {
				await instance
					.get(requests.requestCoffeeHouses)
					.then(response => {
						commit('setCoffeeHouses', response.data)
					})
					.catch(error => {
						// console.log(error)
					})
			}
		},
		async setActiveCHId({ commit }, id) {
			commit('setActiveCHId', id)
		}
	},
	getters: {
		isLoaded(state) {
			return state.coffeeHouses.length === 0 ? false : true
		},
		getCoffeeHouses(state) {
			return state.coffeeHouses
		},
		getActiveCHId(state) {
			return state.activeCHId
		},
		getActiveCH(state) {
			for (const el of state.coffeeHouses) {
				if (el.id === state.activeCHId) {
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
		setCoffeeHouses(state, payload) {
			state.coffeeHouses = payload
		},
		setActiveCHId(state, payload) {
			state.activeCHId = payload
		}
	}
}
