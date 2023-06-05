<template>
  <div class="explore-page">
    <h1>Explore Comics</h1>
    <router-link to="/bulk-add-comics">Bulk Add Comics</router-link>
    <!-- Other content of the Explore page -->
  </div>
</template>

<script>
export default {
  // Component logic
};
</script>

<style scoped>
.explore-page {
  text-align: center;
}

.explore-page h1 {
  margin-bottom: 20px;
}

.explore-page router-link {
  display: block;
  margin-bottom: 10px;
}
</style>
