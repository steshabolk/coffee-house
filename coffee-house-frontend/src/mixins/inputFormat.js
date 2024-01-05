import { mapActions } from 'vuex'

export default {
	methods: {
		inputRadio(product, category) {
			if (product === this.selectedCoffee || product === this.selectedMilk) {
				this.removeSelectedRadio(category)
			} else {
				this.addSelectedRadio({ product, category })
			}
		},
		inputCheckbox(product, category) {
			this.selectCheckbox({ product, category })
		},
		...mapActions('selectedCoffeeItems', ['addSelectedRadio', 'removeSelectedRadio', 'selectCheckbox'])
	}
}
