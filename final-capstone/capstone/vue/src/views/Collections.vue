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
          {{ collection.name }}
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
      collections: [
            { id: '1', name: 'Collection 1' },
      { id: '2', name: 'Collection 2' },
      { id: '3', name: 'Collection 3' },
      ],
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
  max-width: 600px;
  margin: 0 auto;
  padding: 2rem;
}
</style>
