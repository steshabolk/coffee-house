import {
	createRouter,
	// createWebHistory,
	createWebHashHistory
} from 'vue-router'

const routerHistory = createWebHashHistory()

import HomePage from '@/pages/home'
import MenuPage from '@/pages/menu'
import AccountPage from '@/pages/account'

const routers = createRouter({
	history: routerHistory,
	routes: [
		{
			path: '/',
			name: 'home',
			component: HomePage
		},
		{
			path: '/menu',
			name: 'menu',
			component: MenuPage
		},
		{
			path: '/account',
			name: 'account',
			component: AccountPage
		},
		{
			path: '/:CatchAll(.*)',
			name: '404',
			redirect: '/'
		}
	],
	scrollBehavior(to) {
		if (to.hash) {
			return { el: to.hash, behavior: 'smooth' }
		} else {
			return { top: 0 }
		}
	}
})

export default routers
