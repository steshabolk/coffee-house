<template>
	<grid-table :tableColumns="tableColumns" :colorRows="'even'">
		<grid-table-row v-for="(order, index) of orders" :key="index">
			<template #grid-tb-cell-1>{{ order.id }}</template>
			<template #grid-tb-cell-2>{{ order.totalCost }}</template>
			<template #grid-tb-cell-3>{{ hh_mm__dd_mm_yyyy(order.createdAt) }}</template>
			<template #grid-tb-cell-4>{{ hh_mm__dd_mm_yyyy(order.pickUpAt) }}</template>
			<template #grid-tb-cell-5>{{ order.closedAt ? hh_mm__dd_mm_yyyy(order.closedAt) : null }}</template>
			<template #grid-tb-cell-6>{{ toTitleCase(order.status) }}</template>
			<!-- ORDER DETAIL -->
			<template #grid-tb-cell-7>
				<div style="width: 100%; text-align: left">
					<div v-for="(orderDetail, index) of order.orderDetails" :key="index" style="margin: 0.3rem 0">
						<div class="order-details-block" style="margin: 0">
							<p class="order-details-name">
								<span>{{ orderDetail.product.productName }}</span
								><span class="order-details-quantity">&#215;&#10240;{{ orderDetail.quantity }}</span>
							</p>
							<p class="order-details-cost">{{ orderDetail.cost }}&#8381;</p>
						</div>
						<p v-if="orderDetail.additives.length > 0" class="order-details-additives" style="font-size: 0.55rem">
							{{ additivesNames(orderDetail.additives) }}
						</p>
					</div>
				</div>
			</template>
			<!-- ORDER ACTIONS -->
			<template #grid-tb-cell-8>
				<OrderActionsBtn v-if="order.status.toLowerCase() === 'active'" :orderId="order.id" />
			</template>
		</grid-table-row>
	</grid-table>
</template>

<script>
import { hh_mm__dd_mm_yyyy } from '@/services/helper'
import GridTable from '@/components/UI/GridTable.vue'
import GridTableRow from '@/components/UI/GridTableRow.vue'
import OrderActionsBtn from '@/components/UI/OrderActionsBtn.vue'
import textFormat from '@/mixins/textFormat'

export default {
	props: {
		orders: {
			type: Array,
			required: true
		}
	},
	data() {
		return {
			tableColumns: ['№', 'total cost', 'created at', 'pick up at', 'closed at', 'status', 'details', 'actions']
		}
	},
	methods: {
		hh_mm__dd_mm_yyyy
	},
	components: {
		GridTable,
		GridTableRow,
		OrderActionsBtn
	},
	mixins: [textFormat]
}
</script>
