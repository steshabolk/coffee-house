<template>
	<div class="home-card" :style="bgImage">
		<div class="home-card-content">
			<h3 class="home-card-title">{{ title }}</h3>
			<p class="home-cart-line" />
			<p>{{ subtitle }}</p>
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
		subtitle: {
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
