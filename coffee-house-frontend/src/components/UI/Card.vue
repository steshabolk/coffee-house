<template>
	<div class="home-card" :style="bgImage">
		<div class="home-card-content">
			<p class="home-card-title">{{ title }}</p>
			<p class="home-cart-line" />
		</div>
		<p class="home-card-description">{{ description }}</p>
	</div>
</template>

<script>
import imgPath from '@/mixins/imgPath'

export default {
	props: {
		title: {
			type: String,
			required: true
		},
		description: {
			type: String,
			required: true
		},
		imgName: {
			type: String,
			required: true
		}
	},
	mixins: [imgPath],
	computed: {
		bgImage() {
			return {
				'--imgHomeCard': `url(${this.imgPath(this.imgName)})`
			}
		}
	}
}
</script>
