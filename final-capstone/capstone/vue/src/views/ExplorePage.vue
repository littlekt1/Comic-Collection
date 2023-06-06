<template>
  <div class="explore-page">
    <h1 class="flashy-text">Explore Comics</h1>
    <router-link to="/bulk-add-comics" class="gold-link">Bulk Add Comics</router-link>

    <!-- Search Bar -->
    <div class="search-bar">
      <input type="text" v-model="searchQuery" placeholder="Search by comic name, author, or characters">
      <button @click="search">Search</button>
    </div>

    <!-- Comic Grid -->
    <div class="comic-grid">
      <div v-for="comic in results" :key="comic.id" class="comic-item">
        <router-link :to="{name: 'comic', params: {id: comic.id}}">
          <img :src="comic.image" alt="Comic Image">
        </router-link>
        <p>{{ comic.issue }}</p>
      </div>
    </div>

    <div class="image-container">
      <img src="../assets/thing.png" alt="Comic Image" class="bottom-left-image">
      <div class="word-bubble">
        <p class="bubble-text">It's Clobberin' Time!</p>
      </div>
    </div>
  </div>
</template>

<script>
import MetronService from '../services/MetronService';
export default {
  data() {
    return {
      
      results: [
        // Array of comics objects
      ],
      searchQuery: '',
    };
  },
  computed: {
    filteredComics() {
      const query = this.searchQuery.toLowerCase().trim();
      if (!query) {
        return this.comics;
      }
      return this.comics.filter((comic) => {
        return (
          comic.name.toLowerCase().includes(query) ||
          comic.author.toLowerCase().includes(query) ||
          comic.characters.some((character) => character.toLowerCase().includes(query))
        );
      });
    },
  },
  methods: {
    search() {
      MetronService.get(this.searchQuery).then(response => {
        this.results = response.data
                console.log(response.data);

      })
      // Perform search logic here
      console.log('Search query:', this.searchQuery);
      // You can implement the actual search functionality here,
      // such as making an API request or filtering the data locally.
      // Modify the `comics` array based on the search query or make an API call.
    },
  },
};
</script>


<style scoped>
.explore-page {
  position: relative;
  text-align: center;
  min-height: 100vh;
  background-image: url('../../public/comicbackground.jpg') scale;
  background-repeat: no-repeat;
  background-size: cover;
  background-position: center;
}


.explore-page h1 {
  margin-bottom: 20px;
  font-size: 50px;
}

.flashy-text {
  background-image: linear-gradient(to right, gold, silver);
  -webkit-background-clip: text;
  background-clip: text;
  -webkit-text-fill-color: transparent;
  animation: flashyAnimation 1s linear infinite;
}

.gold-link {
  display: block;
  margin-bottom: 10px;
  font-size: 30px;
  color: gold;
  width: 75%;
  margin: 0 auto;
}

.bottom-left-image {
  position: fixed;
  bottom: 100px;
  left: 100px;
  max-width: 200px;
  scale: 3;
}

.word-bubble {
  position: fixed;
  top: 68%;
  left: 20%;
  transform: translate(-50%, -50%);
  background-image: url('../assets/wordbubble.png');
  background-repeat: no-repeat;
  background-position: left;
  background-size: contain;
  width: 225px;
  height: 200px;
}

.bubble-text {
  font-size: 32px;
  text-align: center;
  padding: 20px;
  color: black;
}

@keyframes flashyAnimation {
  0% {
    color: gold;
  }
  50% {
    color: silver;
  }
  100% {
    color: gold;
  }
}

/* Add a media query to hide the images on mobile */
@media (max-width: 600px) {
  .bottom-left-image,
  .word-bubble,
  .top-left-image,
  .top-right-image {
    display: none;
  }
}

.search-bar {
  margin: 20px 0;
}

.search-bar input[type="text"] {
  border: 2px solid black;
  padding: 8px;
  font-size: 16px;
}

.search-bar button {
  font-size: 16px;
  padding: 8px 16px;
  background-color: #4caf50;
  color: white;
  border: none;
  cursor: pointer;
}

.comic-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
  gap: 20px;
  max-width: 1000px;
  margin: 0 auto;
}

.comic-item {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.comic-item img {
  width: 150px;
  max-height: 200px;
  object-fit: cover;
}
</style>