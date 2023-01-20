import instance from '@/store/axiosInstance'
import { requests } from '@/_config'

export default {
	namespaced: true,
	state: {
		products: [],
		activeProducts: null
	},
	actions: {
		async requestProducts({ commit, state, rootGetters }) {
			console.log('requestProducts')
			await instance
				.get(requests.requestProducts)
				.then(result => {
					commit('setProducts', result.data.menu)
					for (const el of state.products) {
						if (el.id === rootGetters['addresses/getActiveAddressId']) {
							commit('setActiveProducts', el.products)
							break
						}
					}
				})
				.catch(error => {
					console.log(error)
				})
		},
		async setActiveProducts({ commit }, activeProducts) {
			commit('setActiveProducts', activeProducts)
		}
	},
	getters: {
		getProducts(state) {
			return state.products
		},
		getActiveProducts(state) {
			return state.activeProducts
		},
		isLoaded(state) {
			return state.activeProducts ? true : false
		}
	},
	mutations: {
		setProducts(state, payload) {
			state.products = payload
		},
		setActiveProducts(state, payload) {
			state.activeProducts = payload
		}
	}
}
