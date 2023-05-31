package com.techelevator.model;


import java.util.List;

public class Collection {

    private int collectionId;
    private String collectionName;
    private List<Comic> comicsInCollection;

    public Collection() {

    }

    public Collection(int collectionId, String collectionName, List<Comic> comicsInCollection) {
        this.collectionId = collectionId;
        this.collectionName = collectionName;
        this.comicsInCollection = comicsInCollection;
    }

    public int getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(int collectionId) {
        this.collectionId = collectionId;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public List<Comic> getComicsInCollection() {
        return comicsInCollection;
    }

    public void setComicsInCollection(List<Comic> comicsInCollection) {
        this.comicsInCollection = comicsInCollection;
    }
}
