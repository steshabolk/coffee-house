import { userIcon, phoneIcon, passwordIcon } from '@/services/svgIcons'

export const nameField = {
	icon: userIcon,
	input: {
		id: 'name',
		placeholder: 'Name',
		type: 'text',
		name: 'name'
	}
}

export const phoneField = {
	icon: phoneIcon,
	input: {
		id: 'phone',
		placeholder: 'Phone',
		type: 'phone',
		name: 'phone'
	}
}

export const passwordField = {
	icon: passwordIcon,
	input: {
		id: 'password',
		placeholder: 'Password',
		type: 'password',
		name: 'password'
	}
}

export const passwordConfirmationField = {
	icon: passwordIcon,
	input: {
		id: 'password-confirm',
		placeholder: 'Confirm Password',
		type: 'password',
		name: 'password-confirm'
	}
}

export const orderIdSearchField = {
	id: 'order-id',
	placeholder: 'Order №',
	type: 'text',
	name: 'order-id'
}

export const createdAtSearchField = {
	from: {
		id: 'created-at-from',
		placeholder: 'From',
		type: 'text',
		name: 'created-at'
	},
	to: {
		id: 'created-at-to',
		placeholder: 'To',
		type: 'text',
		name: 'created-at'
	}
}

export const pickUpAtSearchField = {
	from: {
		id: 'pick-up-at-from',
		placeholder: 'From',
		type: 'text',
		name: 'pick-up-at'
	},
	to: {
		id: 'pick-up-at-to',
		placeholder: 'To',
		type: 'text',
		name: 'pick-up-at'
	}
}

export const closedAtSearchField = {
	from: {
		id: 'closed-at-from',
		placeholder: 'From',
		type: 'text',
		name: 'closed-at'
	},
	to: {
		id: 'closed-at-to',
		placeholder: 'To',
		type: 'text',
		name: 'closed-at'
	}
}
