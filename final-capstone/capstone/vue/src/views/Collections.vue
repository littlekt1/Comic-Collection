<template>
  <div class="collections">
    <h1>Collections</h1>
    <form @submit.prevent="createCollection">
      <input type="text" v-model="newCollectionName" placeholder="Enter collection name" required />
      <label>
        Public:
        <input type="checkbox" v-model="isPublic" />
      </label>
      <button type="submit">Create Collection</button>
    </form>

    <div v-if="collections.length > 0">
      <h2>Your Collections:</h2>
      <ul>
        <li v-for="collection in collections" :key="collection.id">
  <router-link :to="`/collections/${collection.id}`">
    <div class="collection-card">
      <img :src="collection.collectionImage" alt="Collection Image" />
      <p class="collection-name">{{ collection.name }}</p>
    </div>
  </router-link>
</li>
      </ul>
    </div>
    <div v-else>
      <p>No collections found.</p>
    </div>
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
          collectionImage: 'https://via.placeholder.com/200x200', // Initialize an empty string for collection image
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
.collections {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: flex-start;
  text-align: center;
  margin-top: 2rem;
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

</style>
