<template>
	<div class="main-form-wrapper auth-form-wrapper">
		<form @submit.prevent="handleForm" autocomplete="off">
			<div
				v-for="(field, index) of fields"
				:key="index"
				class="main-input-wrapper auth-input-wrapper"
				:class="{ 'input-field-error': isActive == null && user[Object.keys(user)[index]] && v$.user[Object.keys(user)[index]].$invalid }">
				<div class="main-input-text" :class="{ 'main-input-text__active': isActive === index }">
					<SvgIcon :viewBox="field.icon.viewBox" :path="field.icon.svgPath" />
					<input
						v-model="user[Object.keys(user)[index]]"
						@focus="isActive = index"
						@blur="isActive = null"
						:type="field.input.type"
						:placeholder="field.input.placeholder"
						:id="field.input.id"
						:name="field.input.name" />
				</div>
				<div class="form-error-block">
					<div v-if="isActive == null && user[Object.keys(user)[index]]">
						<p class="form-error-msg" v-for="(error, index) of v$.user[Object.keys(user)[index]].$errors" :key="index">
							{{ error.$message }}
						</p>
					</div>
				</div>
			</div>
			<LoaderLine />
			<div v-if="form.btnText" class="main-input-wrapper auth-input-wrapper">
				<button type="submit" class="main-btn btn-disable" :class="{ 'btn-active': isFormValid }">{{ form.btnText }}</button>
			</div>
		</form>
		<div v-if="errMsg" class="request-fail">
			<p class="main-error-message" style="margin-top: 0" v-for="(msg, index) of splitFieldsErrMsg" :key="index">
				{{ msg }}
			</p>
		</div>
	</div>
</template>

<script>
import form from '@/mixins/form'
import { loginUserBody, registerUserBody } from '@/services/requestBody'
import { setup, isFormValid, nameValidator, phoneValidator, passwordValidator } from '@/validators/validators'
import { helpers, required } from '@vuelidate/validators'
import LoaderLine from '@/components/UI/LoaderLine.vue'
import SvgIcon from '@/components/UI/SvgIcon.vue'
import { mapActions, mapGetters } from 'vuex'

export default {
	setup,
	props: {
		form: {
			type: Object,
			required: true
		},
		fields: {
			type: Array,
			required: true
		}
	},
	components: {
		SvgIcon,
		LoaderLine
	},
	data() {
		return {
			user: null,
			isActive: null
		}
	},
	computed: {
		isFormValid,
		...mapGetters('auth', { isRegisterSuccess: 'isRegisterSuccess' }),
		...mapGetters('request', { isRequesting: 'isRequesting', errMsg: 'getErrMsg' })
	},
	methods: {
		initUser() {
			if (this.form.key === 'logIn') {
				this.user = { phone: '', password: '' }
			}
			if (this.form.key === 'signUp') {
				this.user = { name: '', phone: '', password: '', passwordConfirmation: '' }
			}
			this.v$.$reset()
		},

		handleForm() {
			if (!this.isRequesting) {
				if (this.form.key === 'logIn') {
					this.handleLogin()
				}
				if (this.form.key === 'signUp') {
					this.handleRegister()
				}
			}
		},
		handleLogin() {
			if (this.isFormValid) {
				this.login(loginUserBody(this.formatPhoneField(this.user.phone), this.user.password))
				this.resetForm()
			}
		},
		handleRegister() {
			if (this.isFormValid) {
				this.register(registerUserBody(this.user.name, this.formatPhoneField(this.user.phone), this.user.password))
				this.resetForm()
			}
		},

		formatPhoneField(phone) {
			let phoneFormatted = phone
			if (phoneFormatted.startsWith('+')) {
				phoneFormatted = phoneFormatted.slice(1)
			}
			if (phoneFormatted.startsWith('8')) {
				phoneFormatted = '7' + phoneFormatted.slice(1)
			}
			return phoneFormatted
		},

		...mapActions('auth', ['login', 'register']),
		...mapActions('request', ['clearErrMsg'])
	},
	mixins: [form],
	validations() {
		if (this.form.key === 'logIn') {
			return {
				user: {
					phone: {
						...phoneValidator
					},
					password: {
						...passwordValidator
					}
				}
			}
		}
		if (this.form.key === 'signUp') {
			return {
				user: {
					name: {
						...nameValidator
					},
					phone: {
						...phoneValidator
					},
					password: {
						...passwordValidator
					},
					passwordConfirmation: {
						required: helpers.withMessage('confirm the entered password', required),
						sameAsPassword: helpers.withMessage(
							() => 'password are not matching',
							value => value === this.user.password
						)
					}
				}
			}
		}
	},
	beforeMount() {
		this.clearErrMsg()
		this.initUser()
	}
}
</script>
