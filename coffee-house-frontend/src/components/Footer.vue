<template>
	<footer>
		<div class="footer-wrapper">
			<div class="container">
				<div class="social-media-wrapper">
					<div v-for="icon in socialMediaContent" :key="icon.socialMedia">
						<SocialMediaIcon :link="icon.link" :socialMedia="icon.socialMedia" :viewBox="icon.viewBox" :svgPath="icon.svgPath"> </SocialMediaIcon>
					</div>
				</div>
			</div>
		</div>
	</footer>
</template>

<script>
import { vkIcon, instagramIcon, telegramIcon } from '@/services/svgIcons'
import SocialMediaIcon from '@/components/UI/SocialMediaIcon.vue'

export default {
	components: {
		SocialMediaIcon
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
