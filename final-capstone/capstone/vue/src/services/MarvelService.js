import axios from 'axios';

export default {
    get(comicID) {
        return axios.get(`/comic/${comicID}`)
    }
}