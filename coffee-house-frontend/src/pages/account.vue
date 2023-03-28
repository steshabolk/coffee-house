<template>
	<section id="account-section">
		<div class="wrapper-content" style="padding-bottom: 0">
			<div class="container">
				<div v-if="!isAnyAccountLogged" class="auth-wrapper">
					<div class="grid">
						<div class="grid-column">
							<div class="grid-cell">
								<img class="random-img" :src="imgPath(randomImage())" />
							</div>
						</div>
						<div class="grid-column">
							<div class="grid-cell">
								<div class="inline-btn-wrapper">
									<button class="btn-list inline-btn-left" :class="{ 'btn-list-active': logIn.isActive }" @click="changeForm(logIn.key)">
										Log in
									</button>
									<button class="btn-list inline-btn-right" :class="{ 'btn-list-active': signUp.isActive }" @click="changeForm(signUp.key)">
										Sign up
									</button>
								</div>
								<transition-group name="fade" mode="out-in">
									<AuthForm
										v-if="logIn.isActive"
										:form="{
											key: logIn.key,
											title: logIn.title,
											description: logIn.description,
											btnText: logIn.btnText
										}"
										:fields="logIn.fields" />
									<AuthForm
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
				<div v-if="userIsLogged" class="logged-account-wrapper">
					<LoggedAccount :loggedAccount="user" :menu="loggedAccount.userMenu" :activeMenuIndexProp="0" />
				</div>
				<div v-if="managerIsLogged" class="logged-account-wrapper">
					<LoggedAccount :loggedAccount="manager" :menu="loggedAccount.managerMenu" :activeMenuIndexProp="0" />
				</div>
			</div>
		</div>
	</section>
</template>

<script>
import { mapActions, mapGetters } from 'vuex'
import { nameField, phoneField, passwordField, passwordConfirmationField } from '@/services/inputFields'
import { isAnyAccountLogged } from '@/services/userService'
import imgPath from '@/mixins/imgPath'
import AuthForm from '@/components/AuthForm.vue'
import LoggedAccount from '@/components/LoggedAccount.vue'
import UOrders from '@/components/UOrders.vue'
import UAccountSettings from '@/components/UAccountSettings.vue'

export default {
	components: {
		AuthForm,
		LoggedAccount,
		UOrders,
		UAccountSettings
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
			},
			loggedAccount: {
				userMenu: ['Orders', 'Settings', 'Logout'],
				managerMenu: ['Management', 'Logout']
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
		isAnyAccountLogged,
		...mapGetters('auth', { isRegisterSuccess: 'isRegisterSuccess' }),
		...mapGetters('user', { userIsLogged: 'isLogged', user: 'getUser' }),
		...mapGetters('manager', { managerIsLogged: 'isLogged', manager: 'getManager' }),
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
	mixins: [imgPath]
}
</script>
