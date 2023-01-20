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
				<div class="home-card-wrapper">
					<div class="grid">
						<div class="grid-column" v-for="card in cardContent" :key="card.id">
							<HomeCard class="grid-cell" :title="card.title" :subtitle="card.subtitle" :description="card.description" :imgName="card.imgName" />
						</div>
					</div>
				</div>
				<div class="home-content-wrapper">
					<div class="grid">
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
						<div class="grid-column">
							<div class="grid-cell cell-text-padding">
								<div class="home-content-block">
									<p>{{ textContent.p1 }}</p>
									<p>{{ textContent.p2 }}</p>
								</div>
							</div>
							<div class="grid-cell">
								<ArrowLink :link="menuLink.link" :linkText="menuLink.linkText" />
							</div>
						</div>
					</div>
				</div>
				<div class="contact-wrapper" id="contact">
					<div class="grid">
						<div class="grid-column">
							<div class="grid-cell">
								<div class="contact-block">
									<p class="main-title">Our addresses:</p>
									<div v-if="addressesIsLoaded">
										<p style="line-height: 1.3; margin-bottom: 5px" v-for="address in addresses" :key="address.id">{{ address.address }}</p>
									</div>
									<LoaderCard v-else style="height: 120px" />
									<p class="main-title">Opening hours:</p>
									<p>{{ openHoursText }}</p>
									<p class="main-title">Contact us:</p>
									<p>coffee-houses@info.com</p>
								</div>
							</div>
						</div>
						<div class="grid-column">
							<div class="grid-cell">
								<div class="ymap-wrapper">
									<Map />
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
</template>

<script>
import HomeCard from '@/components/UI/HomeCard.vue'
import ArrowLink from '@/components/UI/ArrowLink.vue'
import LoaderCard from '@/components/UI/LoaderCard.vue'
import Map from '@/components/Map.vue'
import imgPath from '@/mixins/imgPath'
import { mapGetters, mapActions } from 'vuex'
import { links } from '@/_config'

export default {
	components: {
		HomeCard,
		ArrowLink,
		Map,
		LoaderCard
	},
	data() {
		return {
			imgMainBanner: 'main-img.png',
			cardContent: [
				{
					id: 1,
					title: 'Coffee',
					subtitle: 'Boost your energy',
					description: 'Each cup of coffee is made from selected freshly roasted beans and a piece of good mood from our baristas',
					imgName: 'card-coffee.png'
				},
				{
					id: 2,
					title: 'Food',
					subtitle: 'Grab a bite on the way',
					description: 'We are happy to treat you to our fresh pastries, sandwiches, toast or pancakes',
					imgName: 'card-food.png'
				},
				{
					id: 3,
					title: 'Desserts',
					subtitle: 'Dessert is always a good idea',
					description:
						'Try our signature desserts: specially selected flavor combinations and natural ingredients will definitely make your day much better',
					imgName: 'card-dessert.png'
				}
			],
			textContent: {
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
		if (this.addresses.length === 0) {
			this.requestAddresses()
		}
	},
	mixins: [imgPath]
}
</script>
