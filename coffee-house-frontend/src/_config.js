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

export const linksManager = [
	{
		title: 'Home',
		alias: 'home',
		url: '/'
	},
	{
		title: 'Management',
		alias: 'management',
		url: '/management'
	},
	{
		title: 'Account',
		alias: 'account',
		url: '/account'
	}
]

export const api = {
	api: 'http://localhost:8085',
	coffeeHouseController: '/api/v1/coffeehouse',
	userController: '/api/v1/users',
	managerController: '/api/v1/manager'
}

export const requests = {
	register: api.api + '/register',
	login: api.api + '/login',
	//
	requestAddresses: api.api + api.coffeeHouseController,
	requestUserMenu: api.api + api.coffeeHouseController + '/menu',
	//
	requestOrders: api.api + api.userController + '/orders',
	changeName: api.api + api.userController + '/settings/name',
	changePassword: api.api + api.userController + '/settings/password',
	//
	requestManagerAddress: api.api + api.managerController + '/address',
	requestManagerMenu: api.api + api.managerController + '/menu',
	requestManagerOrders: api.api + api.managerController + '/orders'
}
