<template>
	<div class="grid">
		<div class="grid-column no-gap">
			<div v-if="cart.length === 0" class="cart-empty">
				<SvgIcon :viewBox="cartEmpty.viewBox" :path="cartEmpty.svgPath" />
				<p class="main-title">{{ cartEmpty.msg }}</p>
			</div>
			<transition-group v-else name="list" tag="div" class="cart-wrapper">
				<div key="0" v-if="unavailableCart.length > 0" style="opacity: 0.7">
					<div class="cart-unavailable">
						<p class="cart-unavailable-title">Not available for ordering at the selected coffee house:</p>
						<SvgIcon class="close-btn" :viewBox="plusIcon.viewBox" :path="plusIcon.svgPath" @click="resetUnavailableCart()" />
					</div>
					<div class="cart-list" v-for="(item, index) of unavailableCart" :key="index">
						<CartObj :cartObj="item" :cartType="'unavailable'" />
					</div>
					<p class="horizontal-line" />
				</div>
				<div class="cart-list" v-for="(item, index) of cart" :key="index">
					<CartObj :cartObj="item" :cartType="'available'" />
				</div>
				<div key="0">
					<p class="horizontal-line" />
					<div class="cart-total">
						<p class="cart-total-text">
							Total : <span class="cart-price">{{ totalCost }}&#8381;</span>
						</p>
						<SvgIcon @click="resetCart()" :viewBox="removeIcon.viewBox" :path="removeIcon.svgPath" />
					</div>
				</div>
				<div key="0">
					<div v-if="isOpenHours">
						<TimeCarousel v-model="time.hoursIndSelected" :key="time.hKey" :arrValues="availableHours" />
						<TimeCarousel v-model="time.minutesIndSelected" :key="time.mKey" :arrValues="availableMinutes" />
						<p class="main-title">Pick up an order:</p>
						<p>{{ pickUpDate }}</p>
						<p style="margin-bottom: 20px">{{ activeAddress }}</p>
						<div v-if="isLogged">
							<LoaderLine style="margin-bottom: 20px" v-if="isRequesting" />
							<button class="main-btn btn-active" :class="{ 'btn-disable': isRequesting }" @click="handleOrder()">Place order</button>
							<p class="main-error-message" v-if="errMsg">{{ errMsg }}</p>
						</div>
						<div v-if="!isLogged" style="margin-top: 15px">
							<p style="text-align: center; font-weight: bold">You need to log in to place an order</p>
							<ArrowLink :link="accountLink.link" :linkText="accountLink.linkText" />
						</div>
					</div>
					<div v-else class="cart-available-msg">
						<p>The order can be placed</p>
						<p>from {{ openingHours.beginning }} to {{ endingHours - 1 }}:30</p>
					</div>
				</div>
			</transition-group>
		</div>
	</div>
</template>

<script>
import dateFormat from '@/mixins/dateFormat'
import { cartIcon, removeIcon, plusIcon } from '@/services/svgIcons'
import TimeCarousel from '@/components/UI/TimeCarousel.vue'
import CartObj from '@/components/UI/CartObj.vue'
import SvgIcon from '@/components/UI/SvgIcon.vue'
import ArrowLink from '@/components/UI/ArrowLink.vue'
import LoaderLine from '@/components/UI/LoaderLine.vue'
import { checkTokenExpTime } from '@/services/userService'
import { mapGetters, mapActions } from 'vuex'
import { links } from '@/_config'

