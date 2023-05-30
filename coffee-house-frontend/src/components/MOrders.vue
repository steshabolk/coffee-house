<template>
	<LoaderCard v-if="!isActiveOrdersLoaded" style="height: 70vh" />
	<div v-else>
		<MSearchOrdersBar />
		<p class="main-title main-title__center" style="margin-top: 2rem">Active Orders</p>
		<MOrdersTable :orders="activeOrders" />
	</div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex'
import { minToMs } from '@/services/helper'
import LoaderCard from '@/components/UI/LoaderCard.vue'
import MSearchOrdersBar from '@/components/MSearchOrdersBar.vue'
import MOrdersTable from '@/components/UI/MOrdersTable.vue'

export default {
	components: {
		LoaderCard,
		MSearchOrdersBar,
		MOrdersTable
	},
	data() {
		return {
			tableColumns: ['№', 'total cost', 'created at', 'pick up at', 'closed at', 'status', 'details', 'actions'],
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
		...mapGetters('addresses', { beginningHours: 'getBeginningHours', endingHours: 'getEndingHours' })
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
