<template>
  <div>
    <div v-show="!isLoading" class="collection-card">
        <img :src="imgSrc">
        <p class="collection-name larger-text"> {{collectionName}} </p>
    </div>
    <div v-show="isLoading">
      <img src="../../public/collectioncover.jpg" alt="">
      <p>Now Loading...</p>
    </div>
  </div>
</template>

<script>
import CollectionService from '../services/CollectionService.js';
import metronService from '../services/MetronService.js'

export default {
    props: {
        id: Number
    },
    data() {
        return {
            imgSrc: '',
            collectionName: '',
            comicsIds: [],
            isLoading: false,
        }
    },
    methods: {
        getCollectionInfo(collectionId) {
            this.isLoading = true;
            CollectionService.getCollection(collectionId).then(response => {
                this.collectionName = response.data.collectionName;

                this.comicsIds = response.data.comicsInCollection;
                if (this.comicsIds.length > 0) {
                    console.log(this.collectionName)
                    this.getComicInfoFromMetron(this.comicsIds[0]);
                } else {
                    this.imgSrc = "../../public/collectioncover.jpg";
                    
                    
                }
                this.isLoading = false;
            })
        },
        getComicInfoFromMetron(comicId) {
            this.isLoading = true;
            metronService.getComicById(comicId).then(response => {
            this.imgSrc = response.data.image;
            this.isLoading = false;
        })
        },
    },
    created() {
        this.getCollectionInfo(this.id);

    }
}
</script>

<style>

.collection-card {
  text-align: center;
}

.collection-card img {
  width: 150px; /* Adjust the width as needed */
  height: auto; /* Maintain aspect ratio */
  margin-bottom: 10px;
}

.collection-name {
  font-weight: bold;
}

img {
  width: 150px;
  height: 200px;
  object-fit: cover;
}
</style>