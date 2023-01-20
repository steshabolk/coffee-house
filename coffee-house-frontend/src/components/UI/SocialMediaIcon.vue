<template>
	<a :href="link" class="social-media-icon" :class="socialMedia">
		<SvgIcon :viewBox="viewBox" :path="svgPath" />
	</a>
</template>

<script>
import SvgIcon from '@/components/UI/SvgIcon.vue'

export default {
	props: {
		link: {
			type: String,
			required: true
		},
		socialMedia: {
			type: String,
			required: true
		},
		viewBox: {
			type: String,
			required: true
		},
		svgPath: {
			type: String,
			required: true
		}
	},
	components: {
		SvgIcon
	}
}
</script>
