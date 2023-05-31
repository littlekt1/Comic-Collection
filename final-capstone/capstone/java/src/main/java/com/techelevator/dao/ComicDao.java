package com.techelevator.dao;

import com.techelevator.model.Collection;
import com.techelevator.model.Comic;

import java.util.List;

public interface ComicDao {

    List<Comic> getAllComics();

    Comic getComic(int id);

    void addComic(Comic comicToAdd);

    void editComic(Comic comicToUpdate);

    void deleteComic(int id);


}
