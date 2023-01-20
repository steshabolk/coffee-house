<template>
	<div class="form-wrapper">
		<p v-if="form.title" class="main-title">{{ form.title }}</p>
		<p v-if="form.description" class="form-text">{{ form.description }}</p>
		<form @submit.prevent="handleForm">
			<div
				v-for="(field, index) of fields"
				:key="index"
				class="input-field"
				:class="{ 'input-field-error': v$.user[Object.keys(user)[index]].$errors.length !== 0 }">
				<div class="input-block" :class="{ 'input-block__active': isActive === index }">
					<SvgIcon :viewBox="field.icon.viewBox" :path="field.icon.svgPath" />
					<input
						v-model="user[Object.keys(user)[index]]"
						@focus="isActive = index"
						@blur="isActive = null"
						:id="field.input.id"
						:placeholder="field.input.placeholder"
						:type="field.input.type"
						:name="field.input.name" />
				</div>
				<div class="form-error-block">
					<p class="main-error-message form-error-msg" v-for="(error, index) of v$.user[Object.keys(user)[index]].$errors" :key="index">
						{{ error.$message }}
					</p>
				</div>
			</div>
			<LoaderLine v-if="isRequesting" />
			<p v-if="isRegisterSuccess" class="main-success-message" style="margin-top: 0">{{ registerSuccessMsg }}</p>
			<div v-if="form.btnText" class="input-field">
				<button
					v-if="form.key === 'changeName'"
					type="submit"
					class="main-btn btn-disable"
					:class="{ 'btn-active': isFormValid && user.name !== userI.name }">
					{{ form.btnText }}
				</button>
				<button v-else type="submit" class="main-btn btn-disable" :class="{ 'btn-active': isFormValid }">{{ form.btnText }}</button>
			</div>
		</form>
		<div v-if="errMsg.length !== 0" class="request-fail">
			<p class="main-error-message" style="font-size: 0.95rem; margin-top: 0" v-for="(errMsg, index) of splittedErrMsg" :key="index">
				{{ errMsg }}
			</p>
		</div>
	</div>
</template>

<script>
import form from '@/mixins/form'
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
			registerSuccessMsg: 'Registration Successful',
			isActive: null
		}
	},
	computed: {
		isFormValid,
		...mapGetters('auth', { isRegisterSuccess: 'isRegisterSuccess' }),
		...mapGetters('user', { userI: 'getUser' }),
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
			if (this.form.key === 'changeName') {
				this.user = { name: this.userI.name }
			}
			if (this.form.key === 'changePassword') {
				this.user = { password: '', passwordConfirmation: '' }
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
				if (this.form.key === 'changeName') {
					this.handleChangeName()
				}
				if (this.form.key === 'changePassword') {
					this.handleChangePassword()
				}
			}
		},
		handleLogin() {
			if (this.isFormValid) {
				this.login({ phone: this.phoneFormatted(this.user.phone), password: this.user.password })
				this.resetForm()
			}
		},
		handleRegister() {
			if (this.isFormValid) {
				this.register({ name: this.user.name, phone: this.phoneFormatted(this.user.phone), password: this.user.password })
				this.resetForm()
			}
		},
		handleChangeName() {
			if (this.isFormValid && this.user.name !== this.userI.name) {
				this.changeName({ name: this.user.name })
			}
		},
		handleChangePassword() {
			if (this.isFormValid) {
				this.changePassword({ password: this.user.password })
			}
		},
		...mapActions('auth', ['login', 'register']),
		...mapActions('user', ['changeName', 'changePassword']),
		...mapActions('request', ['setErrMsg'])
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
		if (this.form.key === 'changeName') {
			return {
				user: {
					name: {
						...nameValidator
					}
				}
			}
		}
		if (this.form.key === 'changePassword') {
			return {
				user: {
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
		if (this.errMsg) {
			this.setErrMsg('')
		}
		this.initUser()
	}
}
</script>
