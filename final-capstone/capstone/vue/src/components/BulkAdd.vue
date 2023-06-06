<template>
  <div class="bulk-add-comics">
    <h1>Bulk Add Comics</h1>
    <form @submit.prevent="addComics">
      <textarea v-model="comicsData" placeholder="Paste CSV/TSV data here"></textarea>
      <button type="submit">Add Comics</button>
    </form>
    <div v-if="errorMessage" class="error-message">{{ errorMessage }}</div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      comicsData: '',
      errorMessage: '',
    };
  },
  methods: {
    addComics() {
      // Parse the CSV/TSV data and add comics
      const comicsArray = this.parseComicsData(this.comicsData);
      if (comicsArray.length > 0) {
        // Add comics to the collection
        // Modify the code as per your requirement to add comics to the collection
        console.log('Adding comics:', comicsArray);
        // Reset the input field
        this.comicsData = '';
        this.errorMessage = '';
      } else {
        this.errorMessage = 'Invalid comics data. Please check the format and try again.';
      }
    },
    parseComicsData(data) {
      // Parse the CSV/TSV data into an array of comics
      const lines = data.split('\n');
      const comicsArray = [];
      for (let i = 0; i < lines.length; i++) {
        const comicData = lines[i].split('\t'); // Change '\t' to ',' if using CSV format
        if (comicData.length === 2) {
          const id = Math.random().toString(36).substr(2, 9);
          const name = comicData[0].trim();
          const imageUrl = comicData[1].trim();
          const comic = { id, name, imageUrl };
          comicsArray.push(comic);
        }
      }
      return comicsArray;
    },
  },
};
</script>

<style scoped>
.bulk-add-comics {
  text-align: center;
}

.bulk-add-comics h1 {
  margin-bottom: 20px;
}

.bulk-add-comics textarea {
  width: 100%;
  height: 200px;
  resize: vertical;
  margin-bottom: 10px;
}

.bulk-add-comics button {
  font-size: 16px;
}

.error-message {
  color: red;
  margin-top: 10px;
}
</style>
