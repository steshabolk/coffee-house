<template>
	<div class="menu-products grid menu-products__lg_gap">
		<div class="grid-column" style="width: 33%">
			<MenuFoodCategory :category="'Food'" :products="userActiveMenu.food" />
		</div>
		<div class="grid-column" style="width: 33%">
			<MenuFoodCategory :category="'Pastries'" :products="userActiveMenu.pastries" />
		</div>
		<div class="grid-column" style="width: 33%">
			<MenuFoodCategory :category="'Desserts'" :products="userActiveMenu.desserts" />
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
		...mapGetters('menu', {
			userActiveMenu: 'getUserActiveMenu'
		})
	}
}
</script>
