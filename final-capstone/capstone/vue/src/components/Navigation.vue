<template>
  <div id="navbar">
    <img src="../../public/logo.png" id="logo" />


    <h1 id="title">Comic Haven</h1>


    <div class="navbuttons">
      <router-link :to="{ name: 'home' }">
        <button class="navbtn">Home</button>
      </router-link>

      <router-link :to="{ name: 'collections' }">
        <button class="navbtn">My Collections</button>
      </router-link>

      <router-link :to="{ name: 'explore' }">
        <button class="navbtn">Explore Comics</button>
      </router-link>

      <router-link v-if="loginCheck" :to="{ name: 'logout' }">
        <button class="navbtn">Log out</button>
      </router-link>

      <router-link v-else :to="{ name: 'login' }">
        <button class="navbtn">Log In</button>
      </router-link>
    </div>
  </div>
</template>

<script>
export default {
  computed: {
    loginCheck() {
      return !!this.$store.state.token; 
    },
  },
};
</script>

<style scoped>

#navbar{
    display: flex;
    height: 5%;
    width: 100%;
    background-color: #191919;
    flex-direction: row;
    align-items: center;
}

#logo{
    width: 5%;
    min-width: 70px;
    padding-left: 1%;
}

#title {
  padding: 10px;
}

.navbuttons {
  display: flex;
  height: max-content;
  flex-grow: 2;
  align-items: center;
  justify-content: flex-end;
  text-align: center;
}

.navbtn {
    font-family: inherit;
    color: #AEAA46;
    font-size: 1.2rem;
    background-color: #605C49;
    border-radius: 10px;
    height: 5rem;
    margin: 1rem;
    padding: 20px;
}

@media only screen and (max-width: 600px) {
  .navbtn {
    height: 5rem;
    margin: .8rem;
    padding: 10px;
  }

  #navbar{
    height: 3%;
    width: 100%;
  }
}
</style>