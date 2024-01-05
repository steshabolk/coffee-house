<template>
	<div class="grid-cell" :class="{ 'menu-item-active': generalCategory === 'food' && isSelected, _additives: generalCategory === 'additives' }">
		<div class="menu-item">
			<p class="menu-item-title">
				<span :class="{ 'menu-item-title__additives': generalCategory === 'additives' }">{{ product.productName }}</span>
				<span class="menu-item-price"><span v-if="generalCategory === 'additives'">+</span>{{ product.price }}&#8381;</span>
			</p>
			<p v-if="generalCategory !== 'additives'" class="menu-item-description">{{ product.description }}</p>
			<div v-if="generalCategory !== 'additives'" class="btn-qty-wrapper">
				<p class="menu-item-size">{{ product.sizeValue }}</p>
				<QuantityBtnGroup v-if="generalCategory === 'food'" :cartObj="cartObj" />
			</div>
		</div>
	</div>
</template>

<script>
import { formCartObjBody } from '@/services/requestBody'
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
			return formCartObjBody(this.product, quantity, cost, [])
		},
		...mapGetters('cart', { cart: 'getCart' })
	}
}
</script>
