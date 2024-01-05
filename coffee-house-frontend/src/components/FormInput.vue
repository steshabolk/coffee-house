<template>
	<div class="main-form-wrapper settings-form-wrapper">
		<form @submit.prevent="handleForm" autocomplete="off">
			<div
				v-for="(field, index) of fields"
				:key="index"
				class="main-input-wrapper settings-input-wrapper"
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
			<div v-if="form.btnText" class="main-input-wrapper settings-input-wrapper">
				<button style="margin-top: 0" type="submit" class="main-btn btn-disable" :class="{ 'btn-active': activeBtnCond() }">
					{{ form.btnText }}
				</button>
			</div>
		</form>
		<div v-if="errMsg" class="request-fail" style="margin-bottom: 2rem">
			<p class="main-error-message" style="margin-top: 0" v-for="(msg, index) of splitFieldsErrMsg" :key="index">
				{{ msg }}
			</p>
		</div>
	</div>
</template>

<script>
import form from '@/mixins/form'
import { changeNameBody, changePasswordBody } from '@/services/requestBody'
import { setup, isFormValid, nameValidator, passwordValidator } from '@/validators/validators'
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
		...mapGetters('user', { userStore: 'getUser' }),
		...mapGetters('request', { isRequesting: 'isRequesting', errMsg: 'getErrMsg' })
	},
	methods: {
		initUser() {
			if (this.form.key === 'changeName') {
				this.user = { name: this.userStore.name }
			}
			if (this.form.key === 'changePassword') {
				this.user = { password: '', passwordConfirmation: '' }
			}
			this.v$.$reset()
		},
		activeBtnCond() {
			if (this.form.key === 'changeName') {
				return this.isFormValid && this.user.name !== this.userStore.name
			}
			if (this.form.key === 'changePassword') {
				return this.isFormValid
			}
		},
		handleForm() {
			if (!this.isRequesting) {
				if (this.form.key === 'changeName') {
					this.handleChangeName()
				}
				if (this.form.key === 'changePassword') {
					this.handleChangePassword()
				}
			}
		},
		handleChangeName() {
			if (this.isFormValid && this.user.name !== this.userStore.name) {
				this.changeName(changeNameBody(this.user.name))
			}
		},
		handleChangePassword() {
			if (this.isFormValid) {
				this.changePassword(changePasswordBody(this.user.password))
				this.resetForm()
			}
		},
		...mapActions('user', ['changeName', 'changePassword']),
		...mapActions('request', ['clearErrMsg'])
	},
	mixins: [form],
	validations() {
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
