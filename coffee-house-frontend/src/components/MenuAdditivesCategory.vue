<template>
	<div class="menu-category-title title_lines">{{ category }}</div>
	<label :for="product.id" v-for="product in products" :key="product.id">
		<input
			v-if="category === 'Milk'"
			type="radio"
			:name="category"
			:id="product.id"
			:value="product.id"
			:checked="selectedMilk === product"
			@click="inputRadio(product, category)" />
		<input
			v-else
			type="checkbox"
			:name="category"
			:id="product.id"
			:value="product.id"
			:checked="select(product, category)"
			@click="inputCheckbox(product, category)" />
		<MenuObj :product="{ productName: additives(category, product.productName), price: product.price }" :generalCategory="'additives'" />
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
	methods: {
		additives(category, name) {
			if (category === 'Milk') {
				return name.split('Milk')[0]
			} else if (category === 'Syrup') {
				return name.split('Syrup')[0]
			} else {
				return name
			}
		},
		select(product, category) {
			if (category === 'Syrup') {
				return this.selectedSyrup.includes(product)
			}
			if (category === 'Topping') {
				return this.selectedTopping.includes(product)
			}
		}
	},
	computed: {
		...mapGetters('selectedCoffeeItems', {
			selectedMilk: 'getSelectedMilk',
			selectedSyrup: 'getSelectedSyrup',
			selectedTopping: 'getSelectedTopping'
		})
	},
	mixins: [inputFormat]
}
</script>
