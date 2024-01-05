import { createRouter, createWebHistory, createWebHashHistory } from 'vue-router'
import store from '@/store'
import { decodeToken } from '@/services/userService'
import VueJwtDecode from 'vue-jwt-decode'

const routerHistory = createWebHashHistory()

import HomePage from '@/pages/home'
import MenuPage from '@/pages/menu'
import AccountPage from '@/pages/account'
import ManagementPage from '@/pages/management'

const router = createRouter({
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
			path: '/management',
			name: 'management',
			beforeEnter: (to, from, next) => {
				if (store.getters['manager/isLogged']) {
					next()
				} else {
					next('/')
				}
			},
			component: ManagementPage
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

router.beforeEach((to, from, next) => {
	if (localStorage.getItem('token')) {
		const token = localStorage.getItem('token')
		const exp = VueJwtDecode.decode(token).exp
		if (exp < Math.floor(Date.now() / 1000)) {
			store.dispatch('auth/logout')
		} else {
			const user = decodeToken(token)
			if (user.role === 'user') {
				if (!store.getters['user/isLogged']) {
					store.dispatch('user/setUser', user)
				}
				if (!store.getters['user/isOrdersLoaded']) {
					store.dispatch('user/requestOrders')
				}
			}
			if (user.role === 'manager') {
				if (!store.getters['manager/isLogged']) {
					store.dispatch('manager/setManager', user)
				}
				if (!store.getters['manager/isCHLoaded']) {
					if (localStorage.getItem('coffeeHouse')) {
						store.dispatch('manager/setCoffeeHouse', localStorage.getItem('coffeeHouse'))
					} else {
						store.dispatch('manager/requestCoffeeHouse')
					}
				}
			}
		}
		next()
	} else {
		next()
	}
})

export default router
