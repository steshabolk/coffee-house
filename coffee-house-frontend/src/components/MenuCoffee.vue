<template>
	<div>
		<div class="menu-products grid">
			<div class="grid-column" style="width: 30%">
				<MenuCoffeeCategory :category="'Classic'" :products="userActiveMenu.coffeeClassic" />
			</div>
			<div class="grid-column" style="width: 45%">
				<MenuCoffeeCategory :category="'Specialty'" :products="userActiveMenu.coffeeSpecialty" />
				<MenuAdditivesCategory :category="'Topping'" :products="userActiveMenu.topping" />
			</div>
			<div class="grid-column" style="width: 25%">
				<MenuAdditivesCategory :category="'Milk'" :products="userActiveMenu.milk" />
				<MenuAdditivesCategory :category="'Syrup'" :products="userActiveMenu.syrup" />
			</div>
		</div>
		<button
			class="main-btn btn-disable"
			:class="{ 'btn-active': selectedCoffee }"
			style="display: flex; flex-direction: row; align-items: center; margin-top: 2rem"
			@click="addSelectedToCart">
			<SvgIcon style="width: 2rem; height: 2rem" :viewBox="plusIcon.viewBox" :path="plusIcon.svgPath" />
			<SvgIcon :viewBox="cartIcon.viewBox" :path="cartIcon.svgPath" />
		</button>
	</div>
</template>

<script>
import { plusIcon, cartIcon } from '@/services/svgIcons'
import { formCartObjBody, addInCartBody } from '@/services/requestBody'
import MenuCoffeeCategory from '@/components/MenuCoffeeCategory.vue'
import MenuAdditivesCategory from '@/components/MenuAdditivesCategory.vue'
import SvgIcon from '@/components/UI/SvgIcon.vue'
import { mapActions, mapGetters } from 'vuex'

export default {
	components: {
		MenuCoffeeCategory,
		MenuAdditivesCategory,
		SvgIcon
	},
	data() {
		return {
			plusIcon,
			cartIcon
		}
	},
	methods: {
		addSelectedToCart() {
			const arr = [...this.selectedSyrup, ...this.selectedTopping]
			if (this.selectedMilk) arr.unshift(this.selectedMilk)
			const cartObj = formCartObjBody(this.selectedCoffee, 0, 0, arr)
			this.addInCart(addInCartBody(cartObj, 1))
			this.resetSelected()
		},
		...mapActions('cart', ['addInCart']),
		...mapActions('selectedCoffeeItems', ['resetSelected'])
	},
	computed: {
		...mapGetters('menu', {
			userActiveMenu: 'getUserActiveMenu'
		}),
		...mapGetters('selectedCoffeeItems', {
			selectedCoffee: 'getSelectedCoffee',
			selectedMilk: 'getSelectedMilk',
			selectedSyrup: 'getSelectedSyrup',
			selectedTopping: 'getSelectedTopping'
		})
	}
}
</script>
