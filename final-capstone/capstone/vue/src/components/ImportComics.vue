<template>
  <div class="import-comics">
    <h1>Import Comics</h1>
    <form @submit.prevent="importComics">
      <input type="file" ref="fileInput" accept=".csv, .tsv" />
      <button type="submit">Import Comics</button>
    </form>
    <div v-if="errorMessage" class="error-message">{{ errorMessage }}</div>
  </div>
</template>

<script>
export default {
  methods: {
    importComics() {
      const file = this.$refs.fileInput.files[0];
      if (file) {
        this.parseFile(file);
      } else {
        this.errorMessage = 'Please select a file.';
      }
    },
    parseFile(file) {
      const reader = new FileReader();
      reader.onload = (event) => {
        const contents = event.target.result;
        const comicsArray = this.parseComicsData(contents);
        if (comicsArray.length > 0) {
          // Add comics to the collection
          // Modify the code as per your requirement to add comics to the collection
          console.log('Importing comics:', comicsArray);
          // Reset the input field
          this.$refs.fileInput.value = '';
          this.errorMessage = '';
        } else {
          this.errorMessage = 'Invalid comics data. Please check the format and try again.';
        }
      };
      reader.readAsText(file);
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
.import-comics {
  text-align: center;
}

.import-comics h1 {
  margin-bottom: 20px;
}

.import-comics input[type="file"] {
  margin-bottom: 10px;
}

.import-comics button {
  font-size: 16px;
}

.error-message {
  color: red;
  margin-top: 10px;
}
</style>
