import { createStore } from 'vuex'

import request from '@/store/request'
import coffeeHouse from '@/store/coffeeHouse'
import menu from '@/store/menu'
import selectedCoffeeItems from '@/store/selectedCoffeeItems'
import cart from '@/store/cart'
import auth from '@/store/auth'
import user from '@/store/user'
import manager from '@/store/manager'

const store = {
	modules: {
		request,
		coffeeHouse,
		menu,
		selectedCoffeeItems,
		cart,
		auth,
		user,
		manager
	}
}

export default createStore(store)
