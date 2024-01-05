export default {
	methods: {
		resetForm() {
			if (this.v$.user.password) {
				this.user.password = ''
				this.v$.user.password.$reset()
			}
			if (this.v$.user.passwordConfirmation) {
				this.user.passwordConfirmation = ''
				this.v$.user.passwordConfirmation.$reset()
			}
		}
	},

	computed: {
		splitFieldsErrMsg() {
			let arr = this.errMsg.split(';')
			arr.forEach((e, i) => {
				arr[i] = e.trim()
			})
			arr = arr.filter(el => el !== '')

			arr.forEach((e, i) => {
				if (e.includes('-')) {
					arr[i] = e.split('-')[1].trim()
				}
			})
			return arr
		}
	}
}
