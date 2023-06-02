<template>
  <div class="trending-comics">
    <h3>Check out the currently trending comics:</h3>
    <div class="carousel-container">
      <vue-carousel :autoplay="true" :loop="true" :items-per-page="1" :autoplay-timeout="5000">
        <vue-carousel-slide v-for="(slide, index) in comicSlides" :key="index">
          <div class="comic-row">
            <div v-for="(comic, comicIndex) in slide" :key="comicIndex" class="comic-item">
              <img :src="require(`@/components/${comic.image}`)" :alt="'Comic ' + (comicIndex + 1)" />
              <p>{{ comic.description }}</p>
            </div>
          </div>
        </vue-carousel-slide>
      </vue-carousel>
    </div>
  </div>
</template>

<script>
import { Carousel, Slide } from 'vue-carousel';

export default {
  name: 'TrendingComics',
  components: {
    VueCarousel: Carousel,
    VueCarouselSlide: Slide,
  },
  data() {
    return {
      comics: [
        {
          image: 'comic0.jpg',
          description: 'The Avengers (1963) Add Comic Button',
        },
        {
          image: 'comic1.jpg',
          description: 'Daredevil (1964) Add Comic Button',
        },
        {
          image: 'comic2.jpg',
          description: 'The Sensational She-Hulk (1989) Add Comic Button',
        },
        {
          image: 'comic3.jpg',
          description: 'Strange Tales (1951) Add Comic Button',
        },
        {
          image: 'comic4.jpg',
          description: 'The Amazing Spider-Man (1963) Add Comic Button',
        },
        {
          image: 'comic5.jpg',
          description: 'The Amazing Spider-Man (2022) Add Comic Button',
        },
        {
          image: 'comic6.jpg',
          description: 'Fantastic Four (2018) Add Comic Button',
        },
        {
          image: 'comic7.jpg',
          description: 'Thor (2020) Add Comic Button',
        },
        {
          image: 'comic8.jpg',
          description: 'The Invincible Iron Man (1968) Add Comic Button',
        },
        {
          image: 'comic9.jpg',
          description: 'Captain America (1968) Add Comic Button',
        },
        {
          image: 'comic10.jpg',
          description: 'The X-Men (1963) Add Comic Button',
        },
        {
          image: 'comic11.jpg',
          description: 'Venom (2018) Add Comic Button',
        },
        {
          image: 'comic12.jpg',
          description: 'Wolverine (2020) Add Comic Button',
        },
        {
          image: 'comic13.jpg',
          description: 'Doctor Strange (1968) Add Comic Button',
        },
        {
          image: 'comic14.jpg',
          description: 'Marvels Greatest Comics (1969) Add Comic Button',
        },
        {
          image: 'comic15.jpg',
          description: 'Spider-Man 2099: Dark Genesis (2023) Add Comic Button',
        },
       ],
    };
  },
 computed: {
    comicSlides() {
      const comicsPerSlide = 4;
      const slides = [];
      for (let i = 0; i < this.comics.length; i += comicsPerSlide) {
        slides.push(this.comics.slice(i, i + comicsPerSlide));
      }
      return slides;
    },
  },
};
</script>

<style scoped>
.trending-comics {
  text-align: center;
}

.carousel-container {
  display: flex;
  justify-content: center;
  align-items: flex-start;
  max-width: 800px;
  margin: 0 auto;
}

.comic-row {
  display: flex;
  justify-content: center;
  align-items: flex-start;
  margin-bottom: 10px;
  flex-wrap: wrap;
}

.comic-item {
  flex: 1 0 25%;
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-bottom: 10px;
}

img {
  width: 200px;
  height: 300px;
  object-fit: cover;
  margin: 3px;
}

.comic-item p {
  margin-top: 10px;
  text-align: center;
}
</style>