export default {
	components: {
		TimeCarousel,
		CartObj,
		SvgIcon,
		ArrowLink,
		LoaderLine
	},
	data() {
		return {
			removeIcon,
			plusIcon,
			cartEmpty: {
				...cartIcon,
				msg: 'Your cart is empty'
			},
			accountLink: {
				link: links[2].url,
				linkText: 'Login'
			},
			time: {
				hKey: 'h1',
				hoursIndCounted: 0,
				hoursIndSelected: 0,
				mKey: 'm1',
				minutesIndCounted: 0,
				minutesIndSelected: 0
			},
			minOrderInterval: 20,
			updateTimeCarousel: 3
		}
	},
	methods: {
		minToMs(min) {
			return min * 60 * 1000
		},
		arrBetweenNums(start, end) {
			return Array.from({ length: end - start + 1 }, (v, k) => start + k)
		},
		hoursToIndex(hours) {
			return hours - this.beginningHours
		},
		indexToHours(index) {
			return index + this.beginningHours
		},
		setTime() {
			const now = new Date()
			const availableDate = new Date(now.getTime() + this.minToMs(this.minOrderInterval))
			this.time.hoursIndSelected = this.hoursToIndex(availableDate.getHours())
			this.time.hoursIndCounted = this.hoursToIndex(availableDate.getHours())
			this.time.minutesIndSelected = availableDate.getMinutes()
			this.time.minutesIndCounted = availableDate.getMinutes()
		},
		changeKey(key) {
			const prefix = key.slice(0, 1)
			let ind = Number(key.slice(1))
			return prefix + ++ind
		},
		handleOrder() {
			if (!this.isRequesting) {
				const now = new Date()
				const createdAt = new Date(now)
				const pickUpAt = new Date(now)
				pickUpAt.setHours(this.indexToHours(this.time.hoursIndSelected))
				pickUpAt.setMinutes(this.time.minutesIndSelected)
				const order = {
					coffeeHouse: this.activeAddressId,
					totalCost: this.totalCost,
					createdAt: this.toLocalDateTime(createdAt),
					pickUpAt: this.toLocalDateTime(pickUpAt)
				}
				let orderDetails = []
				this.cart.forEach(obj => {
					let orderDetail = {
						product: { id: obj.product.id },
						quantity: obj.quantity,
						cost: obj.cost,
						additives: []
					}
					obj.additives.forEach(add => orderDetail.additives.push({ id: add.id }))
					orderDetails.push(orderDetail)
				})
				this.placeOrder({ order, orderDetails })
			}
		},
		...mapActions('cart', ['resetCart', 'resetUnavailableCart', 'placeOrder']),
		...mapActions('request', ['setErrMsg'])
	},
	computed: {
		totalCost() {
			let totalCost = 0
			this.cart.forEach(el => {
				totalCost += el.cost
			})
			return totalCost
		},
		availableHours() {
			return this.arrBetweenNums(this.beginningHours, this.endingHours - 1)
		},
		availableMinutes() {
			return this.arrBetweenNums(0, 59)
		},
		isOpenHours() {
			const cond1 = new Date().getHours() >= this.beginningHours && new Date().getHours() < this.endingHours
			const cond2 = new Date().getHours() === this.endingHours - 1 ? new Date().getMinutes() < 30 : true
			// return cond1 && cond2
			return true
		},
		...mapGetters('cart', {
			cart: 'getCart',
			unavailableCart: 'getUnavailableCart'
		}),
		...mapGetters('addresses', {
			activeAddress: 'getActiveAddress',
			activeAddressId: 'getActiveAddressId',
			openingHours: 'getOpeningHours',
			beginningHours: 'getBeginningHours',
			endingHours: 'getEndingHours'
		}),
		...mapGetters('user', { isLogged: 'isLogged' }),
		...mapGetters('request', { isRequesting: 'isRequesting', errMsg: 'getErrMsg' })
	},
	mixins: [dateFormat],
	watch: {
		'time.hoursIndSelected'(newValue) {
			if (newValue < this.time.hoursIndCounted) {
				this.setTime()
				this.time.hKey = this.changeKey(this.time.hKey)
				this.time.mKey = this.changeKey(this.time.mKey)
			}
		},
		'time.minutesIndSelected'(newValue) {
			if (this.time.hoursIndSelected === this.time.hoursIndCounted && newValue < this.time.minutesIndCounted) {
				this.time.minutesIndSelected = this.time.minutesIndCounted
				this.time.mKey = this.changeKey(this.time.mKey)
			}
		}
	},
	mounted() {
		checkTokenExpTime()
		if (this.isOpenHours) {
			this.setTime()
			setInterval(() => this.setTime(), this.minToMs(3))
		}
		if (this.errMsg) {
			this.setErrMsg('')
		}
	}
}
</script>
