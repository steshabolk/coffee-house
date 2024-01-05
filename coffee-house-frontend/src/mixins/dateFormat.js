import { addZero } from '@/services/helper'

export default {
	computed: {
		pickUpDate() {
			const now = new Date()
			const monthsList = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December']
			const hours = addZero(this.indexToHours(this.time.hoursIndSelected))
			const minutes = addZero(this.time.minutesIndSelected)
			return `${now.getDate()} ${monthsList[now.getMonth()]} ${hours}:${minutes}`
		}
	}
}
