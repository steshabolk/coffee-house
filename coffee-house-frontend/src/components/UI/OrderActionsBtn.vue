<template>
	<div class="btn-block-wrapper btn-block-wrapper-col">
		<button class="main-btn btn-disable" :class="{ 'btn-active': !isRequesting }" @click="cancelOrder(orderId)">
			<SvgIcon class="close-btn" style="cursor: pointer" :viewBox="plusIcon.viewBox" :path="plusIcon.svgPath" />
		</button>
		<button class="main-btn btn-disable" :class="{ 'btn-active': !isRequesting }" @click="receiveOrder(orderId)">
			<SvgIcon :viewBox="confirmIcon.viewBox" :path="confirmIcon.svgPath" />
		</button>
	</div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex'
import SvgIcon from '@/components/UI/SvgIcon.vue'
import { plusIcon, confirmIcon } from '@/services/svgIcons'

import { updateOrderBody } from '@/services/requestBody'
import { orderStatus } from '@/services/orderStatus'

export default {
	props: {
		orderId: {
			type: Number,
			required: true
		}
	},
	components: {
		SvgIcon
	},
	data() {
		return {
			plusIcon,
			confirmIcon
		}
	},
	methods: {
		cancelOrder(id) {
			if (!this.isRequesting) {
				this.updateOrderStatus(updateOrderBody(id, orderStatus.cancelled))
			}
		},
		receiveOrder(id) {
			if (!this.isRequesting) {
				this.updateOrderStatus(updateOrderBody(id, orderStatus.received))
			}
		},
		...mapActions('manager', ['updateOrderStatus'])
	},
	computed: {
		...mapGetters('request', { isRequesting: 'isRequesting' })
	}
}
</script>
