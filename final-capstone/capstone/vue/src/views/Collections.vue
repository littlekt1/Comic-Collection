<template>
  <div class="collections-container">
    <div class="static-image left-image"></div>
    <div v-if="this.isLoading" class="loading">

    </div>
    <div v-if="!!this.$store.state.token" v-show="!this.isLoading" class="collections">
      <h1>User Collections</h1>
      
      <router-link to="/import-comics">
        <button class="navbtn">Import Comics</button>
      </router-link>
      <form @submit="createCollection()">
        <input type="text" v-model="newCollectionName" placeholder="Enter collection name" required class='name'/>
        <label>
          Public:
          <input type="checkbox" v-model="isPublic" />
        </label>
        <button type="submit">Create Collection</button>
        <link href="https://fonts.googleapis.com/css2?family=Bangers&display=swap" rel="stylesheet">
        
      </form>

      <div v-if="collections.length > 0">
        <h2>Your Collections:</h2>
        
        <ul>
          <li v-for="collection in collections" :key="collection.collectionId">
            <router-link :to="`/collections/${collection.collectionId}`" class="gold-link">
              <div class="collection-card">
                <img :src="getCollectionImage(collection)" alt="Collection Image" />
                <p class="collection-name larger-text">{{ collection.collectionName }}</p>
              </div>
            </router-link>
          </li>
        </ul>
      </div>
      <div v-else>
        <p>No collections found.</p>
      </div>
    </div>
    <div v-else class="collections">
      <h1>Please Sign In to create and view your own Collections</h1>
    </div>
    <div class="static-image right-image"></div>
  </div>
</template>

<script>
import collectionService from '../services/CollectionService.js'
export default {
  data() {
    return {
      newCollectionName: '',
      isPublic: false, // Default to private
      collections: [],
      isLoading: true,
    };
  },
  created() {
    this.updateCollections();
  },
  methods: {
    getCollectionImage(collection) {
      if (collection.comicsInCollection.length == 0) {
        return "https://via.placeholder.com/150x200";
      }
      return "https://via.placeholder.com/150x200";//collection.comicsInCollection[0]//image of first comic call here
    },
    updateCollections() {
      this.isLoading = true;
      collectionService.getUserCollections().then(response => {
      this.collections = response.data;
      this.isLoading = false;
      })
    },
    createCollection() {
      
      if (this.newCollectionName.trim() !== '') {
        const newCollection = {
          collectionName: this.newCollectionName.trim(),
          public: this.isPublic, // Set the isPublic property based on the checkbox
          comicsInCollection: [], // Initialize an empty array for comics
       };


        collectionService.createCollection(newCollection);
        this.newCollectionName = ''; // Clear the input field after creating a collection
        this.isPublic = false; // Reset the isPublic checkbox
      }
    },
  },
};
</script>

<style scoped>
.name{
  font-family: 'Bangers', cursive;
  font-size: 15px;
}
.navbtn {
  font-family: 'Bangers', cursive;
  font-size: 18px;
  width:25%;
  margin-bottom: 6px;
}
.static-image {
  position: fixed;
  top: 160px;
  bottom: 0;
  width: 30%; /* Adjust the width as needed */
  background-size: contain;
  background-position: center;
  background-repeat: no-repeat;
}

.left-image {
  left: 0;
  background-image: url('../assets/starwarspanel.jpg');
  top: 90px;
}

.right-image {
  right: 0;
  background-image: url('../assets/batmancomicpanel.jpg');
  top: 90px;
}

.collections-container {
  top: 10px;
  position: relative;
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 22vh;
}

button[type="submit"] {
  font-family: 'Bangers', cursive;
  font-size: 18px;
}

.collections {
  text-align: center;
  flex-basis: 25%;
  padding: 10px;
  border: 2px solid gray;
  margin: 10px;
  background-color: rgba(0, 0, 0, 0.568);
  z-index: 1;
}

.collections-container {
  display: flex;
  justify-content: center;
  align-items: top;
  min-height: 22vh;
}

h1 {
  margin-bottom: 1rem;
}

.collection-card {
  text-align: center;
}

.collection-image {
  width: 200px; /* Adjust the width as needed */
  height: auto; /* Maintain aspect ratio */
  margin-bottom: 10px;
}

.collection-name {
  font-weight: bold;
}

ul {
  list-style: none;
}

.gold-link {
  color: gold; /* Set the hyperlink color to gold */
  text-decoration: none; /* Remove underline from the hyperlink */
}

.gold-link:hover {
  text-decoration: underline; /* Add underline on hover */
}

.larger-text {
  font-size: 25px; /* Set the font size to 20 pixels */
}

@media (max-width: 767px) {
  .static-image.left-image,
  .static-image.right-image {
    display: none;
  }
  .collections-container {
    min-height: 40vh;
  }

  .collections {
    flex-basis: 80%;
  }

}

</style>
