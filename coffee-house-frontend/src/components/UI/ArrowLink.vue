<template>
	<div class="arrow-link-wrapper">
		<router-link :to="link">
			<span class="arrow-link-text">{{ linkText }}</span>
		</router-link>
	</div>
</template>

<script>
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
	}
}
</script>
