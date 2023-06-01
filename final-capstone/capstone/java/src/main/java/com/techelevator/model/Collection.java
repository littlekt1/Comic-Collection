package com.techelevator.model;


import java.util.List;

public class Collection {

    private int collectionId;
    private String collectionName;
    private int ownerId;
    private List<Integer> comicIdInCollection;
    private boolean isPublic = true;

    public Collection() {

    }

    public Collection(int collectionId, String collectionName, int ownerId, List<Integer> comicsInCollection, boolean isPublic) {
        this.collectionId = collectionId;
        this.collectionName = collectionName;
        this.ownerId = ownerId;
        this.comicIdInCollection = comicsInCollection;
        this.isPublic = isPublic;
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

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public List<Integer> getComicsInCollection() {
        return comicIdInCollection;
    }

    public void setComicsInCollection(List<Integer> comicsInCollection) {
        this.comicIdInCollection = comicsInCollection;
    }

    public void addComicToCollection(int comicId) {
        this.comicIdInCollection.add(comicId);
    }

    public void removeComicFromCollection(int comicId) {
        if (this.comicIdInCollection.contains(comicId)) {
            //this works! better ways to do it, but it works as is
            for(int i=0; i<this.comicIdInCollection.size(); i++) {
                if (this.comicIdInCollection.get(i) == comicId) {
                    this.comicIdInCollection.remove(i);
                }
            }
        } else {
            System.out.println("Comic not found, something went wrong");
        }
    }

    public boolean isPublic() {
        return this.isPublic;
    }

    public void setPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }

}
