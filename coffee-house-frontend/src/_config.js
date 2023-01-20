export const links = [
	{
		title: 'Home',
		alias: 'home',
		url: '/'
	},
	{
		title: 'Menu',
		alias: 'menu',
		url: '/menu'
	},
	{
		title: 'Account',
		alias: 'account',
		url: '/account'
	},
	{
		title: 'Contact',
		alias: 'contact',
		url: '/#contact'
	}
]

export const api = {
	api: 'http://localhost:8085/'
}

export const requests = {
	requestAddresses: api.api + 'api/v1/coffeehouse',
	requestProducts: api.api + 'api/v1/coffeehouse/products',
	register: api.api + 'register',
	login: api.api + 'login',
	requestOrders: api.api + 'api/v1/users/orders',
	changeName: api.api + 'api/v1/users/settings/name',
	changePassword: api.api + 'api/v1/users/settings/password'
}
