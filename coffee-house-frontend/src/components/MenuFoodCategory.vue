<template>
	<div class="menu-category-title title_lines">{{ category }}</div>
	<MenuObj v-for="product in products" :key="product.id" :product="product" :generalCategory="'food'" />
</template>

<script>
import MenuObj from '@/components/UI/MenuObj.vue'

export default {
	props: {
		category: {
			type: String,
			required: true
		},
		products: {
			type: Array,
			required: true
		}
	},
	components: {
		MenuObj
	}
}
</script>
