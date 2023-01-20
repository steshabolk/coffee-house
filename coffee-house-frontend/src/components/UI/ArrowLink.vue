<template>
	<div class="menu-link-wrapper">
		<div class="arrow-link">
			<router-link :to="link">
				<span class="arrow-link-text">{{ linkText }}</span>
			</router-link>
		</div>
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
