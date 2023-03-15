<template>
	<section id="management-section">
		<div class="wrapper-content">
			<div class="container">
				<p v-if="isAddressLoaded" class="main-title main-title__center">{{ coffeeHouseAddress.address }}</p>
				<ListBtnGroup
					style="margin-top: 0.5rem; margin-bottom: 0.8rem"
					:keyI="'management'"
					:objectsArray="managementGroup"
					:activeId="activeId"
					:toggleMethod="toggleMenu" />
				<MOrders v-if="activeId === 1" />
				<MMenu v-if="activeId === 2" />
			</div>
		</div>
	</section>
</template>

<script>
import { mapGetters, mapActions } from 'vuex'
import MMenu from '@/components/MMenu.vue'
import MOrders from '@/components/MOrders.vue'
import ListBtnGroup from '@/components/UI/ListBtnGroup.vue'

export default {
	components: { ListBtnGroup, MMenu, MOrders },
	data() {
		return {
			managementGroup: [
				{
					id: 1,
					btnText: 'Orders'
				},
				{
					id: 2,
					btnText: 'Menu'
				}
			],
			activeId: 1
		}
	},
	methods: {
		toggleMenu(id) {
			if (this.activeId != id) {
				this.activeId = id
			}
		}
	},
	computed: {
		...mapGetters('manager', { coffeeHouseAddress: 'getAddress', isAddressLoaded: 'isAddressLoaded' })
	}
}
</script>
