<template>
  <div class="collection-detail">
    <h1>{{ collection.name }}</h1>
    <p>{{ collection.description }}</p>
  </div>
</template>

<script>
export default {
  data() {
    return {
      collection: null,
    };
  },
  created() {
    // Fetch the collection data using an API or store
    // For now, let's assume we receive the collection data as a prop
    this.collection = this.$props.collection;
  },
};
</script>

<style scoped>
.collection-detail {
  text-align: center;
  margin-top: 2rem;
}
</style>
