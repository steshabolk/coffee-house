<template>
	<div class="grid">
		<div class="grid-column grid-column__acc">
			<div class="grid-cell">
				<p class="account-name">{{ loggedAccount.name }}</p>
				<p class="account-phone">+{{ loggedAccount.phone }}</p>
				<p class="horizontal-line" />
				<ul class="account-menu-list">
					<li
						class="account-menu-item"
						v-for="(item, index) of menu"
						:key="index"
						:class="{ 'account-menu-item__active': index === activeMenuIndex }"
						@click="changeActiveMenu(index)">
						{{ item }}
					</li>
				</ul>
				<p class="horizontal-line" />
			</div>
		</div>
		<div v-if="userIsLogged" class="grid-column grid-column__acc-menu">
			<transition-group name="fade-Y" mode="out-in">
				<UOrders v-if="activeMenuIndex === menu.indexOf('orders')" />
				<UAccountSettings v-if="activeMenuIndex === menu.indexOf('settings')" />
			</transition-group>
		</div>
		<div v-if="managerIsLogged" class="grid-column grid-column__acc-menu">
			<transition-group name="fade-Y" mode="out-in">
				<div v-if="activeMenuIndex === menu.indexOf('management')">
					<p style="text-align: center; margin-bottom: 0.5rem">go to management page</p>
					<ArrowLink :link="management.link" :linkText="management.linkText" />
				</div>
			</transition-group>
		</div>
	</div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex'
import { linksManager } from '@/_config'
import UOrders from '@/components/UOrders.vue'
import UAccountSettings from '@/components/UAccountSettings.vue'
import ArrowLink from '@/components/UI/ArrowLink.vue'

export default {
	props: {
		loggedAccount: {
			type: Object,
			required: true
		},
		menu: {
			type: Array,
			required: true
		},
		activeMenuIndexProp: {
			type: Number,
			required: true
		}
	},
	components: {
		UOrders,
		UAccountSettings,
		ArrowLink
	},
	data() {
		return {
			activeMenuIndex: null,
			management: {
				link: linksManager[1].url,
				linkText: 'Management'
			}
		}
	},
	methods: {
		...mapActions('auth', ['logout']),
		setActiveMenu() {
			this.activeMenuIndex = this.activeMenuIndexProp
		},
		changeActiveMenu(setActiveInd) {
			if (setActiveInd === this.menu.indexOf('logout')) {
				this.logout()
			}
			if (this.activeMenuIndex !== setActiveInd) {
				this.activeMenuIndex = setActiveInd
			}
		}
	},
	computed: {
		...mapGetters('user', { userIsLogged: 'isLogged' }),
		...mapGetters('manager', { managerIsLogged: 'isLogged' })
	},
	mounted() {
		this.setActiveMenu()
	}
}
</script>
