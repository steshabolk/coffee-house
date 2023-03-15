<template>
	<footer class="footer-wrapper">
		<div class="social-media-wrapper" v-for="icon in socialMediaContent" :key="icon.socialMedia">
			<a class="social-media-icon" :href="icon.link" :class="icon.socialMedia">
				<SvgIcon :viewBox="icon.viewBox" :path="icon.svgPath" />
			</a>
		</div>
	</footer>
</template>

<script>
import { vkIcon, instagramIcon, telegramIcon } from '@/services/svgIcons'
import SvgIcon from '@/components/UI/SvgIcon.vue'

export default {
	components: {
		SvgIcon
	},
	data() {
		return {
			socialMediaContent: [
				{
					link: '',
					socialMedia: 'vk',
					...vkIcon
				},
				{
					link: '',
					socialMedia: 'instagram',
					...instagramIcon
				},
				{
					link: '',
					socialMedia: 'telegram',
					...telegramIcon
				}
			]
		}
	}
}
</script>
