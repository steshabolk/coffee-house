import { helpers, required, minLength, maxLength } from '@vuelidate/validators'
import useVuelidate from '@vuelidate/core'

export function setup() {
	return {
		v$: useVuelidate({
			$lazy: true,
			$autoDirty: true
		})
	}
}

export function isFormValid() {
	return this.v$.$dirty && !this.v$.$invalid
}

export const nameValidator = {
	required: helpers.withMessage('Enter your name', required),
	minLength: helpers.withMessage('Name should be at least 2 characters long', minLength(2)),
	maxLength: helpers.withMessage('Name should be a maximum of 64 characters', maxLength(64)),
	validPattern: helpers.withMessage(
		() => 'Name must contain only letters, digits and «_» symbol',
		value => /^(?=.*[\p{L}\d])[\p{L}\d_]*$/gu.test(value)
	)
}

export const phoneValidator = {
	required: helpers.withMessage('Enter your phone number', required),
	validPattern: helpers.withMessage(
		() => 'Phone must start with 7 or 8 and contain 11 digits',
		value => /^(\+?)(7|8)(\d{10})$/.test(value)
	)
}

export const passwordValidator = {
	required: helpers.withMessage('Enter your password', required),
	minLength: helpers.withMessage('Password should be at least 6 characters long', minLength(6)),
	containUppercase: helpers.withMessage(
		() => 'Password must contain any uppercase character (A-Z)',
		value => /[A-Z]/.test(value)
	),
	containLowercase: helpers.withMessage(
		() => 'Password must contain any lowercase character (a-z)',
		value => /[a-z]/.test(value)
	),
	containDigit: helpers.withMessage(
		() => 'Password must contain any digit character (0-9)',
		value => /[0-9]/.test(value)
	)
}

export const passwordConfirmationValidator = {
	required: helpers.withMessage('Confirm the entered password', required),
	sameAsPassword: helpers.withMessage(
		() => 'Password are not matching',
		value => value === this.user.password
	)
}
