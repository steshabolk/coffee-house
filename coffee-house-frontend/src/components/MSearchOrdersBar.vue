<template>
	<div>
		<p style="text-align: center">order search bar</p>
		<p style="font-size: 0.8rem; opacity: 0.8; text-align: center">*get order history by parameters</p>
		<form @submit.prevent="handleForm" autocomplete="off" class="search-bar-wrapper">
			<div class="grid-tb" :style="gridColumnsNumVar(tableColumns.length)">
				<div class="grid-tb-cell grid-tb-title" v-for="(header, index) of tableColumns" :key="index">{{ header }}</div>
				<div class="grid-tb-row">
					<div class="grid-tb-cell main-input-wrapper">
						<div class="main-input-text" :class="{ 'main-input-text__active': isActive === orderIdSearchField.id }">
							<input
								v-model="searchParams.id"
								@focus="isActive = orderIdSearchField.id"
								@blur="isActive = null"
								:disabled="!isOrderIdSearch"
								:type="orderIdSearchField.type"
								:placeholder="orderIdSearchField.placeholder"
								:id="orderIdSearchField.id"
								:name="orderIdSearchField.name" />
						</div>
						<div v-if="isActive === null && v$.searchParams.id.$invalid" class="form-error-block">
							<p class="form-error-msg" v-for="(error, index) of v$.searchParams.id.$errors" :key="index">
								{{ error.$message }}
							</p>
						</div>
					</div>
					<div class="grid-tb-cell main-input-wrapper" v-for="(timeSearch_fields, time_index) of timeSearch" :key="time_index">
						<div
							class="main-input-text"
							:class="{ 'main-input-text__active': isActive === `${time_index}_${field_index}` }"
							v-for="(field, field_index) of timeSearch_fields"
							:key="field_index">
							<input
								v-model="searchParams[time_index][field_index]"
								@focus="isActive = `${time_index}_${field_index}`"
								@blur="isActive = null"
								:disabled="!isOrderParamsSearch"
								:type="field.type"
								:placeholder="field.placeholder"
								:id="field.id"
								:name="field.name" />
						</div>
						<div v-if="isActive === null && v$.searchParams[time_index].$invalid" class="form-error-block">
							<p class="form-error-msg" v-for="(error, index) of v$.searchParams[time_index].$errors" :key="index">
								{{ error.$message }}
							</p>
						</div>
					</div>
					<div class="grid-tb-cell">
						<div class="main-input-check" style="width: 100%" v-for="(status, index) of orderStatus" :key="index">
							<input
								v-model="searchParams.status"
								:disabled="!isOrderParamsSearch"
								type="checkbox"
								:value="status"
								:id="`order-status-${index}`"
								name="order-status" />
							<span class="input-checked__square"
								><label :for="`order-status-${index}`" style="text-transform: lowercase">{{ status }}</label></span
							>
						</div>
					</div>
				</div>
			</div>
			<div class="btn-block-wrapper btn-block-wrapper-col" style="margin-left: 0.5rem">
				<button type="submit" style="margin-bottom: 1rem" class="main-btn btn-disable" :class="{ 'btn-active': isSearchingParamsValid }">
					<SvgIcon :viewBox="searchIcon.viewBox" :path="searchIcon.svgPath" />
				</button>
				<button class="main-btn btn-active" @click="resetSearchBar()">
					<SvgIcon class="close-btn" :viewBox="plusIcon.viewBox" :path="plusIcon.svgPath" />
				</button>
			</div>
		</form>
		<transition-group name="fade-X" mode="out-in">
			<custom-scrollbar
				v-if="isSearchOrdersLoaded && searchOrders.length > 0"
				:autoHide="false"
				:style="{ maxHeight: '50vh' }"
				style="margin-top: 1rem"
				ref="scroll">
				<MOrdersTable :orders="searchOrders" />
			</custom-scrollbar>
			<p v-if="isSearchOrdersLoaded && searchOrders.length === 0" class="main-error-message" style="margin-top: 0.5rem">
				Orders with these parameters were not found
			</p>
		</transition-group>
	</div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex'
