<template>
  <div class="home">
    <div class="custom-cursor">
      <h1>Home</h1>
      <p v-if="isAuthenticated">You are authenticated and can see this content.</p>
      <p v-else>You must be authenticated to see this.</p>
      <div class="main-content">
        <div class="section trending-comics">
          <h2>Trending Comics</h2>
          <div class="section-content"> 
            <TrendingComics />
            <div class="comic-item" v-for="comic in trendingComics" :key="comic.id">
              <img :src="comic.image" :alt="comic.title" />
              <p>{{ comic.description }}</p>
            </div>
          </div>
        </div>
        <div class="section my-collections">
          <h2>My Top 5 Collections</h2>
          <div class="section-content">
            <div class="collection-item">
              <img src="collection1.jpg" alt="Collection 1" />
              <p>Collection 1 description</p>
            </div>
            <div class="collection-item">
              <img src="collection2.jpg" alt="Collection 2" />
              <p>Collection 2 description</p>
            </div>
          </div>
        </div>
        <div class="section aggregate-stats">
          <h2>Aggregate Stats</h2>
          <div class="section-content">
            <p>Total number of comics: 1000</p>
            <p>Total number of collections: 50</p>
            <p>Average rating: 4.5</p>
          </div>
        </div>
      </div>
      <footer class="footer">
        <p>Data from GameSpot © 2023 GAMESPOT</p>
      </footer>
    </div>
  </div>
</template>

<script>

import TrendingComics from '../components/TrendingComics.vue';

export default {
  components: {
    TrendingComics,
  },
  name: 'Home',
  data() {
    return {
      isAuthenticated: false,
    };
  },
  mounted() {
    this.isAuthenticated = true; 
  },
};
</script>

<style scoped>
.custom-cursor {
  cursor: url('../../public/avengers.png') 10 10, pointer;
 
}
.home {
  display: flex;
  flex-direction: column;
  text-align: center;
  min-height: 100vh;

}
.main-content {
  flex: 1;
  display: flex;
  justify-content: space-between;
  margin-top: 20px;
  
}
.footer {
  text-align: center;
  border: 2px solid gray; 
  border-radius: 10px; 
  margin: 10px;
  background-color: rgba(150, 150, 150, 0.568);
  z-index: 1;
}

.section {
  flex-basis: 45%;
  margin: 0 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
  padding: 10px;
  border: 2px solid gray; 
  border-radius: 10px; 
  margin: 10px;
  background-color: rgba(0, 0, 0, 0.568);
  z-index: 1;
}
.tending-comics {
  flex-basis: 48%
  
}

.my-collections {
  flex-basis: 37%;
}

.aggregate-stats {
  flex-basis: 15%;
}
.section-content {
  margin-top: 10px;
}

.comic-item,
.collection-item {
  display: flex;
  align-items: center;
  margin-bottom: 10px;
}

.comic-item img,
.collection-item img {
  width: 50px;
  height: 50px;
  margin-right: 10px;
}


</style>