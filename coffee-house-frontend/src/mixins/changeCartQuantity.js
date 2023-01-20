import { mapActions } from 'vuex'

export default {
	methods: {
		changeQuantity(num) {
			if ((this.cartObj.quantity < 10) | (num < 0)) {
				const newQuantity = this.cartObj.quantity + num
				newQuantity === 0 ? this.removeFromCart(this.cartObj) : this.addInCart({ cartObj: this.cartObj, num: num })
			}
		},
		...mapActions('cart', ['addInCart', 'removeFromCart'])
	}
}
