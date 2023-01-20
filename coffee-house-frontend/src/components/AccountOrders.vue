<template>
	<LoaderCard v-if="!isOrdersLoaded" style="min-height: 50vh" />
	<custom-scrollbar v-else-if="orders.length > 0" :autoHide="false" :style="{ height: '50vh' }" ref="scroll">
		<accordion>
			<accordion-item v-for="(order, index) of orders" :key="index">
				<template #accordion-trigger>
					<div
						class="order-status-block"
						:class="{
							status__active: order.status.toLowerCase() === 'active',
							status__received: order.status.toLowerCase() === 'received',
							status__cancelled: order.status.toLowerCase() === 'cancelled'
						}">
						<p>#{{ order.id }}</p>
						<SvgIcon v-if="order.status.toLowerCase() === 'active'" :viewBox="clockIcon.viewBox" :path="clockIcon.svgPath" />
						<SvgIcon v-if="order.status.toLowerCase() === 'received'" :viewBox="successIcon.viewBox" :path="successIcon.svgPath" />
						<SvgIcon
							v-if="order.status.toLowerCase() === 'cancelled'"
							style="transform: rotate(45deg)"
							:viewBox="plusIcon.viewBox"
							:path="plusIcon.svgPath" />
						<p class="order-status">{{ order.status }}</p>
					</div>
					<div class="order-cost-block">
						<p>{{ order.totalCost }}&#8381;</p>
						<p v-if="order.closedAt">{{ closedAt(order.closedAt) }}</p>
						<p v-else>pick up at:&#10240;{{ pickUpAt(order.pickUpAt) }}</p>
					</div>
				</template>
				<template #accordion-not-trigger>
					<div v-if="!order.closedAt">
						<transition-group name="fade-text" mode="out-in">
							<button
								v-if="cancelledId !== order.id"
								style="margin-bottom: 0; width: 100%"
								class="main-btn"
								:class="{ 'btn-active': !isRequesting, 'btn-disable': isRequesting }"
								@click="activateConfirmation(order.id)">
								Cancel
							</button>
							<div v-if="cancelledId === order.id">
								<p class="main-warning-message">{{ confirmationMsg }}</p>
								<LoaderLine v-if="isRequesting" style="margin: 20px auto" />
								<div v-if="!isRequesting" class="main-btn-group">
									<button
										class="main-btn"
										:class="{ 'btn-active': !isRequesting, 'btn-disable': isRequesting }"
										style="width: 45%; border-radius: 5px 0 0 5px"
										@click="disableConfirmation()">
										No
									</button>
									<button
										class="main-btn"
										:class="{ 'btn-active': !isRequesting, 'btn-disable': isRequesting }"
										style="width: 45%; border-radius: 0 5px 5px 0; border-left: 0"
										@click="cancelOrderHandler()">
										Yes
									</button>
								</div>
								<p class="main-error-message" v-if="errMsg">{{ errMsg }}</p>
							</div>
						</transition-group>
					</div>
				</template>
				<template #accordion-content>
					<div class="accordion-order-details">
						<p class="order-address">{{ order.coffeeHouse.address }}</p>
						<div v-for="(orderDetail, index) of order.orderDetails" :key="index">
							<div class="order-details-block">
								<p class="order-details-name">
									<span>{{ orderDetail.product.productName }}</span
									><span class="order-details-quantity">&#215;&#10240;{{ orderDetail.quantity }}</span>
								</p>
								<p class="order-details-cost">{{ orderDetail.cost }}&#8381;</p>
							</div>
							<p v-if="orderDetail.additives.length > 0" class="order-details-additives">{{ additivesNames(orderDetail.additives) }}</p>
						</div>
					</div>
				</template>
			</accordion-item>
		</accordion>
	</custom-scrollbar>
	<div v-else>
		<p class="orders-empty">You don't have any orders yet</p>
		<ArrowLink :link="menuLink.link" :linkText="menuLink.linkText" />
	</div>
</template>

<script>
import additivesNames from '@/mixins/additivesNames'
import dateFormat from '@/mixins/dateFormat'
import { mapGetters, mapActions } from 'vuex'
import SvgIcon from '@/components/UI/SvgIcon.vue'
import Accordion from '@/components/UI/Accordion.vue'
import AccordionItem from '@/components/UI/AccordionItem.vue'
import ArrowLink from '@/components/UI/ArrowLink.vue'
import LoaderLine from '@/components/UI/LoaderLine.vue'
import LoaderCard from '@/components/UI/LoaderCard.vue'
import CustomScrollbar from 'custom-vue-scrollbar'
import { successIcon, plusIcon, clockIcon } from '@/services/svgIcons'
import { links } from '@/_config'

export default {
	components: {
		SvgIcon,
		Accordion,
		AccordionItem,
		CustomScrollbar,
		ArrowLink,
		LoaderCard,
		LoaderLine
	},
	data() {
		return {
			successIcon,
			plusIcon,
			clockIcon,
			menuLink: {
				link: links[1].url,
				linkText: 'Menu'
			},
			cancelledId: null,
			confirmationMsg: 'Are you sure you want to cancel the order?'
		}
	},
	methods: {
		activateConfirmation(id) {
			this.cancelledId = id
		},
		disableConfirmation() {
			if (!this.isRequesting) {
				this.cancelledId = null
			}
		},
		cancelOrderHandler() {
			if (!this.isRequesting) {
				const cancelledOrder = {
					id: this.cancelledId,
					closedAt: this.toLocalDateTime(new Date())
				}
				this.cancelOrder(cancelledOrder)
			}
		},
		...mapActions('user', ['requestOrders', 'cancelOrder']),
		...mapActions('request', ['setErrMsg'])
	},
	computed: {
		...mapGetters('user', { orders: 'getOrders', isOrdersLoaded: 'isOrdersLoaded' }),
		...mapGetters('request', { isRequesting: 'isRequesting', errMsg: 'getErrMsg' })
	},
	mixins: [additivesNames, dateFormat],
	mounted() {
		if (this.errMsg) {
			this.setErrMsg('')
		}
		if (this.orders.length === 0 && !this.isOrdersLoaded) {
			this.requestOrders()
		}
	}
}
</script>
