<template>
  <div id="app">
    <navigation />
    <router-view />
  </div>
</template>

<script>
import Navigation from './components/Navigation.vue'
export default {
  components: {
    Navigation,
  }
};
</script>

<style>
html{
  font-family: 'Bangers', cursive;
  background-color: #24231E;
  color: #AEAA46;
  background-image: url('../public/golddots.jpg');
  background-position: center;
  background-size: 120%;
  cursor: url('../public/avengers.png') 10 10, pointer;
}
</style> 
