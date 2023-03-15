<template>
	<LoaderCard v-if="!isManagerMenuLoaded" />
	<div v-else>
		<custom-scrollbar :autoHide="false" :style="{ height: '70vh' }" ref="scroll">
			<div v-for="(menuCategory, key) of managerMenu" :key="key">
				<p style="margin-top: 1rem" class="menu-category-title">{{ camel2title(key) }}</p>
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
		<LoaderLine style="margin-bottom: 1rem" v-if="isRequesting" />
		<div class="btn-block-wrapper" style="width: 20%; margin-top: 0.8rem">
			<button class="main-btn btn-disable" :class="{ 'btn-active': !isRequesting && isMenuAvailabilityChanged }" @click="resetAvailability()">
				<SvgIcon class="close-btn" style="width: 1.8rem; height: 1.8rem" :viewBox="plusIcon.viewBox" :path="plusIcon.svgPath" />
			</button>
			<button class="main-btn btn-disable" :class="{ 'btn-active': !isRequesting && isMenuAvailabilityChanged }" @click="saveChanges()">
				<SvgIcon style="width: 1.8rem; height: 1.8rem" :viewBox="checkIcon.viewBox" :path="checkIcon.svgPath" />
			</button>
		</div>
		<p class="main-error-message" v-if="errMsg">{{ errMsg }}</p>
	</div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex'
import { updateAvailabilityBody } from '@/services/requestBody'
import { plusIcon, checkIcon } from '@/services/svgIcons'
import { firstArrDiffFromSecond } from '@/services/helper'
import CustomScrollbar from 'custom-vue-scrollbar'
import camel2title from '@/mixins/textFormat'
import LoaderCard from '@/components/UI/LoaderCard.vue'
import LoaderLine from '@/components/UI/LoaderLine.vue'
import SvgIcon from '@/components/UI/SvgIcon.vue'
import Toggle from '@/components/UI/Toggle.vue'

export default {
	components: {
		SvgIcon,
		LoaderCard,
		LoaderLine,
		Toggle,
		CustomScrollbar
	},
	data() {
		return {
			plusIcon,
			checkIcon
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
		...mapGetters('request', { isRequesting: 'isRequesting', errMsg: 'getErrMsg' })
	},
	mixins: [camel2title],
	beforeMount() {
		this.clearErrMsg()
		this.requestManagerMenu()
	}
}
</script>
