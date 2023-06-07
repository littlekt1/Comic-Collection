<template>
  <div class="home">
    <h1 class="header-gradient">Home</h1>
    <p v-if="isAuthenticated">You are authenticated and can see this content.</p>
    <p v-else>You must be authenticated to see this.</p>
    <div class="main-content">
      <div class="section trending-comics">
        <h2>Trending Comics</h2>
        <div class="section-content">
          <div class="carousel-container">
             <div class="carousel-container">
        <div class="carousel">
          <div v-for="(collection, index) in collections" :key="collection.collectionId" class="carousel-item">
            <router-link
              v-if="index < 5"
              :to="`/collections/${collection.collectionId}`"
              class="gold-link"
            >
              <div class="collection-card">
                <img :src="getCollectionImage(collection)" alt="Collection Image" />
                <p class="collection-name larger-text gold-text">{{ collection.collectionName }}</p>
              </div>
            </router-link>
          </div>
        </div>
      </div>
    
            <TrendingComics />
          </div>
        </div>
      </div>
      <div class="section my-collections">
        <h2>My Top 5 Collections</h2>
        <div class="section-content" v-if="!!this.$store.state.token">
      <ul class="collection-list">
        <li v-for="(collection, index) in collections" :key="collection.collectionId">
          <router-link
            v-if="index < 5"  
            :to="`/collections/${collection.collectionId}`"
            class="gold-link"
          >
            <div class="collection-card">
              <img :src="getCollectionImage(collection)" alt="Collection Image" />
              <p class="collection-name larger-text">{{ collection.collectionName }}</p>
            </div>
          </router-link>
        </li>
      </ul>
    </div>
    <div v-else> You need to be logged in to create collections.
      </div>
      </div>
      <div class="section aggregate-stats">
        <h2>Aggregate Stats</h2>
        <div class="section-content">
          <p>Total number of comics:</p>
          <p>Your number of collections:</p>
          <p>Superhero appearances:</p>
        </div>
      </div>
    </div>
    <footer class="footer">
      <p>Data from GameSpot © 2023 GAMESPOT</p>
    </footer>
  </div>
</template>

<script>
import TrendingComics from '../components/TrendingComics.vue';
import collectionService from '../services/CollectionService.js';

export default {
  components: {
    TrendingComics,
  },
  name: 'Home',
  data() {
    return {
      isAuthenticated: false,
      collections: [],
    };
  },
  created() {
    this.isAuthenticated = true;
    this.updateCollections();
  },
    mounted() {
    // Initialize carousel
    const carousel = document.querySelector('.carousel');
    const carouselItems = document.querySelectorAll('.carousel-item');
    const carouselItemWidth = carouselItems[0].offsetWidth;
    let currentIndex = 0;

    // Set initial position
    carousel.style.transform = `translateX(0)`;

    // Add event listeners
    window.addEventListener('resize', () => {
      carousel.style.transform = `translateX(-${currentIndex * carouselItemWidth}px)`;
    });

    setInterval(() => {
      currentIndex = (currentIndex + 1) % carouselItems.length;
      carousel.style.transform = `translateX(-${currentIndex * carouselItemWidth}px)`;
    }, 3000);
  },

  methods: {
    updateCollections() {
      collectionService.getUserCollections().then((response) => {
        this.collections = response.data;
      });
    },
    getCollectionImage(collection) {
      if (collection.comicsInCollection.length === 0) {
        return "https://via.placeholder.com/150x200";
      }
      // Return the image URL of the first comic in the collection
      return collection.comicsInCollection[0].imageUrl;
    },
  },
};
</script>


<style scoped>
.custom-cursor {
  cursor: url('../assets/avengers.png') 10 10, pointer;
}
.home {
  display: flex;
  flex-direction: column;
  text-align: center;
  min-height: 100vh;
  font-display: bold;
}
.header-gradient {
  background-image: linear-gradient(to right, gold, silver);
  -webkit-background-clip: text;
  background-clip: text;
  -webkit-text-fill-color: transparent;
  animation: flashyAnimation 1s linear infinite;
  font-size: 90px; /* Increase the font size */
  margin-bottom: 0; /* Remove the bottom margin */
  margin-top: 15px; /* Remove the top margin */
}

.main-content {
  flex: 1;
  display: flex;
  justify-content: space-between;
  margin-top: 20px;
}

.footer {
  text-align: center;
  border: 2px solid gray;
  border-radius: 10px;
  margin: 10px;
  background-color: rgba(150, 150, 150, 0.568);
  z-index: 1;
}

.section {
  flex-basis: 45%;
  margin: 0 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
  padding: 10px;
  border: 2px solid gray;
  border-radius: 10px;
  margin: 10px;
  background-color: rgba(0, 0, 0, 0.568);
  z-index: 1;
}

.trending-comics {
  text-align: center;
}

.trending-comics .section-content {
  display: flex;
  justify-content: center;
}
.gold-text {
  color: gold; /* Set the collection name color to gold */
}

.gold-link {
  color: gold; /* Set the link color to gold */
  text-decoration: none; /* Remove underline from the link */
}

.carousel-container {
  display: flex;
  justify-content: center;
  overflow: hidden;
  max-width: 800px;
  margin: 0 auto;
}
.carousel {
  display: flex;
  transition: transform 0.3s ease-in-out;
}

.carousel-item {
  flex: 0 0 100%;
}

.my-collections {
   flex-basis: 37%;
  flex-direction: column; /* Set flex direction to column */
  justify-content: center; /* Center items vertically */
  align-items: center; /* Center items horizontally */
}

.aggregate-stats {
  flex-basis: 15%;
}

.section-content {
  margin-top: 10px;
}

.comic-item,
.collection-item {
  display: flex;
  align-items: center;
  margin-bottom: 10px;
}

.comic-item img,
.collection-item img {
  width: 50px;
  height: 50px;
  margin-right: 20px;
}
.collection-list {
  list-style-type: none; /* Hide bullet points */
}

.collection-card {
  display: flex;
  align-items: center;
  border: 2px solid gold; /* Add a border with a gold color */
  padding: 5px;
  height: 150px; /* Keep the height of the collection card */
  margin: 10px; /* Add margin to create spacing between cards */
  width: 80%;
}

.collection-card img {
  width: 100px;
  height: 150px;
  margin-right: 10px;
}

.collection-card .collection-name {
  flex-grow: 1;
  font-size: 45px;
  color: gold; /* Set the collection name color to gold */
  text-align: center;
}
@media (max-width: 767px) {
  .main-content {
    flex-direction: column;
  }

  .section {
    flex-basis: 100%;
  }

  .my-collections {
    margin-top: 20px;
  }

  .comic-item img,
  .collection-item img {
    width: 100%;
    height: auto;
    margin-right: 10px;
  }

  .carousel-container {
    display: flex;
    justify-content: flex-start;
    overflow-x: auto;
    scroll-snap-type: x mandatory;
    -webkit-overflow-scrolling: touch;
    margin: 20px 0;
  }

  .carousel-container::-webkit-scrollbar {
    height: 6px;
  }

  .carousel-container::-webkit-scrollbar-thumb {
    background-color: #888;
    border-radius: 3px;
  }

  .carousel-container::-webkit-scrollbar-track {
    background-color: #f1f1f1;
    border-radius: 3px;
  }
}
</style>


