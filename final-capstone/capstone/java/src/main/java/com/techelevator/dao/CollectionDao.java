package com.techelevator.dao;

import com.techelevator.model.Collection;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public interface CollectionDao {

    public List<Collection> getPublicCollections();

    public List<Collection> getUserCollections(int userId);

    public Collection getCollectionById(int collectionId);

    public int addCollection(Collection collectionToAdd);

    public int editCollection(Collection collectionToUpdate);

    public int deleteCollection(int id);

    public int addComic(int comicId, int collectionId);

    public int removeComic(int comicId, int collectionId);
}
