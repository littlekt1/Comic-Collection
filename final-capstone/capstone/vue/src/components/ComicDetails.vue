<template>
  <div >
    <div class="comic" v-show="!isLoading">
      <!-- section for the cover image -->
      <div id="cover">
        <img :src="comic.thumbnail" />
        <h1>Created: {{comic.date}}</h1>
      </div>

      <!-- section for adding the comic to a collection -->
      <div id="collectionAdd">
        <h2>Select a Collection to add this Comic to!</h2>


        <div id="list1" class="dropdown-check-list" tabindex="100">
          <span class="anchor" v-on:click="toggleCheckboxList()">Select Collection</span>
          <ul class="items">
            <!-- <li><input type="checkbox" />Apple </li> -->
            <li v-for="collection in collections" :key="collection.collectionId" class="collectionCheckbox">
              <input type="checkbox" :id="collection.collectionName" v-on:click="toggleComic(collection)"/> {{collection.collectionName}}
            </li>
          </ul>
        </div>
<!--         
        <form>
          <div v-for="collection in collections" :key="collection.collectionId" class="collectionCheckbox">
            <input type="checkbox" :id="collection.collectionName" :name="collection.collectionName" v-on:click="toggleComic(collection)">
            <label :for="collection.collectionName">Add to {{collection.collectionName}}</label>
          </div>
        </form> -->
      </div>
      <div id="info">
        <h1 id="title">{{comic.name[0]}}</h1>
        <h2 id="series">From the Series, {{comic.series.name}}</h2>
        <p id="description">{{comic.description}}</p>
        
      </div>
      <div>
        <h2 id="character-list">Character List</h2>
        <ul>
          <li v-for="character in comic.characters" :key="character.id" class="character-list-items">{{character.name}}</li>
        </ul>
      </div>
    </div>
    <div class="loading" v-show="isLoading">
      <p>Now Loading...</p>
      <img src="../assets/loading.gif" alt="">
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
        name: [],
        description: '',
        series: {},
        date: '',
        price: '',
        thumbnail: '',
        characters: []
      },
      isLoading: false,
      collections: [],
    }
    
  },
  created() {
    this.isLoading = true;
    CollectionService.getUserCollections().then(response => {
      this.collections = response.data;
      
    });
    

    metronService.getComicById(this.$route.params.id).then(response => {
      
      this.comic.id = response.data.id;
      this.comic.name = response.data.name;
      this.comic.description = response.data.desc;
      this.comic.series = response.data.series;
      this.comic.date = response.data.cover_date;
      this.comic.price = response.data.price;
      this.comic.thumbnail = response.data.image;
      this.comic.characters = response.data.characters;
      this.initCheckbox()
      this.isLoading = false;
    })
  },
  methods: {
    initCheckbox() {
      //if the collection contains the comic, we want it to have the checkbox checked.
      this.collections.forEach(collection => {
        if (collection.comicsInCollection.includes(this.comic.id)) {
          document.getElementById(collection.collectionName).checked = true;
        }
      });
    },
    toggleComic(collection) {
      //this is where we can check for the collection having a limit on number of comics
      //if the user clicks the checkbox, mark it as checked and add the current comic to the collection marked
      if(document.getElementById(collection.collectionName).checked) {
        CollectionService.addComic(collection.collectionId, this.comic.id);
      } else {
        CollectionService.removeComic(collection.collectionId, this.comic.id);
      }
    },
    toggleCheckboxList() {
        let checkList = document.getElementById('list1');
        if (checkList.classList.contains('visible'))
          checkList.classList.remove('visible');
        else
          checkList.classList.add('visible');
      },

  },
  computed: {
    
  }
}
</script>


<style scoped>

.comic {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  justify-items: center;
  
}

.loading {
  margin-left: 50%;
}

#cover img {
  width: 80%;
  border-radius: 10%;
  border: 7px solid white;
}

#cover{
  text-align: center;
}

#title,  #character-list{
  font-size: 4rem;
}

#description, .character-list-items {
  font-size: 1.7rem;
}

.dropdown-check-list {
  display: inline-block;
}

.dropdown-check-list .anchor {
  position: relative;
  cursor: pointer;
  display: inline-block;
  padding: 5px 50px 5px 10px;
  border: 1px solid #ccc;
}

.dropdown-check-list .anchor:after {
  position: absolute;
  content: "";
  border-left: 2px solid black;
  border-top: 2px solid black;
  padding: 5px;
  right: 10px;
  top: 20%;
  -moz-transform: rotate(-135deg);
  -ms-transform: rotate(-135deg);
  -o-transform: rotate(-135deg);
  -webkit-transform: rotate(-135deg);
  transform: rotate(-135deg);
}

.dropdown-check-list .anchor:active:after {
  right: 8px;
  top: 21%;
}

.dropdown-check-list ul.items {
  padding: 2px;
  display: none;
  margin: 0;
  border: 1px solid #ccc;
  border-top: none;
}

.dropdown-check-list ul.items li {
  list-style: none;
}

.dropdown-check-list.visible .anchor {
  color: #0094ff;
}

.dropdown-check-list.visible .items {
  display: block;
}
</style>