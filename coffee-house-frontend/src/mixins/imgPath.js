export default {
	methods: {
		imgPath(imgName) {
			return require('@/assets/img/' + imgName)
		},
		getBgImage(imgName) {
			return {
				'--bgImage': `url(${this.imgPath(imgName)})`
			}
		}
	}
}
