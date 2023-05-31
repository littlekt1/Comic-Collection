<template>
  <div class="collections">
    <h1>Collections</h1>
    <form @submit.prevent="createCollection">
      <input type="text" v-model="newCollectionName" placeholder="Enter collection name" required />
      <button type="submit">Create Collection</button>
    </form>

    <div v-if="collections.length > 0">
      <h2>Your Collections:</h2>
      <ul>
        <li v-for="collection in collections" :key="collection.id">
          <router-link :to="`/collections/${collection.id}`">
          {{ collection.name }}
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
      collections: [],
    };
  },
  methods: {
    createCollection() {
      if (this.newCollectionName.trim() !== '') {
        const newCollection = {
          id: Math.random().toString(36).substr(2, 9), // Generate a random ID for the collection
          name: this.newCollectionName.trim(),
        };
        this.collections.push(newCollection);
        this.newCollectionName = ''; // Clear the input field after creating a collection
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
</style>
