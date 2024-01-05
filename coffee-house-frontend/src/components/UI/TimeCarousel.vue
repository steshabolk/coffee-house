<template>
	<Carousel class="carousel-wrapper" :itemsToShow="5" :snapAlign="'center'" :wrapAround="true">
		<Slide v-for="num in arrValues" :key="num">
			<p class="carousel__item">{{ num < 10 ? '0' + num : num }}</p>
		</Slide>
		<template #addons>
			<Navigation />
		</template>
	</Carousel>
</template>

<script>
import { Carousel, Navigation, Slide } from 'vue3-carousel'

export default {
	props: {
		arrValues: {
			type: Array,
			required: true
		}
	},
	components: {
		Carousel,
		Slide,
		Navigation
	}
}
</script>
