<template>
	<router-link :to="link" class="arrow-link-wrapper">
		<span>{{ linkText }}</span>
		<SvgIcon :viewBox="arrowIcon.viewBox" :path="arrowIcon.svgPath" />
	</router-link>
</template>

<script>
import SvgIcon from '@/components/UI/SvgIcon.vue'
import { arrowIcon } from '@/services/svgIcons'

export default {
	props: {
		link: {
			type: String,
			required: true
		},
		linkText: {
			type: String,
			required: true
		}
	},
	components: {
		SvgIcon
	},
	data() {
		return {
			arrowIcon
		}
	}
}
</script>
