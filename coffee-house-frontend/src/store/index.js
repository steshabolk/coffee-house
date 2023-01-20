import { createStore } from 'vuex'

import request from '@/store/request'
import addresses from '@/store/addresses'
import products from '@/store/products'
import selectedCoffeeItems from '@/store/selectedCoffeeItems'
import cart from '@/store/cart'
import auth from '@/store/auth'
import user from '@/store/user'

const store = {
	modules: {
		request,
		addresses,
		products,
		selectedCoffeeItems,
		cart,
		auth,
		user
	}
}

export default createStore(store)
