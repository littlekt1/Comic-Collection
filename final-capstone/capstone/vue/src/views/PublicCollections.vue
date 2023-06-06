<template>
  <div class="collections-container">
    <div class="static-image left-image"></div>
    <div v-if="this.isLoading" class="loading">

    </div>
    <div v-if="!!this.$store.state.token" v-show="!this.isLoading" class="collections">
      <h1>Public Collections</h1>

      <div v-if="collections.length > 0">
        <!-- <h2>Public Collections:</h2> -->
        <ul>
          <li v-for="collection in collections" :key="collection.collectionId">
            <router-link :to="`/collections/${collection.collectionId}`" class="gold-link">
              <div class="collection-card">
                <img :src="collection.collectionImage" alt="Collection Image" />
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
      <h1>Please Sign or Register to create and view your own Collections</h1>
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
      isPublic: false, // Default to public
      isLoading: true,
    };
  },
  created() {
    this.updateCollections();
  },
  methods: {
    updateCollections() {
      collectionService.getUserCollections().then(response => {
      this.collections = response.data;
      this.isLoading = false;
    })
    },
    createCollection() {
      this.isLoading = true;
      if (this.newCollectionName.trim() !== '') {
        const newCollection = {
          collectionName: this.newCollectionName.trim(),
          public: this.isPublic, // Set the isPublic property based on the checkbox
          comicsInCollection: [], // Initialize an empty array for comics
          collectionImage: 'https://via.placeholder.com/150x200', // Initialize an empty string for collection image
       };

//We will check for this elsewhere.
        // // Check user role and limit the number of comics based on it
        // if (this.$store.state.userRole === 'standard') {
        //   // Standard user can have up to 100 comics in a collection
        //   if (newCollection.comics.length >= 100) {
        //     alert('You have reached the maximum limit for comics in a collection.');
        //     return;
        //   }
        // }

        collectionService.createCollection(newCollection);
        this.updateCollections();
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
  flex-basis: 35%;
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