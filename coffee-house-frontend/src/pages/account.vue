<template>
	<section id="account-section">
		<div class="wrapper-content">
			<div class="container">
				<div v-if="!isLogged" class="auth-form-wrapper">
					<div class="grid gap-h">
						<div class="grid-column">
							<div class="grid-cell">
								<img class="random-img" :src="imgPath(randomImage())" />
							</div>
						</div>
						<div class="grid-column">
							<div class="grid-cell">
								<div class="auth-btn-group">
									<button
										class="btn-list"
										:class="{ 'btn-list-active': logIn.isActive }"
										style="width: 45%; border-radius: 5px 0 0 5px"
										@click="changeForm(logIn.key)">
										Log in
									</button>
									<button
										class="btn-list"
										:class="{ 'btn-list-active': signUp.isActive }"
										style="width: 45%; border-radius: 0 5px 5px 0; border-left: 0"
										@click="changeForm(signUp.key)">
										Sign up
									</button>
								</div>
								<transition-group name="fade" mode="out-in">
									<FormObj
										v-if="logIn.isActive"
										:form="{
											key: logIn.key,
											title: logIn.title,
											description: logIn.description,
											btnText: logIn.btnText
										}"
										:fields="logIn.fields" />
									<FormObj
										v-if="signUp.isActive"
										:form="{
											key: signUp.key,
											title: signUp.title,
											description: signUp.description,
											btnText: signUp.btnText
										}"
										:fields="signUp.fields" />
								</transition-group>
							</div>
						</div>
					</div>
				</div>
				<div v-if="isLogged" class="account-wrapper">
					<LoggedAccount />
				</div>
			</div>
		</div>
	</section>
</template>

<script>
import { mapActions, mapGetters } from 'vuex'
import { nameField, phoneField, passwordField, passwordConfirmationField } from '@/services/inputFields'
import { checkTokenExpTime } from '@/services/userService'
import imgPath from '@/mixins/imgPath'
import FormObj from '@/components/FormObj.vue'
import LoggedAccount from '@/components/LoggedAccount.vue'

export default {
	components: {
		FormObj,
		LoggedAccount
	},
	data() {
		return {
			logIn: {
				key: 'logIn',
				isActive: true,
				title: 'Welcome!',
				description: 'Log in to your account to place an order',
				btnText: 'Log in',
				fields: [phoneField, passwordField]
			},
			signUp: {
				key: 'signUp',
				isActive: false,
				title: 'Welcome!',
				description: 'Before using our service you need to create an account',
				btnText: 'Create account',
				fields: [nameField, phoneField, passwordField, passwordConfirmationField]
			}
		}
	},
	methods: {
		...mapActions('auth', ['setIsRegisterSuccess']),
		changeForm(form) {
			if (!this.isRequesting) {
				if (form === 'logIn' && !this.logIn.isActive) {
					this.logIn.isActive = true
					this.signUp.isActive = false
				}
				if (form === 'signUp' && !this.signUp.isActive) {
					this.logIn.isActive = false
					this.signUp.isActive = true
				}
			}
		},
		randomImage() {
			const max = 8
			const min = 1
			return `random-${Math.floor(Math.random() * (max - min + 1)) + min}.png`
		}
	},
	computed: {
		...mapGetters('auth', { isRegisterSuccess: 'isRegisterSuccess' }),
		...mapGetters('user', { isLogged: 'isLogged' }),
		...mapGetters('request', { isRequesting: 'isRequesting' })
	},
	watch: {
		isRegisterSuccess(newValue) {
			if (newValue === true) {
				setTimeout(() => this.changeForm('logIn'), 2000)
				setTimeout(() => this.setIsRegisterSuccess(false), 2000)
			}
		}
	},
	mixins: [imgPath],
	mounted() {
		checkTokenExpTime()
	}
}
</script>
