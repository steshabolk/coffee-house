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
