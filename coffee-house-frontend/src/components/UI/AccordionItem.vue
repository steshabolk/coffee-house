<template>
	<li class="accordion__item">
		<div class="accordion__trigger" :class="{ accordion__trigger_active: visible }">
			<div @click="open">
				<slot name="accordion-trigger"></slot>
			</div>
			<slot name="accordion-not-trigger"></slot>
		</div>
		<transition name="accordion" @enter="start" @after-enter="end" @before-leave="start" @after-leave="end">
			<div class="accordion__content" v-show="visible">
				<ul>
					<slot name="accordion-content"></slot>
				</ul>
			</div>
		</transition>
		<p class="horizontal-line" />
	</li>
</template>

<script>
export default {
	props: {},
	inject: ['Accordion'],
	data() {
		return {
			index: null
		}
	},
	computed: {
		visible() {
			return this.index == this.Accordion.active
		}
	},
	methods: {
		open() {
			if (this.visible) {
				this.Accordion.active = null
			} else {
				this.Accordion.active = this.index
			}
		},
		start(el) {
			el.style.height = el.scrollHeight + 'px'
		},
		end(el) {
			el.style.height = ''
		}
	},
	created() {
		this.index = this.Accordion.count++
	}
}
</script>
