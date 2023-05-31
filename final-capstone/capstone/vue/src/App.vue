<template>
  <div id="app">
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
    </div>
    <router-view />
  </div>
</template>

<style>
html{
  font-family: 'Bangers', cursive;
  background-color: #24231E;
  color: #AEAA46;
  background-image: url('../public/golddots.jpg');
  background-position: center;
  background-size: 100%;
}
</style>
