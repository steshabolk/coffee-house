<template>
	<div class="main-form-wrapper auth-form-wrapper">
		<p v-if="form.title" class="main-title">{{ form.title }}</p>
		<p v-if="form.description" class="auth-form-desc">{{ form.description }}</p>
		<form @submit.prevent="handleForm">
			<div
				v-for="(field, index) of fields"
				:key="index"
				class="main-input-wrapper auth-input-wrapper"
				:class="{ 'input-field-error': v$.user[Object.keys(user)[index]].$invalid }">
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
					<p class="main-error-message form-error-msg" v-for="(error, index) of v$.user[Object.keys(user)[index]].$errors" :key="index">
						{{ error.$message }}
					</p>
				</div>
			</div>
			<LoaderLine v-if="isRequesting" style="margin-top: 0" />
			<p v-if="isRegisterSuccess" class="main-success-message" style="margin-top: 0">Registration Successful</p>
			<div v-if="form.btnText" class="main-input-wrapper auth-input-wrapper">
				<button type="submit" class="main-btn btn-disable" :class="{ 'btn-active': isFormValid }">{{ form.btnText }}</button>
			</div>
		</form>
		<div v-if="errMsg.length !== 0" class="request-fail">
			<p class="main-error-message" style="margin-top: 0" v-for="(errMsg, index) of splittedErrMsg" :key="index">
				{{ errMsg }}
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
				this.login(loginUserBody(this.phoneFormatted(this.user.phone), this.user.password))
				this.resetForm()
			}
		},
		handleRegister() {
			if (this.isFormValid) {
				this.register(registerUserBody(this.user.name, this.phoneFormatted(this.user.phone), this.user.password))
				this.resetForm()
			}
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
						required: helpers.withMessage('Confirm the entered password', required),
						sameAsPassword: helpers.withMessage(
							() => 'Password are not matching',
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
