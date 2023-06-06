<template>
  <div class="home">
    <h1 class="header-gradient">Home</h1>
    <p v-if="isAuthenticated">You are authenticated and can see this content.</p>
    <p v-else>You must be authenticated to see this.</p>
    <div class="main-content">
      <div class="section trending-comics">
        <h2>Trending Comics</h2>
        <div class="section-content">
          <div class="carousel-container">
            <TrendingComics />
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
          <p>Total number of comics:</p>
          <p>Your number of collections:</p>
          <p>Superhero appearances:</p>
        </div>
      </div>
    </div>
    <footer class="footer">
      <p>Data from GameSpot © 2023 GAMESPOT</p>
    </footer>
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
  cursor: url('../assets/avengers.png') 10 10, pointer;
}
.home {
  display: flex;
  flex-direction: column;
  text-align: center;
  min-height: 100vh;
  font-display: bold;
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

.trending-comics {
  text-align: center;
}

.trending-comics .section-content {
  display: flex;
  justify-content: center;
}

.carousel-container {
  display: flex;
  justify-content: center;
  max-width: 800px;
  margin: 0 auto;
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

@media (max-width: 767px) {
  .main-content {
    flex-direction: column;
  }

  .section {
    flex-basis: 100%;
  }

  .my-collections {
    margin-top: 20px;
  }

  .comic-item img,
  .collection-item img {
    width: 100%;
    height: auto;
    margin-right: 10px;
  }

  .carousel-container {
    display: flex;
    justify-content: flex-start;
    overflow-x: auto;
    scroll-snap-type: x mandatory;
    -webkit-overflow-scrolling: touch;
    margin: 20px 0;
  }

  .carousel-container::-webkit-scrollbar {
    height: 6px;
  }

  .carousel-container::-webkit-scrollbar-thumb {
    background-color: #888;
    border-radius: 3px;
  }

  .carousel-container::-webkit-scrollbar-track {
    background-color: #f1f1f1;
    border-radius: 3px;
  }
}
</style>