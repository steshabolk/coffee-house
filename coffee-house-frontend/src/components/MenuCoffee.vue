<template>
	<div>
		<div class="grid">
			<div class="grid-column">
				<MenuCoffeeCategory :category="'Classic'" :products="activeProducts.coffeeClassic" />
			</div>
			<div class="grid-column">
				<MenuCoffeeCategory :category="'Specialty'" :products="activeProducts.coffeeSpecialty" />
				<MenuAdditivesCategory :category="'Topping'" :products="activeProducts.topping" />
			</div>
			<div class="grid-column">
				<MenuAdditivesCategory :category="'Milk'" :products="activeProducts.milk" />
				<MenuAdditivesCategory :category="'Syrup'" :products="activeProducts.syrup" />
			</div>
		</div>
		<button
			class="main-btn btn-disable"
			:class="{ 'btn-active': selectedCoffee }"
			style="display: flex; flex-direction: row; align-items: center; margin-bottom: 15px"
			@click="addSelectedToCart">
			<SvgIcon style="width: 30px; height: 30px" :viewBox="plusIcon.viewBox" :path="plusIcon.svgPath" />
			<SvgIcon style="width: 40px; height: 40px" :viewBox="cartIcon.viewBox" :path="cartIcon.svgPath" />
		</button>
	</div>
</template>

<script>
import MenuCoffeeCategory from '@/components/MenuCoffeeCategory.vue'
import MenuAdditivesCategory from '@/components/MenuAdditivesCategory.vue'
import SvgIcon from '@/components/UI/SvgIcon.vue'
import { plusIcon, cartIcon } from '@/services/svgIcons'
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
			const cartObj = {
				product: this.selectedCoffee,
				quantity: 0,
				cost: 0,
				additives: arr
			}
			this.addInCart({ cartObj: cartObj, num: 1 })
			this.resetSelected()
		},
		...mapActions('cart', ['addInCart']),
		...mapActions('selectedCoffeeItems', ['resetSelected'])
	},
	computed: {
		...mapGetters('products', {
			activeProducts: 'getActiveProducts'
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
