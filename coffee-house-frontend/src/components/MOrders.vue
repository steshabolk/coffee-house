<template>
	<LoaderCard v-if="!isActiveOrdersLoaded" style="min-height: 84vh" />
	<div v-else>
		<MSearchOrdersBar />
		<p class="main-title main-title__center" style="margin-top: 2rem">Active Orders</p>
		<MOrdersTable :orders="activeOrders" />
	</div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex'
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
			tableColumns: ['№', 'total cost', 'created at', 'pick up at', 'closed at', 'status', 'details', 'actions']
		}
	},
	methods: {
		...mapActions('manager', ['requestActiveOrders'])
	},
	computed: {
		...mapGetters('manager', { isActiveOrdersLoaded: 'isActiveOrdersLoaded', activeOrders: 'getActiveOrders' })
	},
	beforeMount() {
		this.requestActiveOrders()
	}
}
</script>
