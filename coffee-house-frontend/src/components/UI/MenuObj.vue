<template>
	<div class="grid-cell" :class="{ 'menu-item-active': generalCategory === 'food' && isSelected }">
		<div class="vertical-line"></div>
		<div class="menu-item">
			<p class="menu-item-title main-title">
				{{ product.productName }} <span class="menu-item-price"><span v-if="generalCategory === 'additives'">+</span>{{ product.price }}&#8381;</span>
			</p>
			<p v-if="generalCategory !== 'additives'" class="menu-item-description">{{ product.description }}</p>
			<div v-if="generalCategory !== 'additives'" class="btn-wrapper">
				<p class="menu-item-size">{{ product.sizeValue }}</p>
				<QuantityBtnGroup v-if="generalCategory === 'food'" :cartObj="cartObj" />
			</div>
		</div>
	</div>
</template>

<script>
import QuantityBtnGroup from '@/components/UI/QuantityBtnGroup.vue'
import { mapGetters } from 'vuex'

export default {
	props: {
		product: {
			type: Object,
			required: true
		},
		generalCategory: {
			type: String,
			required: true
		}
	},
	components: {
		QuantityBtnGroup
	},
	methods: {},
	computed: {
		isSelected() {
			return this.cart.find(obj => JSON.stringify(obj.product) === JSON.stringify(this.product))
		},
		cartObj() {
			const obj = this.cart.find(obj => JSON.stringify(obj.product) === JSON.stringify(this.product))
			const quantity = obj ? obj.quantity : 0
			const cost = obj ? obj.cost : 0
			return { product: this.product, quantity: quantity, cost: cost, additives: [] }
		},
		...mapGetters('cart', { cart: 'getCart' })
	}
}
</script>
