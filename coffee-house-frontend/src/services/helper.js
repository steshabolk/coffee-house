// ARRAY
export const objectsEqual = (o1, o2) =>
	typeof o1 === 'object' && Object.keys(o1).length > 0
		? Object.keys(o1).length === Object.keys(o2).length && Object.keys(o1).every(p => objectsEqual(o1[p], o2[p]))
		: o1 === o2

export const arraysEqual = (a1, a2) => a1.length === a2.length && a1.every((o, idx) => objectsEqual(o, a2[idx]))

export const firstArrDiffFromSecond = (a1, a2) => a1.filter(el1 => !a2.find(el2 => Object.keys(el2).every(p => el1[p] === el2[p])))

// OBJECT
export const deepClone = obj => {
	if (obj === null) return null
	let clone = Object.assign({}, obj)
	Object.keys(clone).forEach(key => (clone[key] = typeof obj[key] === 'object' ? deepClone(obj[key]) : obj[key]))
	if (Array.isArray(obj)) {
		clone.length = obj.length
		return Array.from(clone)
	}
	return clone
}

// DATE TIME FORMAT
export function addZero(num) {
	return num < 10 ? '0' + num : num
}

export function hh_mm__dd_mm_yyyy(date) {
	const parsed = new Date(Date.parse(date))
	const y = parsed.getFullYear()
	const m = addZero(parsed.getMonth() + 1)
	const d = addZero(parsed.getDate())
	const h = addZero(parsed.getHours())
	const min = addZero(parsed.getMinutes())
	return `${h}:${min} ${d}.${m}.${y}`
}

export function hh_mm(date) {
	const parsed = new Date(Date.parse(date))
	const h = addZero(parsed.getHours())
	const min = addZero(parsed.getMinutes())
	return `${h}:${min}`
}

export function toLocalDateTime(date) {
	const parsed = new Date(Date.parse(date))
	const y = parsed.getFullYear()
	const m = addZero(parsed.getMonth() + 1)
	const d = addZero(parsed.getDate())
	const h = addZero(parsed.getHours())
	const min = addZero(parsed.getMinutes())
	const s = addZero(parsed.getSeconds())
	return `${y}-${m}-${d} ${h}:${min}:${s}`
}

export function minToMs(min) {
	return min * 60 * 1000
}
