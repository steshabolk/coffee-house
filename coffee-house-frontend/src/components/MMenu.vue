<template>
	<LoaderCard v-if="!isManagerMenuLoaded" style="height: 70vh" />
	<div v-else class="menu-mng-wrapper">
		<p style="font-size: 0.8rem; opacity: 0.8; text-align: center; margin-bottom: 1rem">*manage the availability of products using switches</p>
		<custom-scrollbar :autoHide="false" :style="{ height: '65vh' }" ref="scroll">
			<div v-for="(menuCategory, key) of managerMenu" :key="key">
				<p style="margin-top: 1rem" class="menu-category-title title_lines">{{ camel2title(key) }}</p>
				<div v-for="(product, index) of menuCategory" :key="index">
					<div class="menu-mng-obj" :class="{ unavailable: !availabilityById(product.id) }">
						<p style="width: 62%">{{ product.productName }}</p>
						<p style="width: 14%; text-align: right">{{ product.sizeValue }}</p>
						<p style="width: 12%; text-align: right">{{ product.price }}&#8381;</p>
						<Toggle style="width: 12%; justify-content: right" :checked="availabilityById(product.id)" @click="toggleAvailability(product.id)" />
					</div>
				</div>
			</div>
		</custom-scrollbar>
		<ErrorCloseable />
		<div class="btn-block-wrapper" style="width: 30%; margin-top: 0.8rem">
			<button class="main-btn btn-disable" :class="{ 'btn-active': !isRequesting && isMenuAvailabilityChanged }" @click="resetAvailability()">
				cancel
			</button>
			<button class="main-btn btn-disable" :class="{ 'btn-active': !isRequesting && isMenuAvailabilityChanged }" @click="saveChanges()">save</button>
		</div>
	</div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex'
import { updateAvailabilityBody } from '@/services/requestBody'
import { plusIcon, confirmIcon } from '@/services/svgIcons'
import { firstArrDiffFromSecond } from '@/services/helper'
import CustomScrollbar from 'custom-vue-scrollbar'
import camel2title from '@/mixins/textFormat'
import LoaderCard from '@/components/UI/LoaderCard.vue'
import SvgIcon from '@/components/UI/SvgIcon.vue'
import Toggle from '@/components/UI/Toggle.vue'
import ErrorCloseable from '@/components/UI/ErrorCloseable.vue'

export default {
	components: {
		SvgIcon,
		LoaderCard,
		Toggle,
		CustomScrollbar,
		ErrorCloseable
	},
	data() {
		return {
			plusIcon,
			confirmIcon
		}
	},
	methods: {
		availabilityById(id) {
			return this.menuAvailability.find(el => el.id === id).availability
		},
		saveChanges() {
			if (this.isMenuAvailabilityChanged) {
				const diff = firstArrDiffFromSecond(this.menuAvailability, this.initialMenuAvailability)
				if (diff.length > 0) {
					this.updateAvailability(updateAvailabilityBody(diff))
				} else {
					this.resetAvailability()
				}
			}
		},
		...mapActions('menu', ['requestManagerMenu', 'toggleAvailability', 'resetAvailability', 'updateAvailability']),
		...mapActions('request', ['clearErrMsg'])
	},
	computed: {
		...mapGetters('menu', {
			isManagerMenuLoaded: 'isManagerMenuLoaded',
			managerMenu: 'getManagerMenu',
			initialMenuAvailability: 'getInitialMenuAvailability',
			menuAvailability: 'getChangedMenuAvailability',
			isMenuAvailabilityChanged: 'isMenuAvailabilityChanged'
		}),
		...mapGetters('request', { isRequesting: 'isRequesting' })
	},
	mixins: [camel2title],
	beforeMount() {
		this.clearErrMsg()
		this.requestManagerMenu()
	}
}
</script>
