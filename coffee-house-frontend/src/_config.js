export const links = [
	{
		title: 'home',
		alias: 'home',
		url: '/'
	},
	{
		title: 'menu',
		alias: 'menu',
		url: '/menu'
	},
	{
		title: 'account',
		alias: 'account',
		url: '/account'
	},
	{
		title: 'contact',
		alias: 'contact',
		url: '/#contact'
	}
]

export const linksManager = [
	{
		title: 'home',
		alias: 'home',
		url: '/'
	},
	{
		title: 'management',
		alias: 'management',
		url: '/management'
	},
	{
		title: 'account',
		alias: 'account',
		url: '/account'
	}
]

export const api = {
	api: process.env.VUE_APP_API,
	coffeeHouseController: '/api/v1/coffeehouse',
	userController: '/api/v1/users',
	managerController: '/api/v1/manager'
}

export const requests = {
	register: api.api + '/register',
	login: api.api + '/login',
	//
	requestCoffeeHouses: api.api + api.coffeeHouseController,
	requestUserMenu: api.api + api.coffeeHouseController + '/menu',
	//
	requestOrders: api.api + api.userController + '/orders',
	changeName: api.api + api.userController + '/settings/name',
	changePassword: api.api + api.userController + '/settings/password',
	//
	requestManagerCH: api.api + api.managerController + '/coffeehouse',
	requestManagerMenu: api.api + api.managerController + '/menu',
	requestManagerOrders: api.api + api.managerController + '/orders'
}
