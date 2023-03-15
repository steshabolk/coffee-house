<template>
	<div class="btn-qty-block">
		<button v-if="cartObj.quantity" class="btn-quantity" @click="changeQuantity(-1)">
			<SvgIcon :viewBox="minusIcon.viewBox" :path="minusIcon.svgPath" />
		</button>
		<p v-if="cartObj.quantity" class="item-quantity">{{ cartObj.quantity }}</p>
		<button class="btn-quantity" :class="{ 'btn-quantity-disable': cartObj.quantity === 10 }" @click="changeQuantity(1)">
			<SvgIcon :viewBox="plusIcon.viewBox" :path="plusIcon.svgPath" />
		</button>
	</div>
</template>

<script>
import changeQuantity from '@/mixins/changeCartQuantity'
import { plusIcon, minusIcon } from '@/services/svgIcons'
import SvgIcon from '@/components/UI/SvgIcon.vue'

export default {
	props: {
		cartObj: {
			type: Object,
			required: true
		}
	},
	components: {
		SvgIcon
	},
	data() {
		return {
			plusIcon,
			minusIcon
		}
	},
	mixins: [changeQuantity]
}
</script>
