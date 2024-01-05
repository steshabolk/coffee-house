<template>
	<LoaderCard v-if="!isOrdersLoaded" style="height: 60vh" />
	<custom-scrollbar v-else-if="orders.length > 0" class="order-scrollbar" :autoHide="false" ref="scroll">
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
						<p style="font-weight: bold">{{ order.totalCost }}&#8381;</p>
						<p v-if="order.closedAt">{{ hh_mm__dd_mm_yyyy(order.closedAt) }}</p>
						<p v-else>pick up at:&#10240;{{ hh_mm(order.pickUpAt) }}</p>
					</div>
				</template>
				<template #accordion-not-trigger>
					<div v-if="!order.closedAt">
						<button
							v-if="cancelledId !== order.id"
							style="margin-bottom: 0; margin-top: 0.8rem; width: 80%"
							class="main-btn"
							:class="{ 'btn-active': !isRequesting, 'btn-disable': isRequesting }"
							@click="activateConfirmation(order.id)">
							Cancel
						</button>
						<transition-group name="fade-slot" mode="out-in">
							<div v-if="cancelledId === order.id">
								<p style="text-align: center; margin-top: 0.5rem">{{ confirmationMsg }}</p>
								<LoaderLine style="margin: 2vw auto" />
								<div v-if="!isRequesting" class="inline-btn-wrapper" style="margin-top: 1vw">
									<button
										class="main-btn inline-btn-left"
										:class="{ 'btn-active': !isRequesting, 'btn-disable': isRequesting }"
										@click="disableConfirmation()">
										No
									</button>
									<button
										class="main-btn inline-btn-right"
										:class="{ 'btn-active': !isRequesting, 'btn-disable': isRequesting }"
										@click="cancelOrderHandler()">
										Yes
									</button>
								</div>
								<ErrorCloseable v-if="!isRequesting" key="1" />
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
		<p style="text-align: center">you don't have any orders yet</p>
		<ArrowLink :link="menuLink.link" :linkText="menuLink.linkText" />
	</div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex'
import { links } from '@/_config'
import additivesNames from '@/mixins/textFormat'
import SvgIcon from '@/components/UI/SvgIcon.vue'
import Accordion from '@/components/UI/Accordion.vue'
import AccordionItem from '@/components/UI/AccordionItem.vue'
import ArrowLink from '@/components/UI/ArrowLink.vue'
import LoaderLine from '@/components/UI/LoaderLine.vue'
import LoaderCard from '@/components/UI/LoaderCard.vue'
import CustomScrollbar from 'custom-vue-scrollbar'
import { successIcon, plusIcon, clockIcon } from '@/services/svgIcons'
import { hh_mm__dd_mm_yyyy, hh_mm } from '@/services/helper'
import { orderStatus } from '@/services/orderStatus'
import ErrorCloseable from '@/components/UI/ErrorCloseable.vue'

export default {
	components: {
		SvgIcon,
		Accordion,
		AccordionItem,
		CustomScrollbar,
		ArrowLink,
		LoaderCard,
		LoaderLine,
		ErrorCloseable
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
		hh_mm__dd_mm_yyyy,
		hh_mm,
		activateConfirmation(id) {
			this.clearErrMsg()
			this.cancelledId = id
		},
		disableConfirmation() {
			this.clearErrMsg()
			if (!this.isRequesting) {
				this.cancelledId = null
			}
		},
		cancelOrderHandler() {
			if (!this.isRequesting) {
				this.cancelOrder(this.cancelledId)
				this.disableConfirmation()
			}
		},
		...mapActions('user', ['requestOrders', 'cancelOrder']),
		...mapActions('request', ['clearErrMsg'])
	},
	computed: {
		...mapGetters('user', { orders: 'getOrders', isOrdersLoaded: 'isOrdersLoaded' }),
		...mapGetters('request', { isRequesting: 'isRequesting' })
	},
	mixins: [additivesNames],
	mounted() {
		this.clearErrMsg()
	}
}
</script>
