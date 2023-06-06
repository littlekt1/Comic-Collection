package com.techelevator.dao;

import com.techelevator.model.Collection;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
@Component
public class CollectionJdbcDao implements CollectionDao {

    private final JdbcTemplate template;

    public CollectionJdbcDao(DataSource dataSource) {
        template = new JdbcTemplate((dataSource));
    }

    //Start Collection Modification or collecting

    @Override
    public List<Collection> getPublicCollections() {
        String sql = "SELECT * FROM collections WHERE isPublic = true";
        List<Collection> collectionList = new ArrayList<>();
        SqlRowSet results = template.queryForRowSet(sql);

        while(results.next()) {
            Collection collection = mapRowToCollection(results);
            collectionList.add(collection);
        }
        return collectionList;
    }


    @Override
    public List<Collection> getUserCollections(int userId) {
        String sql = "SELECT * FROM collections WHERE owner_id = ?";
        List<Collection> collectionList = new ArrayList<>();
        SqlRowSet results = template.queryForRowSet(sql, userId);

        while(results.next()) {
            Collection collection = mapRowToCollection(results);
            collectionList.add(collection);
        }
        return collectionList;
    }

    @Override
    public Collection getCollectionById(int collectionId) {
        String sql = "SELECT * FROM collections WHERE collection_id = ?";
        SqlRowSet results = template.queryForRowSet(sql, collectionId);
        if (results.next()) {
            return mapRowToCollection(results);
        }
        return null;
    }

    @Override
    public int addCollection(Collection collectionToAdd) {
        String name = collectionToAdd.getCollectionName();
        int ownerId = collectionToAdd.getOwnerId();
        boolean isPublic = collectionToAdd.isPublic();
        List<Integer> comicIds = collectionToAdd.getComicsInCollection();

        String sql = "INSERT INTO collection(collection_name, owner_id, comics_in_collection, visible) VALUES ?, ?, ?, ? RETURNING collection_id)";
        return template.update(sql, name, ownerId, comicIds, isPublic);
    }

    @Override
    public int editCollection(Collection collectionToUpdate) {
        String sql = "UPDATE collections SET collection_name = ?, owner_id = ?, comics_in_collection = ?,  visible = ? WHERE collection_id = ? RETURNING collection_id";
        return template.update(sql, collectionToUpdate.getCollectionName(), collectionToUpdate.getOwnerId(), collectionToUpdate.getComicsInCollection(), collectionToUpdate.isPublic(),
                collectionToUpdate.getCollectionId());

    }

    @Override
    public int deleteCollection(int collectionId) {
        String sql = "DELETE * FROM collections WHERE collection_id = ?";
        return template.update(sql, collectionId);
    }


    //END

    //START comic-id-list mod

    @Override
    public int addComic(int comicId, int collectionId) {
            String sql = "UPDATE collections SET comics_in_collection = ARRAY_APPEND(comics_in_collection, ?)  WHERE collection_id = ?";
        return template.update(sql, comicId, collectionId);
    }

    @Override
    public int removeComic(int comicId, int collectionId) {
        String sql = "UPDATE collections SET comics_in_collection = ARRAY_REMOVE(comics_in_collection, ?)  WHERE collection_id = ?";
        return template.update(sql, comicId, collectionId);
    }


    //END

    private Collection mapRowToCollection(SqlRowSet rowSet) {
        Collection collection = new Collection();
        collection.setCollectionId(rowSet.getInt("collection_id"));
        collection.setOwnerId(rowSet.getInt("owner_id"));
        collection.setPublic(rowSet.getBoolean("visible"));
        collection.setCollectionName(rowSet.getString("collection_name"));
        collection.setComicsInCollection((List<Integer>) rowSet.getObject("comics_in_collection"));
        return collection;
    }
}
