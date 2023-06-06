<template>
  <div class="explore-page">
    <h1 class="flashy-text">Explore Comics</h1>
    <router-link to="/bulk-add-comics" class="gold-link">Bulk Add Comics</router-link>
    <div class="image-container">
      <img src="../assets/thing.png" alt="Comic Image" class="bottom-left-image">
      <div class="word-bubble">
        <p class="bubble-text">It's Clobberin' Time!</p>
      </div>
    </div>
  </div>
</template>

<style scoped>
.explore-page {
  position: relative;
  text-align: center;
  min-height: 100vh;
  background-image: url('../../public/comicbackground.jpg') scale;
  background-repeat: no-repeat;
  background-size: cover;
  background-position: center;
}


.explore-page h1 {
  margin-bottom: 20px;
  font-size: 50px;
}

.flashy-text {
  background-image: linear-gradient(to right, gold, silver);
  -webkit-background-clip: text;
  background-clip: text;
  -webkit-text-fill-color: transparent;
  animation: flashyAnimation 1s linear infinite;
}

.gold-link {
  display: block;
  margin-bottom: 10px;
  font-size: 30px;
  color: gold;
  width: 75%;
  margin: 0 auto;
}

.bottom-left-image {
  position: fixed;
  bottom: 100px;
  left: 100px;
  max-width: 200px;
  scale: 3;
}

.word-bubble {
  position: fixed;
  top: 68%;
  left: 20%;
  transform: translate(-50%, -50%);
  background-image: url('../assets/wordbubble.png');
  background-repeat: no-repeat;
  background-position: left;
  background-size: contain;
  width: 225px;
  height: 200px;
}

.bubble-text {
  font-size: 32px;
  text-align: center;
  padding: 20px;
  color: black;
}

@keyframes flashyAnimation {
  0% {
    color: gold;
  }
  50% {
    color: silver;
  }
  100% {
    color: gold;
  }
}

/* Add a media query to hide the images on mobile */
@media (max-width: 600px) {
  .bottom-left-image,
  .word-bubble,
  .top-left-image,
  .top-right-image {
    display: none;
  }
}
</style>