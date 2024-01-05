export default {
	namespaced: true,
	state: {
		cart: [],
		unavailableCart: []
	},
	actions: {
		async addInCart({ commit, state }, { cartObj, num }) {
			let isChanged = false
			for (const obj of state.cart) {
				if (
					obj.product.id === cartObj.product.id &&
					obj.additives.length === cartObj.additives.length &&
					obj.additives.every(el => cartObj.additives.includes(el))
				) {
					obj.quantity += num
					let calcCost = obj.product.price * obj.quantity
					obj.additives.forEach(add => {
						calcCost += add.price * obj.quantity
					})
					obj.cost = calcCost
					isChanged = true
					break
				}
			}
			if (!isChanged) {
				commit('addInCart', { cartObj, num })
			}
		},
		async removeFromCart({ commit, state }, cartObj) {
			for (const obj of state.cart) {
				if (
					obj.product.id === cartObj.product.id &&
					obj.additives.length === cartObj.additives.length &&
					obj.additives.every(el => cartObj.additives.includes(el))
				) {
					commit('removeFromCart', obj)
					break
				}
			}
		},
		async resetCart({ commit }) {
			commit('resetCart')
		},
		async resetUnavailableCart({ commit }) {
			commit('resetUnavailableCart')
		},
		async updateCartAvailability({ state, commit, rootGetters }) {
			const activeMenu = rootGetters['menu/getUserActiveMenu']
			const availableIds = []
			Object.keys(activeMenu).forEach(key => {
				activeMenu[key].forEach(obj => {
					availableIds.push(obj.id)
				})
			})
			let newCart = []
			let newUnavailableCart = []
			for (const obj of state.cart.concat(state.unavailableCart)) {
				if (availableIds.includes(obj.product.id) && obj.additives.every(el => availableIds.includes(el.id))) {
					newCart.push(obj)
				} else {
					newUnavailableCart.push(obj)
				}
			}
			commit('updateCartAvailability', { newCart, newUnavailableCart })
		}
	},
	getters: {
		getCart(state) {
			return state.cart
		},
		getUnavailableCart(state) {
			return state.unavailableCart
		}
	},
	mutations: {
		addInCart(state, { cartObj, num }) {
			cartObj.quantity += num
			let calcCost = cartObj.product.price * cartObj.quantity
			cartObj.additives.forEach(add => {
				calcCost += add.price * cartObj.quantity
			})
			cartObj.cost = calcCost
			state.cart.push(cartObj)
		},
		removeFromCart(state, payload) {
			state.cart = state.cart.filter(el => el !== payload)
		},
		resetCart(state) {
			state.cart = []
			state.unavailableCart = []
		},
		resetUnavailableCart(state) {
			state.unavailableCart = []
		},
		updateCartAvailability(state, { newCart, newUnavailableCart }) {
			state.cart = newCart
			state.unavailableCart = newUnavailableCart
		}
	}
}
