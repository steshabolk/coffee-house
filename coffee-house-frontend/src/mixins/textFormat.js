export default {
	methods: {
		additivesNames(additives) {
			let additivesNames = []
			if (additives.length > 0) {
				additives.forEach(obj => {
					additivesNames.push(obj.productName)
				})
			}
			return additivesNames.join(', ')
		},
		camel2title(camelCase) {
			return camelCase
				.replace(/([A-Z])/g, match => ` ${match}`)
				.replace(/^./, match => match.toUpperCase())
				.trim()
		}
	}
}
