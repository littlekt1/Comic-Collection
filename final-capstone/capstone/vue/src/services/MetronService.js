import axios from 'axios';

export default {
    get(characterName) {
        return axios.get(`/comic-character?comicSearch=${characterName}`)
    },

    getComicById(comicId) {
        return axios.get(`/comic-issue?issueSearch=${comicId}`)
    },
    getRandomComics() {
        return axios.get(`/get-random`)
    }
}