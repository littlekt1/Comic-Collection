<template>
  <div class="collection-detail">
    <div class="content-container">
      <h1>{{ collection.collectionName }}</h1>
      
      <!-- Share Collection URL -->
      <div class="share-collection">
        <button @click="importComics">Import Comics</button>

        <p>Share this collection:</p>
        <input type="text" :value="collectionURL" readonly />
      </div>
      
      <!-- Comic Grid -->
      <div class="comic-grid">
        <div v-for="comicId in collection.comicsInCollection" :key="comicId" class="comic-item">
          <img src="https://via.placeholder.com/200" alt="Placeholder Image" />
          <!-- <p>{{ comic.name }}</p> -->
        </div>
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
import CollectionService from '../services/CollectionService';
export default {
  data() {
    return {
      collection: {
        collectionName: '',
        collectionId: this.$route.params.id,
        comicsInCollection: [],
      },
    };
  },
  created() {
    this.getComicsFromCollection(this.collection.collectionId);
  },
  computed: {
    collectionURL() {
      // Get the current route and build the collection URL
      const currentRoute = this.$route.fullPath;
      return `${window.location.origin}${currentRoute}?`;
    },
  },
  methods: {
    getComicsFromCollection(collectionId)  {
      CollectionService.getCollection(collectionId).then(response => {

        this.collection = response.data;
      })
    },
    importComics() {
      // Logic to import comics
      // Add your implementation here
    },
  },
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