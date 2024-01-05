<template>
	<div class="btn-block-wrapper btn-block-wrapper-col active-order">
		<button class="main-btn btn-disable active-order__received" :class="{ 'btn-active': !isRequesting }" @click="receiveOrder(orderId)">
			received
		</button>
		<button class="main-btn btn-disable active-order__cancelled" :class="{ 'btn-active': !isRequesting }" @click="cancelOrder(orderId)">
			canceled
		</button>
	</div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex'
import SvgIcon from '@/components/UI/SvgIcon.vue'
import { plusIcon, confirmIcon } from '@/services/svgIcons'
import { orderStatus } from '@/services/orderStatus'
import { closeActiveOrderParams } from '@/services/requestBody'

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
				this.closeActiveOrder(closeActiveOrderParams(id, orderStatus.cancelled))
			}
		},
		receiveOrder(id) {
			if (!this.isRequesting) {
				this.closeActiveOrder(closeActiveOrderParams(id, orderStatus.received))
			}
		},
		...mapActions('manager', ['closeActiveOrder'])
	},
	computed: {
		...mapGetters('request', { isRequesting: 'isRequesting' })
	}
}
</script>
