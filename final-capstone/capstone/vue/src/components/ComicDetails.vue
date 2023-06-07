<template>
  <div class="comic">


      <!-- section for adding the comic to a collection -->
      <div id="collectionAdd">
        <h2>Select a Collection to add this Comic to!</h2>
        <form>
          <div v-for="collection in collections" :key="collection.collectionId" class="collectionCheckbox">
            <input type="checkbox" v-on:click="toggleComic(collection)">
            <label>Add to {{collection.collectionName}}</label>
          </div>
        </form>
      </div>
      <div id="info">
        <h1 id="title">Test Title <!-- {{comic.title}}--></h1>
        <p id="description">Test Description</p>
        
      </div>
      <div>
        <h2 id="character-list">Character List</h2>
        <ul>
          <li class="character-list-items">Character</li>
          <li class="character-list-items">Character</li>
          <li class="character-list-items">Character</li>
          <li class="character-list-items">Character</li>
        </ul>
      </div>
  </div>
</template>

<script>
import metronService from '../services/MetronService.js'
import CollectionService from '../services/CollectionService.js'
export default {
  data() {
    return {
      comic: {
        id: '',
        title: '',
        description: '',
        series: '',
        dates: [],
        prices: [],
        thumbnail: '',
        characters: ''
      },
      isLoading: true,
    }
    
  },
  created() {

    CollectionService.getUserCollections().then(response => {
      this.collections = response.data;
    }),
    metronService.getComic(this.$route.params.id).then(response => {

      this.comic.id = response.data.id;
      this.comic.title = response.data.title;
      this.comic.description = response.data.description;
      this.comic.series = response.data.series;
      this.comic.dates = response.data.dates;
      this.comic.prices = response.data.prices;
      this.comic.thumbnail = response.data.thumbnail;
      this.comic.characters = response.data.characters;
      this.isLoading = false;
    })
  },
  methods: {
    //if the collection contains the comic, we want it to have the checkbox checked.
    toggleComic(collection) {
      //this is where we can check for the collection having a limit on number of comics
      //if the user clicks the checkbox, mark it as checked and add the current comic to the collection marked
      return collection;
    }

  },
  computed: {
    
  }
}
</script>

<style scoped>

.comic {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  justify-items: center;
  
}

#cover img {
  width: 80%;
  border-radius: 10%;
  border: 7px solid white;
}

#title,  #character-list{
  font-size: 4rem;
}

#description, .character-list-items {
  font-size: 1.7rem;
}

</style>
