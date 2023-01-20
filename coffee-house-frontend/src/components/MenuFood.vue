<template>
	<div class="grid">
		<div class="grid-column">
			<MenuFoodCategory :category="'Food'" :products="activeProducts.food" />
		</div>
		<div class="grid-column">
			<MenuFoodCategory :category="'Pastries'" :products="activeProducts.pastries" />
		</div>
		<div class="grid-column">
			<MenuFoodCategory :category="'Desserts'" :products="activeProducts.desserts" />
		</div>
	</div>
</template>

<script>
import MenuFoodCategory from '@/components/MenuFoodCategory.vue'
import { mapGetters } from 'vuex'

export default {
	components: {
		MenuFoodCategory
	},
	computed: {
		...mapGetters('products', {
			activeProducts: 'getActiveProducts'
		})
	}
}
</script>
