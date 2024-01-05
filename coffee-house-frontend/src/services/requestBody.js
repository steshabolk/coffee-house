import { toLocalDateTime } from '@/services/helper'

// REQUEST BODY
export function loginUserBody(phone, password) {
	return { phone: phone, password: password }
}

export function registerUserBody(name, phone, password) {
	return { name: name, phone: phone, password: password }
}

export function changeNameBody(name) {
	return { name: name }
}

export function changePasswordBody(password) {
	return { password: password }
}

export function updateAvailabilityBody(arrDiff) {
	return { menu: arrDiff }
}

export function orderBody(activeAddressId, totalCost, createdAt, pickUpAt) {
	return { coffeeHouse: activeAddressId, totalCost: totalCost, createdAt: toLocalDateTime(createdAt), pickUpAt: toLocalDateTime(pickUpAt) }
}

export function orderDetailBody(productId, quantity, cost) {
	return { product: { id: productId }, quantity: quantity, cost: cost, additives: [] }
}

export function orderDetailAdditivesBody(productId) {
	return { id: productId }
}

// REQUEST PARAMS
function formRequestParams(initial, result = {}) {
	const loopNestedObj = (obj, key_seq) => {
		Object.entries(obj).forEach(([key, val]) => {
			const _key_seq = key_seq ? key_seq + key.charAt(0).toUpperCase() + key.slice(1) : key
			if (val && typeof val === 'object' && !Array.isArray(val)) {
				loopNestedObj(val, _key_seq)
			} else {
				if (Array.isArray(val)) {
					result[_key_seq] = val.length === 0 ? null : val.join(',')
				} else result[_key_seq] = val ? val : null
			}
		})
	}
	loopNestedObj(initial, '')
	return result
}

export function getActiveOrdersParams() {
	return { show: 'active' }
}

export function ordersSearchParams() {
	return {
		id: '',
		createdAt: {
			from: '',
			to: ''
		},
		pickUpAt: {
			from: '',
			to: ''
		},
		closedAt: {
			from: '',
			to: ''
		},
		status: []
	}
}

export function getOrdersBySearchParams(searchParams) {
	return formRequestParams(searchParams, { show: 'search' })
}

export function closeActiveOrderParams(id, status) {
	return { id: id, status: status }
}

// CART OBJ
export function formCartObjBody(product, quantity, cost, additives) {
	return { product: product, quantity: quantity, cost: cost, additives: additives }
}

export function addInCartBody(cartObj, num) {
	return { cartObj: cartObj, num: num }
}
