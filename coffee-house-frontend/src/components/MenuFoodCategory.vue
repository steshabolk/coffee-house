<template>
	<!-- <div> -->
	<div class="menu-category-title">{{ category }}</div>
	<div v-for="product in products" :key="product.id">
		<MenuObj :product="product" :generalCategory="'food'" />
	</div>
	<!-- </div> -->
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
