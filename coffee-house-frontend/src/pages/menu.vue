<template>
	<section id="menu-section">
		<div class="wrapper-content">
			<div class="container">
				<div class="menu-wrapper" v-if="addressesIsLoaded && productsIsLoaded">
					<!-- ADDRESSES -->
					<div class="menu-addresses">
						<ul class="addresses-list">
							<li
								class="address-item btn-list"
								v-for="address in addresses"
								:key="address.id"
								@click="toggleAddress(address.id)"
								:class="{ 'btn-list-active': activeAddressId === address.id }">
								<p>{{ addressList(address)[1] }}</p>
								<p class="address-item-text">{{ addressList(address)[0] }}</p>
							</li>
						</ul>
					</div>
					<!-- CATEGORY -->
					<div class="menu-category">
						<ul class="category-list">
							<li
								class="category-item btn-list"
								v-for="category in categories"
								:key="category.id"
								@click="toggleCategory(category.id)"
								:class="{ 'btn-list-active': activeCategoryId === category.id }">
								<SvgIcon :viewBox="category.viewBox" :path="category.svgPath" />
							</li>
						</ul>
					</div>
					<!-- PRODUCTS -->
					<div class="menu-products">
						<transition-group name="fade" mode="out-in">
							<MenuCoffee v-if="activeCategoryId === 1" :key="menuIndex" />
							<MenuFood v-if="activeCategoryId === 2" :key="menuIndex" />
							<Cart v-if="activeCategoryId === 3" :key="menuIndex" />
						</transition-group>
					</div>
				</div>
				<LoaderCard v-else style="min-height: 70vh" />
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
import SvgIcon from '@/components/UI/SvgIcon.vue'

export default {
	components: {
		MenuCoffee,
		MenuFood,
		Cart,
		LoaderCard,
		SvgIcon
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
		addressList(address) {
			return address.address.split(',')
		},
		toggleAddress(id) {
			if (this.activeAddressId != id) {
				this.setActiveAddressId(id)
				for (const coffeeHouseMenu of this.menu) {
					if (coffeeHouseMenu.id === id) {
						this.setActiveProducts(coffeeHouseMenu.products)
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
		...mapActions('products', ['requestProducts', 'setActiveProducts']),
		...mapActions('cart', ['updateCartAvailability'])
	},
	computed: {
		...mapGetters('addresses', { addresses: 'getAddresses', addressesIsLoaded: 'isLoaded', activeAddressId: 'getActiveAddressId' }),
		...mapGetters('products', {
			menu: 'getProducts',
			productsIsLoaded: 'isLoaded'
		})
	},
	beforeMount() {
		if (this.addresses.length === 0) {
			this.requestAddresses()
		}
		if (this.menu.length === 0) {
			this.requestProducts()
		}
	}
}
</script>
