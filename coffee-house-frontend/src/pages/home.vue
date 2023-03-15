<template>
	<section id="banner-section" class="main-img" :style="bgImage">
		<div class="arrow">
			<a href="#about-section" @click.prevent="goToSection('about-section')">
				<span></span>
				<span></span>
				<span></span>
			</a>
		</div>
	</section>
	<section id="about-section">
		<div class="wrapper-content">
			<div class="container">
				<div class="grid home-card-wrapper">
					<div class="grid-column" v-for="card in cardContent" :key="card.id">
						<Card class="grid-cell" :title="card.title" :description="card.description" :imgName="card.imgName" />
					</div>
				</div>
				<div class="grid home-info-wrapper" style="margin-top: 2rem; margin-bottom: 2rem">
					<div class="grid-column">
						<div class="grid-cell cell-img-padding hover-zoom">
							<img :src="imgPath(homeImg(1))" />
						</div>
						<div class="grid-cell cell-img-padding hover-zoom shift-top-right">
							<img :src="imgPath(homeImg(2))" />
						</div>
					</div>
					<div class="grid-column">
						<div class="grid-cell cell-img-padding hover-zoom shift-bottom-left" style="z-index: 1">
							<img :src="imgPath(homeImg(3))" />
						</div>
						<div class="grid-cell cell-img-padding hover-zoom shift-top">
							<img :src="imgPath(homeImg(4))" />
						</div>
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
								<div v-if="addressesIsLoaded">
									<p v-for="address in addresses" :key="address.id">{{ address.address }}</p>
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
							</li>
						</ul>
					</div>
					<div class="grid-column">
						<div class="grid-cell ymap-wrapper">
							<YMap />
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
</template>

<script>
import { mapGetters, mapActions } from 'vuex'
import { links } from '@/_config'
import imgPath from '@/mixins/imgPath'
import YMap from '@/components/YMap.vue'
import Card from '@/components/UI/Card.vue'
import ArrowLink from '@/components/UI/ArrowLink.vue'
import LoaderCard from '@/components/UI/LoaderCard.vue'

export default {
	components: {
		Card,
		ArrowLink,
		YMap,
		LoaderCard
	},
	data() {
		return {
			imgMainBanner: 'main-img.png',
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
					description:
						'Try our signature desserts: specially selected flavor combinations and natural ingredients will definitely make your day much better',
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
		...mapActions('addresses', ['requestAddresses'])
	},
	computed: {
		bgImage() {
			return {
				'--imgMainBanner': `url(${this.imgPath(this.imgMainBanner)})`
			}
		},
		openHoursText() {
			return `daily ${this.openingHours.beginning} - ${this.openingHours.ending}`
		},
		...mapGetters('addresses', { addresses: 'getAddresses', addressesIsLoaded: 'isLoaded', openingHours: 'getOpeningHours' })
	},
	mounted() {
		this.requestAddresses()
	},
	mixins: [imgPath]
}
</script>
