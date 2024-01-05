<template>
	<div class="menu-category-title title_lines">{{ category }}</div>
	<label :for="product.id" v-for="product in products" :key="product.id">
		<input
			type="radio"
			name="coffee"
			:id="product.id"
			:value="product.id"
			:checked="selectedCoffee === product"
			@click="inputRadio(product, 'Coffee')" />
		<MenuObj :product="product" :generalCategory="'coffee'" />
	</label>
</template>

<script>
import MenuObj from '@/components/UI/MenuObj.vue'
import inputFormat from '@/mixins/inputFormat'
import { mapGetters } from 'vuex'

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
	},
	computed: {
		...mapGetters('selectedCoffeeItems', { selectedCoffee: 'getSelectedCoffee' })
	},
	mixins: [inputFormat]
}
</script>
