<template>
  <div class="collection-detail">
    <div class="content-container">
      <h1>{{ collection.collectionName }}</h1>
      
      <!-- Add Comic Form -->
      <form @submit.prevent="addComic" class="add-comic-form">
        <input type="text" v-model="newComicName" placeholder="Enter comic name" required />
        <button type="submit">Add Comic</button>
      </form>
      
      <!-- Share Collection URL -->
      <div class="share-collection">
        <button @click="importComics">Import Comics</button>

        <p>Share this collection:</p>
        <input type="text" :value="collectionURL" readonly />
          <button @click="deleteCollection">Delete Collection</button>

      </div>
      
      <!-- Comic Grid -->

      <div class="comic-grid" v-show="!isLoading">
        <div v-for="comicId in collection.comics" :key="comicId" class="comic-item">
          <router-link :to="{name: 'comic', params: {id:comicId}}" class="gold-link">
            <comicCard :id="comicId"/>
          </router-link>
        </div>
      </div>
      <div class="loading" v-show="isLoading">
        <p>Now Loading...</p>
        <img src="../assets/loading.gif" alt="">
      </div>

      <!-- Collection Statistics -->
      <div class="collection-statistics top-right">
        <h2>Collection Statistics</h2>
        <p>Total Comics: {{ collection.comics.length }}</p>
        <p>Total Spider-Man Comics: {{spidermanCount}}</p>
        <p>Total Iron Man Comics: {{ironmanCount}}</p>
        <p>Total Superman Comics: {{supermanCount}}</p>
        <p>Total Flash Comics: {{flashCount}}</p>
          <p>Total Batman Comics: {{batmanCount}}</p>
          <p>Total Thor Comics: {{thorCount}}</p>
          <p>Total Hulk Comics: {{hulkCount}}</p>
      </div>

      <!-- Grievous Image -->
      <div class="image-container">
        <div class="static-image image">
          <div class="grievous-image"></div>
          <div class="word-bubble">
            <p class="bubble-text">These comics will make a fine addition to my collection.</p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import CollectionService from '../services/CollectionService.js';
import ComicCard from '../components/ComicCard.vue';
import MetronService from "../services/MetronService";


export default {
  components: {
    ComicCard
  },
  data() {
    return {
      collection: {
        id: '',
        ownerId: '',
        collectionName: '',
        comics: [],
        public: '',
      },
      comics: [],
      newComicName: '',
      imgSrc: '',
      isLoading: false,
    };
  },
 
  computed: {
    spidermanCount() {
      let count = 0;
      for (const comic of this.comics) {
        if (comic.characters.filter(character => character.name.toLowerCase() == "spider-man").length > 0 ) count++
        
      } return count
    },
       supermanCount() {
      let count = 0;
      for (const comic of this.comics) {
        if (comic.characters.filter(character => character.name.toLowerCase() == "superman").length > 0 ) count++
        
      } return count
    },
     ironmanCount() {
      let count = 0;
      for (const comic of this.comics) {
        if (comic.characters.filter(character => character.name.toLowerCase() == "iron man").length > 0 ) count++
        
      } return count
    },
    thorCount() {
      let count = 0;
      for (const comic of this.comics) {
        if (comic.characters.filter(character => character.name.toLowerCase() == "thor").length > 0 ) count++
        
      } return count
    },
       hulkCount() {
      let count = 0;
      for (const comic of this.comics) {
        if (comic.characters.filter(character => character.name.toLowerCase() == "hulk").length > 0 ) count++
        
      } return count
    },
     batmanCount() {
      let count = 0;
      for (const comic of this.comics) {
        if (comic.characters.filter(character => character.name.toLowerCase() == "batman").length > 0 ) count++
        
      } return count
    },
    flashCount() {
      let count = 0;
      for (const comic of this.comics) {
        if (comic.characters.filter(character => character.name.toLowerCase() == "flash").length > 0 ) count++
        
      } return count
    },
    
    collectionURL() {
      // Get the current route and build the collection URL
      const currentRoute = this.$route.fullPath;
      const collectionID = this.collection.id; // Replace with the actual collection ID
      return `${window.location.origin}${currentRoute}?collectionId=${collectionID}`;
    }
  },
  created() {
    this.getCollection(this.$route.params.id);
    console.log(this.collection.comics);
  },
  methods: {
    getCollection(collectionId) {
      this.isLoading = true;
      CollectionService.getCollection(collectionId).then(response => {
        this.collection.id = response.data.collectionId;
        this.collection.collectionName = response.data.collectionName;
        this.collection.comics = response.data.comicsInCollection;
        this.collection.public = response.data.public;
        this.collection.comics.forEach((id) => {
          MetronService.getComicById(id).then(response => {
            this.comics.push(response.data)

        })
        this.isLoading = false;
      });
    })},
    importComics() {
      // Logic to import comics
      // Add your implementation here
    },
    countComicsByBrand(brand) {
      return this.collection.comics.filter(comic => comic.brand === brand).length;
    },
    updateComicCollection(comic) {
      // This method handles the checkbox change event and updates the 'inCollection' property of the comic
      // You can perform any additional actions based on the checkbox state change here
      console.log(comic.name, 'is now', comic.inCollection ? 'in the collection' : 'not in the collection');
    },
    addComic() {
      // Logic to add a new comic to the collection
      // Add your implementation here
       const newComic = {
    characters: [{ name: 'Spider-Man' }]
       };
       
  this.comics.push(newComic); // Add the new comic to the 'comics' array

  // Update the counts based on the new comic
  if (newComic.characters.filter(character => character.name.toLowerCase() === 'spider-man').length > 0) {
    this.spidermanCount++;
  }
  if (newComic.characters.filter(character => character.name.toLowerCase() === 'superman').length > 0) {
    this.supermanCount++;
  }
  if (newComic.characters.filter(character => character.name.toLowerCase() === 'iron man').length > 0) {
    this.ironmanCount++;
  }
  if (newComic.characters.filter(character => character.name.toLowerCase() === 'thor').length > 0) {
    this.spidermanCount++;
  }
  if (newComic.characters.filter(character => character.name.toLowerCase() === 'hulk').length > 0) {
    this.supermanCount++;
  }
  if (newComic.characters.filter(character => character.name.toLowerCase() === 'batman').length > 0) {
    this.ironmanCount++;
  }
   if (newComic.characters.filter(character => character.name.toLowerCase() === 'flash').length > 0) {
    this.ironmanCount++;
  }

    }
  }
};
</script>


