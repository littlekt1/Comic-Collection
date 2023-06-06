import axios from 'axios';

export default {
    get(characterName) {
        return axios.get(`/comic-character/${characterName}`)
    }
}