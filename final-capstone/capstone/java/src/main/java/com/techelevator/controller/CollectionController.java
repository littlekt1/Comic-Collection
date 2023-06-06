package com.techelevator.controller;


import com.techelevator.dao.CollectionDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
public class CollectionController {

    @Autowired
    CollectionDao collectionDao;
    @Autowired
    UserDao userDao;


    public CollectionController(CollectionDao collectionDao, UserDao userDao) {
        this.collectionDao = collectionDao;
        this.userDao = userDao;
    }

    //For the Overall Collections Page
    @RequestMapping(path="/collections/public", method = RequestMethod.GET)
    public List<Collection> publicCollections() {
        return collectionDao.getPublicCollections();
    }

    //for the "My Collections" page
    @RequestMapping(path="/collections/user", method = RequestMethod.GET)
    public List<Collection> userCollections(Principal principal) {
        int userId = getUserId(principal);
        return collectionDao.getUserCollections(userId);
    }

    //for when you click on a collection
    @RequestMapping(path = "/collections/{id}", method = RequestMethod.GET)
    public Collection userCollection(@PathVariable int id, Principal principal) {
        int userId = getUserId(principal);
        Collection returnedCollection = collectionDao.getCollectionById(id);
        //if userId = userId from username, or if collection is public
        if (userId == returnedCollection.getOwnerId() || returnedCollection.isPublic()) {
            return returnedCollection;
        }
        //happyPath, return to if time
        return null;
    }

    //for creating a collection
    @RequestMapping(path="/add-collection", method = RequestMethod.POST)
    public Collection addCollection(@RequestBody Collection newCollection, Principal principal) {
        int userId = getUserId(principal);
        newCollection.setOwnerId(userId);
        int returnedId = collectionDao.addCollection(newCollection);
        return collectionDao.getCollectionById(returnedId);
    }

    @RequestMapping(path = "/edit-collection", method = RequestMethod.PUT)
    public Collection editCollection(@RequestBody Collection editCollection) {
        int collectionID = editCollection.getCollectionId();
        int returnedCollectionId = collectionDao.editCollection(editCollection);
        return collectionDao.getCollectionById(returnedCollectionId);
    }

    @RequestMapping(path = "/remove-collection/{id}", method = RequestMethod.DELETE)
    public boolean deleteCollection(@PathVariable int id) {
        int deletedRows = collectionDao.deleteCollection(id);
        return (deletedRows == 1);
    }


    //comic editing

    @RequestMapping(path = "/add-comic/{collectionId}/{comicId}", method = RequestMethod.PUT)
    public int addComic(@PathVariable int collectionId, @PathVariable int comicId) {
        return collectionDao.addComic(comicId, collectionId);
    }

    @RequestMapping(path = "/remove-comic/{collectionId}/{comicId}", method = RequestMethod.PUT)
    public boolean removeComic(@PathVariable int collectionId, @PathVariable int comicId) {
        return (collectionDao.removeComic(comicId, collectionId) == 1);
    }


    private int getUserId(Principal principal) {
        return userDao.findIdByUsername(principal.getName());
    }
}
