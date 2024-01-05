<template>
	<header class="header-wrapper">
		<div class="navbar-wrapper">
			<div class="navbar-btn">
				<input type="checkbox" />
				<span></span>
				<span></span>
				<span></span>
				<ul class="navbar-menu">
					<li class="navbar-item" v-for="link in headerLinks" :key="link.alias">
						<router-link class="navbar-link" :to="link.url">{{ link.title }}</router-link>
						<p :key="index" class="navbar-num" v-if="link.alias === 'account' && activeOrdersNum > 0">{{ activeOrdersNum }}</p>
					</li>
				</ul>
			</div>
		</div>
	</header>
</template>

<script>
import { links, linksManager } from '@/_config'
import { mapGetters } from 'vuex'

export default {
	data() {
		return {
			links,
			linksManager,
			index: 1
		}
	},
	computed: {
		headerLinks() {
			if (this.isManagerLogged) {
				return this.linksManager
			} else {
				return this.links
			}
		},
		...mapGetters('user', { activeOrdersNum: 'getActiveOrdersNum' }),
		...mapGetters('manager', { isManagerLogged: 'isLogged' })
	},
	watch: {
		activeOrdersNum() {
			this.index++
		}
	}
}
</script>
