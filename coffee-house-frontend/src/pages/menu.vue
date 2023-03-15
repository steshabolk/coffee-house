<template>
	<section id="menu-section">
		<div class="wrapper-content">
			<div class="container">
				<LoaderCard v-if="!(isAddressesLoaded && isUserMenuLoaded)" />
				<div v-else class="menu-wrapper">
					<!-- ADDRESSES -->
					<ListBtnGroup
						style="font-size: 0.9rem; line-height: 1.2; margin-bottom: 0.5rem"
						:keyI="'address'"
						:objectsArray="addresses"
						:activeId="activeAddressId"
						:toggleMethod="toggleAddress" />
					<!-- CATEGORY -->
					<ListBtnGroup :keyI="'menuCategory'" :objectsArray="categories" :activeId="activeCategoryId" :toggleMethod="toggleCategory" />
					<!-- MENU -->
					<transition-group name="fade" mode="out-in">
						<MenuCoffee v-if="activeCategoryId === 1" :key="menuIndex" />
						<MenuFood v-if="activeCategoryId === 2" :key="menuIndex" />
						<Cart v-if="activeCategoryId === 3" :key="menuIndex" />
					</transition-group>
				</div>
			</div>
		</div>
	</section>
</template>

<script>
import { drinkIcon, foodIcon, cartIcon } from '@/services/svgIcons'
import { mapGetters, mapActions } from 'vuex'
import MenuCoffee from '@/components/MenuCoffee.vue'
import MenuFood from '@/components/MenuFood.vue'
import Cart from '@/components/Cart.vue'
import LoaderCard from '@/components/UI/LoaderCard.vue'
import ListBtnGroup from '@/components/UI/ListBtnGroup.vue'

export default {
	components: {
		MenuCoffee,
		MenuFood,
		Cart,
		LoaderCard,
		ListBtnGroup
	},
	data() {
		return {
			categories: [
				{
					id: 1,
					...drinkIcon
				},
				{
					id: 2,
					...foodIcon
				},
				{
					id: 3,
					...cartIcon
				}
			],
			activeCategoryId: 1,
			menuIndex: 1
		}
	},
	methods: {
		toggleAddress(id) {
			if (this.activeAddressId != id) {
				this.setActiveAddressId(id)
				for (const coffeeHouseMenu of this.menu) {
					if (coffeeHouseMenu.id === id) {
						this.setActiveUserMenu(coffeeHouseMenu.products)
						this.updateCartAvailability()
						this.menuIndex++
						break
					}
				}
			}
		},
		toggleCategory(id) {
			if (this.activeCategoryId != id) {
				this.activeCategoryId = id
			}
		},
		...mapActions('addresses', ['requestAddresses', 'setActiveAddressId']),
		...mapActions('menu', ['requestUserMenu', 'setActiveUserMenu']),
		...mapActions('cart', ['updateCartAvailability'])
	},
	computed: {
		...mapGetters('addresses', { addresses: 'getAddresses', isAddressesLoaded: 'isLoaded', activeAddressId: 'getActiveAddressId' }),
		...mapGetters('menu', { isUserMenuLoaded: 'isUserMenuLoaded', menu: 'getUserMenu' })
	},
	beforeMount() {
		this.requestAddresses()
		this.requestUserMenu()
	}
}
</script>
