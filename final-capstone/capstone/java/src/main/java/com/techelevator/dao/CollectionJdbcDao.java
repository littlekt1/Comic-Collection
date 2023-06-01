package com.techelevator.dao;

import com.techelevator.model.Collection;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class CollectionJdbcDao implements CollectionDao {

    private final JdbcTemplate template;

    public CollectionJdbcDao(DataSource dataSource) {
        template = new JdbcTemplate((dataSource));
    }

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
    public void addCollection(Collection collectionToAdd) {
        String name = collectionToAdd.getCollectionName();
        int ownerId = collectionToAdd.getOwnerId();
        boolean isPublic = collectionToAdd.isPublic();
        List<Integer> comicIds = collectionToAdd.getComicsInCollection();

        String sql = "INSERT INTO collection(collection_name, owner_id, comics_in_collection, visible) VALUES ?, ?, ?, ?)";
        template.update(sql, name, ownerId, comicIds, isPublic);
    }

    @Override
    public void editCollection(Collection collectionToUpdate) {
        String sql = "UPDATE collections SET collection_name = ?, owner_id = ?, comics_in_collection = ?,  visible = ? WHERE collection_id = ?";
        template.update(sql, collectionToUpdate.getCollectionName(), collectionToUpdate.getOwnerId(), collectionToUpdate.getComicsInCollection(), collectionToUpdate.isPublic(),
                collectionToUpdate.getCollectionId());

    }

    @Override
    public void deleteCollection(int collectionId) {
        String sql = "DELETE * FROM collections WHERE collection_id = ?";
        template.update(sql, collectionId);
    }

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
