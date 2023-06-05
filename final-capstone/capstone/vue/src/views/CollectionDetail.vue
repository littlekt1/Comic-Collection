<template>
  <div class="collection-detail">
    <h1>{{ collection.name }}</h1>
    
    <!-- Add Comic Form -->
    <form @submit.prevent="addComic" class="add-comic-form">
      <input type="text" v-model="newComicName" placeholder="Enter comic name" required />
      <button type="submit">Add Comic</button>
    </form>
    
    <!-- Share Collection URL -->
    <div class="share-collection">
      <p>Share this collection:</p>
      <input type="text" :value="collectionURL" readonly />
    </div>
    
    <!-- Comic Grid -->
    <div class="comic-grid">
      <div v-for="comic in collection.comics" :key="comic.id" class="comic-item">
          <img src="https://m.media-amazon.com/images/I/71GPeneF0rL._AC_UF894,1000_QL80_.jpg" alt="Comic Image" />
        <p>Comic Name</p>
      </div>
    </div>
  </div>
</template>


<script>
export default {
  data() {
    return {
      collection: {
        name: 'Sample Collection',
        comics: [],
      },
      newComicName: '',
    };
  },
  computed: {
    collectionURL() {
      // Get the current route and build the collection URL
      const currentRoute = this.$route.fullPath;
      const collectionID = this.collection.id; // Replace with the actual collection ID
      return `${window.location.origin}${currentRoute}?collectionId=${collectionID}`;
    },
  },
  methods: {
    addComic() {
      if (this.newComicName.trim() !== '') {
        const newComic = {
          id: Math.random().toString(36).substr(2, 9), // Generate a random ID for the comic
          name: this.newComicName.trim(),
        };
        this.collection.comics.push(newComic);
        this.newComicName = ''; // Clear the input field after adding a comic
      }
    },
  },
};
</script>

<style scoped>
.collection-detail {
  text-align: center;
}

.add-comic-form {
  margin-bottom: 20px;
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
  width: 100%;
  max-height: 200px;
  object-fit: cover;
}
</style>
