export default {
	methods: {
		closedAt(date) {
			const parsed = new Date(Date.parse(date))
			const y = parsed.getFullYear()
			const m = this.addZero(parsed.getMonth() + 1)
			const d = this.addZero(parsed.getDate())
			const h = this.addZero(parsed.getHours())
			const min = this.addZero(parsed.getMinutes())
			return `${h}:${min}⠀${d}.${m}.${y}`
		},
		pickUpAt(date) {
			const parsed = new Date(Date.parse(date))
			const h = this.addZero(parsed.getHours())
			const min = this.addZero(parsed.getMinutes())
			return `${h}:${min}`
		},
		toLocalDateTime(date) {
			const parsed = new Date(Date.parse(date))
			const y = parsed.getFullYear()
			const m = this.addZero(parsed.getMonth() + 1)
			const d = this.addZero(parsed.getDate())
			const h = this.addZero(parsed.getHours())
			const min = this.addZero(parsed.getMinutes())
			const s = this.addZero(parsed.getSeconds())
			return `${y}-${m}-${d} ${h}:${min}:${s}`
		},
		addZero(num) {
			return num < 10 ? '0' + num : num
		}
	},
	computed: {
		pickUpDate() {
			const now = new Date()
			const monthsList = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December']
			const hours = this.addZero(this.indexToHours(this.time.hoursIndSelected))
			const minutes = this.addZero(this.time.minutesIndSelected)
			return `${now.getDate()} ${monthsList[now.getMonth()]} ${hours}:${minutes}`
		}
	}
}
