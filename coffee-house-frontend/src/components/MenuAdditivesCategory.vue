<template>
	<div>
		<div class="menu-category-title">{{ category }}</div>
		<div v-for="product in products" :key="product.id">
			<label :for="product.id">
				<input
					v-if="category === 'Milk'"
					type="radio"
					:name="category"
					:id="product.id"
					:value="product.id"
					:checked="selectedMilk === product"
					@click="checkRadio(product, category)" />
				<input
					v-else
					type="checkbox"
					:name="category"
					:id="product.id"
					:value="product.id"
					:checked="select(product, category)"
					@click="checkCheckbox(product, category)" />
				<MenuObj :product="{ productName: additives(category, product.productName), price: product.price }" :generalCategory="'additives'" />
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
	methods: {
		additives(category, name) {
			if (category === 'Milk' || category === 'Syrup') {
				return name.split(' ')[0]
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
	mixins: [checkInput]
}
</script>
