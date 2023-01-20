<template>
	<div class="settings-block">
		<SvgIcon @click="setActiveSetting(null)" class="svg-icon-stng" :viewBox="arrowIcon.viewBox" :path="arrowIcon.svgPath" />
		<div class="settings-wrapper" v-for="(setting, index) of settings" :key="index">
			<transition-group name="fade" mode="out-in">
				<div v-if="activeSettingsInd === null">
					<p @click="setActiveSetting(index)" style="cursor: pointer">{{ setting.stngTitle }}</p>
					<p class="horizontal-line" />
				</div>
			</transition-group>
		</div>
		<transition-group name="fade" mode="out-in">
			<FormObj
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
</template>

<script>
import FormObj from '@/components/FormObj.vue'
import Accordion from '@/components/UI/Accordion.vue'
import AccordionItem from '@/components/UI/AccordionItem.vue'
import { nameField, passwordField, passwordConfirmationField } from '@/services/inputFields'
import SvgIcon from '@/components/UI/SvgIcon.vue'
import { arrowIcon } from '@/services/svgIcons'

export default {
	components: {
		FormObj,
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
					stngTitle: 'Change name',
					btnText: 'Save name',
					fields: [nameField]
				},
				{
					key: 'changePassword',
					stngTitle: 'Change password',
					btnText: 'Save password',
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
