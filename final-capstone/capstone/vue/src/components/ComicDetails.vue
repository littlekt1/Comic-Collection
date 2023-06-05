<template>
  <div class="comic">

      <div id="cover">
        <img src="../../public/galactuspanel.jpg" />
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
import marvelService from '../services/MetronService.js'
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
    marvelService.get(this.$route.params.id).then(response => {
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