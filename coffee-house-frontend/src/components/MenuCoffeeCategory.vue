<template>
	<div>
		<div class="menu-category-title">{{ category }}</div>
		<div v-for="product in products" :key="product.id">
			<label :for="product.id">
				<input
					type="radio"
					name="coffee"
					:id="product.id"
					:value="product.id"
					:checked="selectedCoffee === product"
					@click="checkRadio(product, 'Coffee')" />
				<MenuObj :product="product" :generalCategory="'coffee'" />
			</label>
		</div>
	</div>
</template>

<script>
import MenuObj from '@/components/UI/MenuObj.vue'
import checkInput from '@/mixins/checkInput'
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
	mixins: [checkInput]
}
</script>
