<template>
  <div>
      <navigation />
      <comic-details />
  </div>
</template>

<script>
import ComicDetails from '../components/ComicDetails.vue'
import Navigation from '../components/Navigation.vue'
export default {
  components: {
    Navigation,
    ComicDetails,
  },

}
</script>

<style>

</style>