import { mapGetters } from 'vuex'

export default {
	methods: {
		phoneFormatted(phone) {
			let phoneFormatted = phone
			if (phoneFormatted.startsWith('+')) {
				phoneFormatted = phoneFormatted.slice(1)
			}
			if (phoneFormatted.startsWith('8')) {
				phoneFormatted = '7' + phoneFormatted.slice(1)
			}
			return phoneFormatted
		},
		resetForm() {
			this.user.password = ''
			this.v$.user.password.$reset()
			if (this.v$.user.passwordConfirmation) {
				this.user.passwordConfirmation = ''
				this.v$.user.passwordConfirmation.$reset()
			}
		}
	},
	computed: {
		splittedErrMsg() {
			let arr = this.errMsg.split(';')
			arr.forEach((e, i) => {
				arr[i] = e.trim()
			})
			return arr.filter(el => el !== '')
		}
	}
}