import { setup, orderNumValidator, dateTimeValidator, isAnyFormFieldValid } from '@/validators/validators'
import { orderIdSearchField, createdAtSearchField, pickUpAtSearchField, closedAtSearchField } from '@/services/inputFields.js'
import { searchIcon, plusIcon } from '@/services/svgIcons.js'
import { orderStatus } from '@/services/orderStatus.js'
import { ordersSearchParams } from '@/services/requestBody.js'
import { deepClone } from '@/services/helper.js'
import CustomScrollbar from 'custom-vue-scrollbar'
import gridColumnsNumVar from '@/mixins/gridColumnsNumVar'
import textFormat from '@/mixins/textFormat'
import SvgIcon from '@/components/UI/SvgIcon.vue'
import GridTable from '@/components/UI/GridTable.vue'
import GridTableRow from '@/components/UI/GridTableRow.vue'
import MOrdersTable from '@/components/UI/MOrdersTable.vue'

export default {
	setup,
	components: {
		SvgIcon,
		GridTable,
		GridTableRow,
		CustomScrollbar,
		MOrdersTable
	},
	data() {
		return {
			orderStatus,
			orderIdSearchField,
			timeSearch: { createdAt: createdAtSearchField, pickUpAt: pickUpAtSearchField, closedAt: closedAtSearchField },
			searchIcon,
			plusIcon,
			tableColumns: ['№', 'created at', 'pick up at', 'closed at', 'status'],
			searchParams: null,
			isActive: null,
			isSearchingParamsChanged: false
		}
	},
	methods: {
		initSearchParams() {
			this.searchParams = ordersSearchParams()
		},
		handleForm() {
			if (!this.isRequesting && this.isSearchingParamsValid) {
				const changeDataFormat = ['createdAt', 'pickUpAt', 'closedAt']
				const key = ['from', 'to']
				let params = deepClone(this.searchParams)
				changeDataFormat.forEach(param => {
					key.forEach(key => {
						if (params[param][key]) params[param][key] = this.parseDateParams(params[param][key], key)
					})
				})
				this.requestSearchOrders(params)
				this.isSearchingParamsChanged = false
			}
		},
		parseDateParams(param, key) {
			const arr = param.split(' ')
			const date_arr = arr[0].split('.')
			const time_arr = key === 'from' ? ['00', '00', '00'] : ['23', '59', '59']
			if (arr.length === 2) {
				const time = arr[1].split(':')
				time_arr[0] = time[0]
				time_arr[1] = time[1]
			}
			return `${date_arr[2]}-${date_arr[1]}-${date_arr[0]} ${time_arr[0]}:${time_arr[1]}:${time_arr[2]}`
		},
		resetSearchBar() {
			this.initSearchParams()
			this.isActive = null
			this.isSearchingParamsChanged = false
			this.resetSearchOrders()
		},
		...mapActions('request', ['clearErrMsg']),
		...mapActions('manager', ['requestSearchOrders', 'resetSearchOrders'])
	},
	computed: {
		isAnyFormFieldValid,
		isOrderIdSearch() {
			const cond1 = this.searchParams.createdAt.from || this.searchParams.createdAt.to
			const cond2 = this.searchParams.pickUpAt.from || this.searchParams.pickUpAt.to
			const cond3 = this.searchParams.closedAt.from || this.searchParams.closedAt.to
			return !cond1 && !cond2 && !cond3 && this.searchParams.status.length === 0
		},
		isOrderParamsSearch() {
			return !this.searchParams.id
		},
		isSearchingParamsValid() {
			const cond1 = this.isOrderIdSearch && !this.isOrderParamsSearch
			const cond2 = !this.isOrderIdSearch && this.isOrderParamsSearch
			return (cond1 || cond2) && this.isSearchingParamsChanged && this.isAnyFormFieldValid
		},
		...mapGetters('request', { isRequesting: 'isRequesting' }),
		...mapGetters('manager', { isSearchOrdersLoaded: 'isSearchOrdersLoaded', searchOrders: 'getSearchOrders' })
	},
	watch: {
		searchParams: {
			handler() {
				if (!this.isSearchingParamsChanged) {
					this.isSearchingParamsChanged = true
				}
			},
			deep: true
		}
	},
	mixins: [gridColumnsNumVar, textFormat],
	beforeMount() {
		this.clearErrMsg()
		this.initSearchParams()
	},
	validations() {
		return {
			searchParams: {
				id: {
					...orderNumValidator
				},
				createdAt: {
					from: {
						...dateTimeValidator
					},
					to: {
						...dateTimeValidator
					}
				},
				pickUpAt: {
					from: {
						...dateTimeValidator
					},
					to: {
						...dateTimeValidator
					}
				},
				closedAt: {
					from: {
						...dateTimeValidator
					},
					to: {
						...dateTimeValidator
					}
				},
				status: {}
			}
		}
	}
}
</script>
