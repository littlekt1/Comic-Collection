import axios from 'axios';

export default {
    get(characterName) {
        //localhost:9000/comic-character?comicSearch=hulk
        console.log(characterName);
        return axios.get(`/comic-character?comicSearch=${characterName}`)
    }
}