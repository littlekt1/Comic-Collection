<template>
  <div class="trending-comics">
    <h3>Check out the currently trending comics:</h3>
    <div class="comics-container">
      <div v-for="(comic, index) in comics" :key="index" class="comic-item">
        <img :src="comic.image" :alt="'Comic ' + (index + 1)" />
        <p>{{ comic.description }}</p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'TrendingComics',
  data() {
    return {
      comics: [
        {
          image: 'comic1.jpg',
          description: 'Comic 1 description',
        },
        {
          image: 'comic2.jpg',
          description: 'Comic 2 description',
        },
        {
          image: 'comic3.jpg',
          description: 'Comic 3 description',
        },
        {
          image: 'comic4.jpg',
          description: 'Comic 4 description',
        },
      ],
    };
  },
};
</script>

<style scoped>

.comics-container {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 10px;
}

.comic-item {
  display: flex;
  flex-direction: column;
  align-items: center;
  text-align: center;
}

.comic-item img {
  width: 200px;
  height: 300px;
}

.comic-item p {
  margin-top: 10px;
}
</style>