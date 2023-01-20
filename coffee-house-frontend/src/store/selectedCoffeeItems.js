export default {
	namespaced: true,
	state: {
		selectedCoffee: '',
		selectedMilk: '',
		selectedSyrup: [],
		selectedTopping: []
	},
	actions: {
		async addSelectedRadio({ commit }, { product, category }) {
			commit('addSelectedRadio', { product, category })
		},
		async removeSelectedRadio({ commit }, category) {
			commit('removeSelectedRadio', category)
		},
		async selectCheckbox({ state, commit }, { product, category }) {
			Object.keys(state).forEach(key => {
				if (key.includes(category)) {
					if (state[key].includes(product)) {
						commit('removeSelectedCheckbox', { product, key })
					} else {
						commit('addSelectedCheckbox', { product, key })
					}
				}
			})
		},
		async resetSelected({ commit }) {
			commit('resetSelected')
		}
	},
	getters: {
		getSelectedCoffee(state) {
			return state.selectedCoffee
		},
		getSelectedMilk(state) {
			return state.selectedMilk
		},
		getSelectedSyrup(state) {
			return state.selectedSyrup
		},
		getSelectedTopping(state) {
			return state.selectedTopping
		}
	},
	mutations: {
		addSelectedRadio(state, { product, category }) {
			Object.keys(state).forEach(key => {
				if (key.includes(category)) {
					state[key] = product
				}
			})
		},
		removeSelectedRadio(state, category) {
			Object.keys(state).forEach(key => {
				if (key.includes(category)) {
					state[key] = ''
				}
			})
		},
		addSelectedCheckbox(state, { product, key }) {
			state[key].push(product)
		},
		removeSelectedCheckbox(state, { product, key }) {
			state[key] = state[key].filter(el => el !== product)
		},
		resetSelected(state) {
			state.selectedCoffee = ''
			state.selectedMilk = ''
			state.selectedSyrup = []
			state.selectedTopping = []
		}
	}
}
