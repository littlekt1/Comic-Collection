<template>
  <div>
    <div v-show="!isLoading">
      <img :src="imgSrc">
      <p>{{comicName[0]}}</p>
    </div>
    <div v-show="isLoading">
      <img src="../assets/collectioncover.jpg" alt="">
      <p>Now Loading...</p>
    </div>
  </div>
</template>

<script>
import metronService from '../services/MetronService.js'

export default {
    props: {
        id: Number
    },
    data() {
        return {
            imgSrc: '',
            comicName: '',
            isLoading: false,
        }
    },
    methods: {
        getComicInfoFromMetron(comicId) {
            this.isLoading = true;
            metronService.getComicById(comicId).then(response => {
            this.imgSrc = response.data.image;
            this.comicName = response.data.name;
            this.isLoading = false;
        })
        },
    },
    created() {
        this.getComicInfoFromMetron(this.id);
    }
}
</script>

<style>
img {
  width: 150px;
  height: 200px;
  object-fit: cover;
}
</style>