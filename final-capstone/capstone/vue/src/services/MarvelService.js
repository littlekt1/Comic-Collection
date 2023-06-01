import axios from 'axios';

export default {
    get(comicID) {
        return axios.get(`https://gateway.marvel.com//v1/public/comics/${comicID}`)
    }
}