<template>
  <div id="navbar">
    <img src="../../public/Comic_Haven_Logo_01.png" id="logo"/>
    <router-link v-bind:to="{name: 'home'}">
    <button class="navbutton">Home</button>
    </router-link>
    <router-link v-bind:to="{name: 'collections'}">
    <button class="navbutton">My Collections</button>
    </router-link>
    <router-link v-bind:to="{name: 'explore'}">
    <button class="navbutton">Explore Comics</button>
    </router-link>
  </div>
</template>

<script>
export default {

}
</script>

<style>

#navbar{
    height: 5%;
    width: 100%;
    background-color: #191919;
    display: flex;
    align-items: center;
}

#logo{
    display: inline-block;
    width: 5%;
    min-width: 60px;
    padding-left: 1%;
}

.navbutton {
    background-color: #605C49;
    border-radius: 10px;
    justify-items: flex-end;
    margin: 0px;
}
</style>