<style scoped>
.image-container {
  position: fixed;
  bottom: 0;
  left: 0;
  right: 0;
  display: flex;
  justify-content: flex-start;
}
.gold-link {
  color: gold;
  text-decoration: none;
}

.content-container {
  flex: 1;
  text-align: center;
}

.image {
  width: 25%; /* Adjust the width as needed */
  height: 700px; /* Adjust the height as needed */
  background-size: contain;
  background-position: bottom left;
  z-index: 1;
  background-repeat: no-repeat;
  position: relative;
}

.grievous-image {
  width: 100%;
  height: 100%;
  position: absolute;
  
  left: -25px;
  bottom:-12px;
  background-image: url('../assets/grievouscomics.png');
  background-size: contain;
  background-position: bottom left;
  z-index: 0;
  background-repeat: no-repeat;
  scale: 1.2
;
}

.word-bubble {
  position: absolute;
  top: 55%;
  left: 79%;
  transform: translate(-50%, -50%);
  background-image: url('../assets/wordbubble.png');
  background-repeat: no-repeat;
  background-position: center;
  background-size: contain;
  width: 200px;
  height: 150px;
  z-index: 3;
}

.bubble-text {
  font-size: 16.5px;
  text-align: center;
  margin: 0;
  padding: 40px;
  color: black;
}

.collection-detail {
  text-align: center;
}

.add-comic-form input[type="text"] {
  border: 2px solid black;
  padding: 8px;
  width: 20%;
  font-size: 16px;
  margin-bottom: 10px;
}
.add-comic-form button {
  font-family: "Bangers", sans-serif;
  font-size: 20px;
  height: 36px;

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
.collection-statistics {
  margin-top: 40px;
  padding: 10px;
  width: 18%;
  border: 2px solid gray;
  border-radius: 10px;
  background-color: rgba(0, 0, 0, 0.868);
  z-index: 1;
   position: fixed;
  top: 120px;
  right: 20px;
  margin-top: 0;
}

.collection-statistics h2 {
  margin-bottom: 10px;
  font-size: 20px;
}

.collection-statistics p {
  font-size: 16px;
}

@media (max-width: 768px) {
  .image-container {
    display: none;
  }

  .add-comic-form input[type="text"] {
    width: 90%;
  }
  .add-comic-form button {
  font-family: "Bangers", sans-serif;
  font-size: 20px;
  height: 36px;
  margin: 20px
}
  .comic-grid {
    /* Add the following styles to center the content */
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
    align-items: center;
  }

   .comic-item {
    /* Update styles to display text under each comic */
    display: flex;
    flex-direction: column;
    align-items: center;
    text-align: center;
  }

  .comic-item img {
    /* Adjust the width and height as needed */
    width: 150px;
    height: 200px;
    object-fit: cover;
  }
}

.share-collection {
  margin-bottom: 20px; /* Add some bottom margin for spacing */
}

</style>
