<template>
	<section id="menu-section">
		<div class="wrapper-content">
			<div class="container">
				<LoaderCard v-if="!(isCHsLoaded && isUserMenuLoaded)" />
				<div v-else class="menu-wrapper">
					<!-- COFFEE HOUSES -->
					<ListBtnGroup
						style="font-size: 0.7rem; line-height: 1.2; margin-bottom: 0.2rem"
						:keyI="'address'"
						:objectsArray="coffeeHouses"
						:activeId="activeCHId"
						:toggleMethod="toggleCH" />
					<!-- CATEGORY -->
					<ListBtnGroup :keyI="'menuCategory'" :objectsArray="categories" :activeId="activeCategoryId" :toggleMethod="toggleCategory" />
					<!-- MENU -->
					<transition-group name="fade-slot" mode="out-in">
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
		toggleCH(id) {
			if (this.activeCHId != id) {
				this.setActiveCHId(id)
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
		...mapActions('coffeeHouse', ['requestCoffeeHouses', 'setActiveCHId']),
		...mapActions('menu', ['requestUserMenu', 'setActiveUserMenu']),
		...mapActions('cart', ['updateCartAvailability'])
	},
	computed: {
		...mapGetters('coffeeHouse', { coffeeHouses: 'getCoffeeHouses', isCHsLoaded: 'isLoaded', activeCHId: 'getActiveCHId' }),
		...mapGetters('menu', { isUserMenuLoaded: 'isUserMenuLoaded', menu: 'getUserMenu' })
	},
	beforeMount() {
		this.requestCoffeeHouses()
		this.requestUserMenu()
	}
}
</script>
