import { mapActions } from 'vuex'
import { addInCartBody } from '@/services/requestBody'

export default {
	methods: {
		changeQuantity(num) {
			if ((this.cartObj.quantity < 10) | (num < 0)) {
				const newQuantity = this.cartObj.quantity + num
				newQuantity === 0 ? this.removeFromCart(this.cartObj) : this.addInCart(addInCartBody(this.cartObj, num))
			}
		},
		...mapActions('cart', ['addInCart', 'removeFromCart'])
	}
}
