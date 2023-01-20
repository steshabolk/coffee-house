import VueJwtDecode from 'vue-jwt-decode'
import store from '@/store'

export function authHeader() {
	if (localStorage.getItem('token')) {
		return { Authorization: 'Bearer ' + localStorage.getItem('token') }
	}
}

export function decodeToken(token) {
	const decoded = VueJwtDecode.decode(token)
	return { phone: decoded.sub, name: decoded.name, role: decoded.role.replace('ROLE_', '').toLowerCase() }
}

export function userFromAuthHeader(authHeader) {
	const token = authHeader.replace('Bearer ', '')
	localStorage.setItem('token', token)
	const user = decodeToken(token)
	store.dispatch('user/setUser', user)
}

export function checkTokenExpTime() {
	if (localStorage.getItem('token')) {
		const token = localStorage.getItem('token')
		const exp = VueJwtDecode.decode(token).exp
		if (exp < Math.floor(Date.now() / 1000)) {
			store.dispatch('auth/logout')
		} else {
			const user = decodeToken(token)
			store.dispatch('user/setUser', user)
		}
	}
}
