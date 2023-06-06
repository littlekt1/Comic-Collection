<template>
  <div class="explore-page">
    <h1>Explore Comics</h1>

    <!-- Search Bar -->
    <div class="search-bar">
      <input type="text" v-model="searchQuery" placeholder="Search by name, author, or character" />
      <button @click="searchComics">Search</button>
    </div>

    <!-- Comic Grid -->
    <div class="comic-grid">
      <div v-for="comic in searchResults" :key="comic.id" class="comic-item">
        <img src="https://via.placeholder.com/200" alt="Placeholder Image" />
        <p>{{ comic.name }}</p>
        <p>Author: {{ comic.author }}</p>
        <p>Characters: {{ comic.characters.join(', ') }}</p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      searchQuery: '',
      searchResults: [],
      comics: [
        { id: 1, name: 'Comic A', author: 'Author A', characters: ['Superman', 'Spider-Man'] },
        { id: 2, name: 'Comic B', author: 'Author B', characters: ['Batman', 'Superman'] },
        { id: 3, name: 'Comic C', author: 'Author C', characters: ['Spider-Man', 'Wonder Woman'] },
        // Add more comics as needed
      ],
    };
  },
  methods: {
   searchComics() {
  const query = this.searchQuery.trim().toLowerCase();

  if (query === '') {
    this.searchResults = [];
    return;
  }

  // Filter the comics based on the search query
  this.searchResults = this.comics.filter((comic) => {
    const comicName = comic.name.toLowerCase();
    const author = comic.author.toLowerCase();
    const characters = comic.characters.map((character) => character.toLowerCase());

    // Check if the search query matches the comic name, author, or any character
    return (
      comicName.includes(query) ||
      author.includes(query) ||
      characters.some((character) => character.includes(query))
    );
  });
    },
  },
};
</script>

<style scoped>
.explore-page {
  text-align: center;
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
