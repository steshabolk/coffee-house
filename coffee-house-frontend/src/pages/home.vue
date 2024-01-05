<template>
	<section id="banner-section" class="bg-img" :style="getBgImage(this.bgImage)">
		<div class="arrow">
			<a href="#about-section" @click.prevent="goToSection('about-section')">
				<span></span>
				<span></span>
				<span></span>
			</a>
		</div>
	</section>
	<section id="about-section">
		<div class="grid home-card-wrapper">
			<Card
				v-for="card in cardContent"
				:key="card.id"
				class="grid-cell"
				:title="card.title"
				:description="card.description"
				:imgName="card.imgName" />
		</div>
		<div class="wrapper-content" style="margin-top: 0">
			<div class="container">
				<div class="grid home-info-wrapper" style="margin-top: 1rem; margin-bottom: 2rem">
					<div class="grid-column tablet_disabled" style="gap: 0">
						<img class="grid-cell hover-zoom" :src="imgPath(homeImg(1))" />
						<img class="grid-cell hover-zoom shift-top-right" :src="imgPath(homeImg(2))" />
					</div>
					<div class="grid-column tablet_disabled" style="gap: 0">
						<img class="grid-cell hover-zoom shift-bottom-left" style="z-index: 1" :src="imgPath(homeImg(3))" />
						<img class="grid-cell hover-zoom shift-top" :src="imgPath(homeImg(4))" />
					</div>
					<div class="grid-column home-info-gap">
						<div class="grid-cell">
							<div class="home-info-block">
								<p>{{ info.p1 }}</p>
								<p>{{ info.p2 }}</p>
							</div>
						</div>
						<div class="grid-cell">
							<ArrowLink :link="menuLink.link" :linkText="menuLink.linkText" />
						</div>
					</div>
				</div>
				<div class="grid contact-wrapper" id="contact">
					<div class="grid-column">
						<ul class="grid-cell">
							<li>
								<p class="main-title">Our addresses:</p>
								<div v-if="isCHsLoaded">
									<p v-for="ch in coffeeHouses" :key="ch.id">{{ ch.address }}</p>
								</div>
								<LoaderCard v-else style="height: 8rem" />
							</li>
							<li>
								<p class="main-title">Opening hours:</p>
								<p>{{ openHoursText }}</p>
							</li>
							<li>
								<p class="main-title">Contact us:</p>
								<p>coffee-houses@info.com</p>
								<div class="social-media-wrapper">
									<a v-for="icon in socialMediaContent" :key="icon.socialMedia" class="social-media-icon" :href="icon.link" :class="icon.socialMedia">
										<SvgIcon :viewBox="icon.viewBox" :path="icon.svgPath" />
									</a>
								</div>
							</li>
						</ul>
					</div>
					<div class="grid-column ymap-wrapper">
						<YMap />
					</div>
				</div>
			</div>
		</div>
	</section>
</template>

<script>
import { mapGetters, mapActions } from 'vuex'
import { links } from '@/_config'
import { vkIcon, instagramIcon, telegramIcon } from '@/services/svgIcons'
import imgPath from '@/mixins/imgPath'
import SvgIcon from '@/components/UI/SvgIcon.vue'
import YMap from '@/components/YMap.vue'
import Card from '@/components/UI/Card.vue'
import ArrowLink from '@/components/UI/ArrowLink.vue'
import LoaderCard from '@/components/UI/LoaderCard.vue'

export default {
	components: {
		Card,
		YMap,
		LoaderCard,
		ArrowLink,
		SvgIcon
	},
	data() {
		return {
			bgImage: 'main-img.png',
			cardContent: [
				{
					id: 1,
					title: 'Coffee',
					description: 'Each cup of coffee is made from selected freshly roasted beans and a piece of good mood from our baristas',
					imgName: 'card-coffee.png'
				},
				{
					id: 2,
					title: 'Food',
					description: 'We are happy to treat you to our fresh pastries, sandwiches, toast or pancakes',
					imgName: 'card-food.png'
				},
				{
					id: 3,
					title: 'Desserts',
					description: 'Try our signature desserts: specially selected flavor combinations will definitely make your day much better',
					imgName: 'card-dessert.png'
				}
			],
			info: {
				p1: `Our coffee houses are a cozy place where you can share any moments with us:
        whether it's a long-awaited meeting with friends or a cup of coffee after a hard day.
        We are always happy to chat with you about everything if we are not busy:
        about coffee or books, a new released movie or an author's exhibition, your dog or the weather.`,
				p2: `Every day we make delicious desserts, bake crispy croissants
        and make invigorating coffee to give you a good mood.`
			},
			socialMediaContent: [
				{
					link: '',
					socialMedia: 'vk',
					...vkIcon
				},
				{
					link: '',
					socialMedia: 'instagram',
					...instagramIcon
				},
				{
					link: '',
					socialMedia: 'telegram',
					...telegramIcon
				}
			],
			menuLink: {
				link: links[1].url,
				linkText: 'Menu'
			}
		}
	},
	methods: {
		goToSection(section) {
			document.getElementById(section).scrollIntoView({ behavior: 'smooth' })
		},
		homeImg(imgNum) {
			return `home-img-${imgNum}.png`
		},
		...mapActions('coffeeHouse', ['requestCoffeeHouses'])
	},
	computed: {
		openHoursText() {
			return `daily ${this.openingHours.beginning} - ${this.openingHours.ending}`
		},
		...mapGetters('coffeeHouse', { coffeeHouses: 'getCoffeeHouses', isCHsLoaded: 'isLoaded', openingHours: 'getOpeningHours' })
	},
	mounted() {
		this.requestCoffeeHouses()
	},
	mixins: [imgPath]
}
</script>
