<template>
	<div class="settings-block">
		<SvgIcon
			@click="setActiveSetting(null)"
			class="return-btn"
			:class="{ 'return-btn_active': activeSettingsInd !== null }"
			:viewBox="arrowIcon.viewBox"
			:path="arrowIcon.svgPath" />
		<div class="settings-wrapper">
			<transition-group name="fade-Y" mode="out-in">
				<div v-if="activeSettingsInd === null">
					<div class="setting-item" v-for="(setting, index) of settings" :key="index">
						<p @click="setActiveSetting(index)" style="cursor: pointer">{{ setting.stngTitle }}</p>
						<p class="horizontal-line" />
					</div>
				</div>
				<FormInput
					v-if="
						activeSettingsInd !== null && (settings[activeSettingsInd].key === 'changeName' || settings[activeSettingsInd].key === 'changePassword')
					"
					:form="{
						key: settings[activeSettingsInd].key,
						btnText: settings[activeSettingsInd].btnText
					}"
					:fields="settings[activeSettingsInd].fields" />
			</transition-group>
		</div>
	</div>
</template>

<script>
import { nameField, passwordField, passwordConfirmationField } from '@/services/inputFields'
import { arrowIcon } from '@/services/svgIcons'
import FormInput from '@/components/FormInput.vue'
import Accordion from '@/components/UI/Accordion.vue'
import AccordionItem from '@/components/UI/AccordionItem.vue'
import SvgIcon from '@/components/UI/SvgIcon.vue'

export default {
	components: {
		FormInput,
		Accordion,
		AccordionItem,
		SvgIcon
	},
	data() {
		return {
			activeSettingsInd: null,
			arrowIcon,
			settings: [
				{
					key: 'changeName',
					stngTitle: 'change name',
					btnText: 'save',
					fields: [nameField]
				},
				{
					key: 'changePassword',
					stngTitle: 'change password',
					btnText: 'save',
					fields: [passwordField, passwordConfirmationField]
				}
			]
		}
	},
	methods: {
		setActiveSetting(setInd) {
			if (setInd != this.activeSettingsInd) {
				this.activeSettingsInd = setInd
			}
		}
	}
}
</script>
