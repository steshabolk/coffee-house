<template>
	<LoaderCard v-if="!isActiveOrdersLoaded" style="height: 70vh" />
	<div v-else>
		<p style="text-align: center">active orders</p>
		<p style="font-size: 0.8rem; opacity: 0.8; text-align: center">*the list of active orders is updated once a minute</p>
		<MOrdersTable :orders="activeOrders" />
	</div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex'
import { minToMs } from '@/services/helper'
import LoaderCard from '@/components/UI/LoaderCard.vue'
import MOrdersTable from '@/components/UI/MOrdersTable.vue'

export default {
	components: {
		LoaderCard,
		MOrdersTable
	},
	data() {
		return {
			pollingActiveOrders: null
		}
	},
	methods: {
		minToMs,
		pollActiveOrders() {
			if (this.isOpenHours) {
				this.pollingActiveOrders = setInterval(() => this.updateActiveOrders(), this.minToMs(1))
			}
		},
		...mapActions('manager', ['requestActiveOrders', 'updateActiveOrders'])
	},
	computed: {
		isOpenHours() {
			return new Date().getHours() >= this.beginningHours && new Date().getHours() < this.endingHours
			// return true
		},
		...mapGetters('manager', {
			isActiveOrdersLoaded: 'isActiveOrdersLoaded',
			activeOrders: 'getActiveOrders'
		}),
		...mapGetters('coffeeHouse', { beginningHours: 'getBeginningHours', endingHours: 'getEndingHours' })
	},
	beforeMount() {
		this.requestActiveOrders()
		if (this.isOpenHours) {
			this.pollActiveOrders()
		}
	},
	unmounted() {
		clearInterval(this.pollingActiveOrders)
	}
}
</script>
