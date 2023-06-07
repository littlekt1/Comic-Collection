<template>
  <div>

        <img :src="imgSrc">

    <p>{{comicName[0]}}</p>

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
        }
    },
    methods: {
        getComicInfoFromMetron(comicId) {
            metronService.getComicById(comicId).then(response => {
            this.imgSrc = response.data.image;
            this.comicName = response.data.name
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