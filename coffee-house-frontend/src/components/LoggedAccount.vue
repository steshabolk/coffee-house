<template>
	<div class="grid">
		<div class="grid-column grid-column__acc">
			<div class="grid-cell">
				<p class="account-name">{{ user.name }}</p>
				<p class="account-phone">+{{ user.phone }}</p>
				<p class="horizontal-line" />
				<ul class="account-menu-list">
					<li
						class="account-menu-item"
						v-for="(item, index) of menu"
						:key="index"
						:class="{ 'account-menu-item__active': index === activeMenuInd }"
						@click="changeActiveMenu(index)">
						{{ item }}
					</li>
				</ul>
			</div>
		</div>
		<div class="grid-column grid-column__acc-menu">
			<transition-group name="fade" mode="out-in">
				<AccountOrders v-if="activeMenuInd === menu.indexOf('Orders')" />
				<AccountSettings v-if="activeMenuInd === menu.indexOf('Settings')" />
			</transition-group>
		</div>
	</div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex'
import AccountOrders from '@/components/AccountOrders.vue'
import AccountSettings from '@/components/AccountSettings.vue'

export default {
	components: {
		AccountOrders,
		AccountSettings
	},
	data() {
		return {
			menu: ['Orders', 'Settings', 'Logout'],
			activeMenuInd: 0
		}
	},
	methods: {
		...mapActions('auth', ['logout']),
		changeActiveMenu(setActiveInd) {
			if (setActiveInd === this.menu.indexOf('Logout')) {
				this.logout()
			}
			if (this.activeMenu !== setActiveInd) {
				this.activeMenuInd = setActiveInd
			}
		}
	},
	computed: {
		...mapGetters('user', { user: 'getUser' })
	}
}
</script>
