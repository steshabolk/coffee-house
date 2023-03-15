<template>
	<div class="menu-category-title">{{ category }}</div>
	<div v-for="product in products" :key="product.id">
		<label :for="product.id">
			<input
				type="radio"
				name="coffee"
				:id="product.id"
				:value="product.id"
				:checked="selectedCoffee === product"
				@click="inputRadio(product, 'Coffee')" />
			<MenuObj :product="product" :generalCategory="'coffee'" />
		</label>
	</div>
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
