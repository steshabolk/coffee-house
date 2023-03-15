<template>
	<div class="toggle-wrapper">
		<input class="toggle" type="checkbox" :checked="checked" />
	</div>
</template>

<script>
export default {
	props: {
		checked: {
			type: Boolean,
			required: true
		}
	}
}
</script>
