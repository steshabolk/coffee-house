<template>
	<ul class="btn-list-wrapper">
		<li class="btn-list" v-for="obj in objectsArray" :key="obj.id" @click="toggleMethod(obj.id)" :class="{ 'btn-list-active': activeId === obj.id }">
			<div v-if="keyI === 'address'">
				<p style="text-transform: none">{{ splittedAddress(obj)[1] }}</p>
				<p class="main-text" style="font-style: italic; text-transform: none">{{ splittedAddress(obj)[0] }}</p>
			</div>
			<SvgIcon v-if="keyI === 'menuCategory'" :viewBox="obj.viewBox" :path="obj.svgPath" />
			<div v-if="keyI === 'management'">
				<p>{{ obj.btnText }}</p>
			</div>
		</li>
	</ul>
</template>

<script>
import SvgIcon from '@/components/UI/SvgIcon.vue'

export default {
	props: {
		keyI: {
			type: String,
			required: true
		},
		objectsArray: {
			type: Array,
			required: true
		},
		activeId: {
			type: Number,
			required: true
		},
		toggleMethod: {
			type: Function,
			required: true
		}
	},
	components: {
		SvgIcon
	},
	methods: {
		splittedAddress(address) {
			return address.address.split(',')
		}
	}
}
</script>
