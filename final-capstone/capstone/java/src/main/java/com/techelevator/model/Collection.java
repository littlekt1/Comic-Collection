package com.techelevator.model;


import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Collection {

    private int collectionId;
    private String collectionName;
    private int ownerId;
    private int[] comicIdInCollection;
    private boolean isPublic;

    public Collection() {

    }

    public Collection(int collectionId, String collectionName, int ownerId, int[] comicsInCollection, boolean isPublic) {
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

    public int[] getComicsInCollection() {
        return comicIdInCollection;
    }

    public void setComicsInCollection(int[] comicsInCollection) {
        this.comicIdInCollection = comicsInCollection;
    }

    public void addComicToCollection(int comicId) {
        int[] extendedArray = Arrays.copyOf(comicIdInCollection, comicIdInCollection.length+1);
        extendedArray[extendedArray.length-1] = comicId;
        this.comicIdInCollection = extendedArray;
    }

    public void removeComicFromCollection(int comicId) {
        int[] newArray = new int[this.comicIdInCollection.length-1];
        for (int i = 0, k=0; i < this.comicIdInCollection.length; i++) {
            if (this.comicIdInCollection[i] != comicId) {
                newArray[k] = this.comicIdInCollection[i];
                k++;
//                int finalI = i;
//                this.comicIdInCollection = IntStream.range(0, this.comicIdInCollection.length)
//                        .filter(k -> k != finalI)
//                        .map(k -> this.comicIdInCollection[k])
//                        .toArray(Integer[]::new);
            }
        }
        this.comicIdInCollection = newArray;
    }

    public boolean isPublic() {
        return this.isPublic;
    }

    public void setPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }

}
