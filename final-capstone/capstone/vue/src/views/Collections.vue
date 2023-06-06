<template>
  <div class="collections-container">
    <div class="static-image left-image"></div>

    <div class="collections">
      <h1>Collections</h1>
      <router-link to="/import-comics">
        <button class="navbtn">Import Comics</button>
      </router-link>
      <form @submit.prevent="createCollection">
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
          <li v-for="collection in collections" :key="collection.id">
            <router-link :to="`/collections/${collection.id}`" class="gold-link">
              <div class="collection-card">
                <img :src="collection.collectionImage" alt="Collection Image" />
                <p class="collection-name larger-text">{{ collection.name }}</p>
              </div>
            </router-link>
          </li>
        </ul>
      </div>
      <div v-else>
        <p>No collections found.</p>
      </div>
    </div>
    <div class="static-image right-image"></div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      newCollectionName: '',
      isPublic: false, // Default to private
      collections: [],
    };
  },
  methods: {
    createCollection() {
      if (this.newCollectionName.trim() !== '') {
        const newCollection = {
          id: Math.random().toString(36).substr(2, 9), // Generate a random ID for the collection
          name: this.newCollectionName.trim(),
          isPublic: this.isPublic, // Set the isPublic property based on the checkbox
          comics: [], // Initialize an empty array for comics
          collectionImage: 'https://via.placeholder.com/150x200', // Initialize an empty string for collection image
       };

        // Check user role and limit the number of comics based on it
        if (this.$store.state.userRole === 'standard') {
          // Standard user can have up to 100 comics in a collection
          if (newCollection.comics.length >= 100) {
            alert('You have reached the maximum limit for comics in a collection.');
            return;
          }
        }

        this.collections.push(newCollection);
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
  background-image: url('../../public/starwarspanel.jpg');
  top: 90px;
}

.right-image {
  right: 0;
  background-image: url('../../public/batmancomicpanel.jpg');
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