<template>
	<header>
		<div class="navbar">
			<div class="navbar-content">
				<div class="navbar-btn">
					<!-- <div class="navbar-btn" :class="{ onScroll: !view.topOfPage }"> -->
					<input class="navbar-input" type="checkbox" />
					<span></span>
					<span></span>
					<div class="navbar-menu">
						<ul class="navbar-list">
							<li class="navbar-item" v-for="link in links" :key="link.alias">
								<router-link class="navbar-link" :to="link.url">{{ link.title }}</router-link>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</header>
</template>

<script>
import { links } from '@/_config'

export default {
	data() {
		return {
			links: links
			// view: {
			// 	topOfPage: true
			// }
		}
	}
	// beforeMount() {
	// 	window.addEventListener('scroll', this.handleScroll)
	// },
	// methods: {
	// 	handleScroll() {
	// 		if (window.pageYOffset > 0) {
	// 			if (this.view.topOfPage) this.view.topOfPage = false
	// 		} else {
	// 			if (!this.view.topOfPage) this.view.topOfPage = true
	// 		}
	// 	}
	// }
}
</script>
