<template>
	<yandex-map
		:coords="center"
		:show-all-markers="true"
		:zoom="12"
		style="width: 100%; height: 100%"
		:settings="settings"
		:controls="[]"
		:scroll-zoom="true"
		:options="{ minZoom: '10', maxZoom: '18' }">
		<ymap-marker
			v-for="coord in coords"
			:key="coord.id"
			:marker-id="coord.id"
			:coords="[coord.x, coord.y]"
			:icon="markerIcon"
			:balloon-template="balloonTemplate(coord)" />
	</yandex-map>
</template>

<script>
import { yandexMap, ymapMarker } from 'vue-yandex-maps'

export default {
	components: {
		yandexMap,
		ymapMarker
	},
	data() {
		return {
			settings: {
				apiKey: '',
				lang: 'en_US',
				coordorder: 'latlong',
				enterprise: false,
				mode: 'debug',
				version: '2.1'
			},
			coords: [
				{
					id: 1,
					title: '"Berthold Centre"',
					text: 'st.Grazhdanskaya 15',
					x: 59.928382,
					y: 30.311979
				},
				{
					id: 2,
					title: '"Sevkabel Port"',
					text: 'Kozhevennaya liniya 40',
					x: 59.924708,
					y: 30.241603
				},
				{
					id: 3,
					title: '"Fligel"',
					text: 'st.Vosstaniya 24',
					x: 59.93832,
					y: 30.359903
				}
			],
			markerIcon: {
				color: 'black',
				glyph: 'dot'
			},
			center: [59.939096, 30.315871]
		}
	},
	methods: {
		balloonTemplate: coord => {
			return `
        <p class="ymap-balloon-title">${coord.title}</p>
        <p>${coord.text}</p>
      `
		}
	}
}
</script>
