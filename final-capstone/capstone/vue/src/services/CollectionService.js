import axios from 'axios';

export default {
    getPublicCollections() {
        return axios.get('/collections/public')
    },

    getUserCollections() {
        return axios.get('/collections/user');
    },

    getCollection(id) {
        return axios.get(`/collections/${id}`);
    },

    createCollection(collection) {
        return axios.post('/add-collection', collection)
    },

    editCollection(collection) {
        return axios.put('/edit-collection', collection)
    },

    removeCollection(id) {
        return axios.delete(`/remove-collection/${id}`);
    },

    addComic(collectionId, comicId) {
        return axios.put(`/add-comic/${collectionId}/${comicId}`);
    },

    removeComic(collectionId, comicId) {
        return axios.put(`/remove-comic/${collectionId}/${comicId}`);
    },
}