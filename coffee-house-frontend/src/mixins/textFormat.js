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
		},
		toTitleCase(str) {
			return str.replace(/\w\S*/g, function (txt) {
				return txt.charAt(0).toUpperCase() + txt.substr(1).toLowerCase()
			})
		}
	}
}
