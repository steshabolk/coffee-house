<template>
	<p class="cart-list-item">
		<span>{{ cartObj.product.productName }}</span>
		<span v-if="cartType === 'available'" class="btn-qty-wrapper">
			<QuantityBtnGroup :cartObj="cartObj" />
			<span class="cart-price">{{ cartObj.cost }}&#8381;</span>
			<SvgIcon class="close-btn" :viewBox="plusIcon.viewBox" :path="plusIcon.svgPath" @click="changeQuantity(-cartObj.quantity)" />
		</span>
	</p>
	<p v-if="cartObj.additives.length > 0" class="cart-list-additives">{{ additivesNames(cartObj.additives) }}</p>
</template>

<script>
import changeQuantity from '@/mixins/changeCartQuantity'
import additivesNames from '@/mixins/textFormat'
import { plusIcon } from '@/services/svgIcons'
import QuantityBtnGroup from '@/components/UI/QuantityBtnGroup.vue'
import SvgIcon from '@/components/UI/SvgIcon.vue'

export default {
	props: {
		cartObj: {
			type: Object,
			required: true
		},
		cartType: {
			type: String,
			required: true
		}
	},
	components: {
		QuantityBtnGroup,
		SvgIcon
	},
	data() {
		return {
			plusIcon
		}
	},
	mixins: [changeQuantity, additivesNames]
}
</script>
