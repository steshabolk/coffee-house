<template>
	<div>
		<div class="menu-products grid" style="gap: 6rem">
			<div class="grid-column" style="width: 50%">
				<MenuCoffeeCategory :category="'Classic'" :products="userActiveMenu.coffeeClassic" />
				<MenuCoffeeCategory :category="'Specialty'" :products="userActiveMenu.coffeeSpecialty" />
			</div>
			<div class="grid-column" style="width: 30%; gap: 0.8rem">
				<MenuAdditivesCategory :category="'Topping'" :products="userActiveMenu.topping" />
				<MenuAdditivesCategory :category="'Milk'" :products="userActiveMenu.milk" />
				<MenuAdditivesCategory :category="'Syrup'" :products="userActiveMenu.syrup" />
			</div>
		</div>
		<button
			class="main-btn btn-disable main-btn-bg"
			:class="{ 'btn-active': selectedCoffee }"
			style="position: fixed; bottom: 0; right: 0; margin: 0 2rem 2rem 0; z-index: 99"
			@click="addSelectedToCart">
			<SvgIcon style="width: 2rem; height: 2rem" :viewBox="plusIcon.viewBox" :path="plusIcon.svgPath" />
		</button>
	</div>
</template>

<script>
import { plusIcon } from '@/services/svgIcons'
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
			plusIcon
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
