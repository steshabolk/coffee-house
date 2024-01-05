<template>
	<section id="management-section">
		<div class="wrapper-content">
			<div class="container">
				<div v-if="isCHLoaded" style="display: flex; align-items: center; justify-content: center">
					<SvgIcon style="width: 1rem; height: 1rem; margin-right: 0.5rem" :viewBox="locationIcon.viewBox" :path="locationIcon.svgPath" />
					<p style="text-align: center; font-weight: bold">{{ coffeeHouse }}</p>
				</div>
				<ListBtnGroup
					style="margin-top: 0.5rem; margin-bottom: 0.8rem"
					:keyI="'management'"
					:objectsArray="managementGroup"
					:activeId="activeId"
					:toggleMethod="toggleMenu" />
				<transition-group name="fade-slot" mode="out-in">
					<MOrders v-if="activeId === 1" :key="menuIndex" />
					<MMenu v-if="activeId === 2" :key="menuIndex" />
					<MSearchOrdersBar v-if="activeId === 3" :key="menuIndex" />
				</transition-group>
			</div>
		</div>
	</section>
</template>

<script>
import { mapGetters, mapActions } from 'vuex'
import { locationIcon } from '@/services/svgIcons.js'
import SvgIcon from '@/components/UI/SvgIcon.vue'
import MMenu from '@/components/MMenu.vue'
import MOrders from '@/components/MOrders.vue'
import MSearchOrdersBar from '@/components/MSearchOrdersBar.vue'
import ListBtnGroup from '@/components/UI/ListBtnGroup.vue'

export default {
	components: { ListBtnGroup, MMenu, MOrders, SvgIcon, MSearchOrdersBar },
	data() {
		return {
			locationIcon,
			managementGroup: [
				{
					id: 1,
					btnText: 'active'
				},
				{
					id: 2,
					btnText: 'menu'
				},
				{
					id: 3,
					btnText: 'history'
				}
			],
			activeId: 1,
			menuIndex: 1
		}
	},
	methods: {
		toggleMenu(id) {
			if (this.activeId != id) {
				this.activeId = id
				this.menuIndex++
			}
		}
	},
	computed: {
		...mapGetters('manager', { coffeeHouse: 'getCoffeeHouse', isCHLoaded: 'isCHLoaded' })
	}
}
</script>
