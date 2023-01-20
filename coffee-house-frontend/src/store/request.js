export default {
	namespaced: true,
	state: {
		isRequesting: false,
		errMsg: ''
	},
	actions: {
		async setIsRequesting({ commit }, payload) {
			commit('setIsRequesting', payload)
		},
		async setErrMsg({ commit }, err) {
			commit('setErrMsg', err)
		}
	},
	getters: {
		isRequesting(state) {
			return state.isRequesting
		},
		getErrMsg(state) {
			return state.errMsg
		}
	},
	mutations: {
		setIsRequesting(state, payload) {
			state.isRequesting = payload
		},
		setErrMsg(state, payload) {
			state.errMsg = payload
		}
	}
}
