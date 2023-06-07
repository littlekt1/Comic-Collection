package com.techelevator.dao;

import com.techelevator.model.Collection;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import javax.sql.rowset.serial.SerialArray;
import javax.sql.rowset.serial.SerialException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
//<<<<<<< HEAD
//=======
//
//>>>>>>> main
@Component
public class CollectionJdbcDao implements CollectionDao {

    private final JdbcTemplate template;

    public CollectionJdbcDao(DataSource dataSource) {
        template = new JdbcTemplate((dataSource));
    }

    //Start Collection Modification or collecting

    @Override
    public List<Collection> getPublicCollections() {
        String sql = "SELECT * FROM collections WHERE visible = true";
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
        int[] comicIds = collectionToAdd.getComicsInCollection();

        String sql = "INSERT INTO collections (collection_name, owner_id, comics_in_collection, visible) VALUES (?, ?, ?, ?) RETURNING collection_id";
        return template.queryForObject(sql, Integer.class, name, ownerId, comicIds, isPublic);
    }

    @Override
    public int editCollection(Collection collectionToUpdate) {
        String name = collectionToUpdate.getCollectionName();
        int ownerId = collectionToUpdate.getOwnerId();
        boolean isPublic = collectionToUpdate.isPublic();
        int[] comicIds = collectionToUpdate.getComicsInCollection();
        int collectionId = collectionToUpdate.getCollectionId();
        String sql = "UPDATE collections SET collection_name = ?, owner_id = ?, comics_in_collection = ?,  visible = ? WHERE collection_id = ? RETURNING collection_id";
        return template.queryForObject(sql, Integer.class, name, ownerId, comicIds, isPublic, collectionId);

    }

    @Override
    public int deleteCollection(int collectionId) {
        String sql = "DELETE FROM collections WHERE collection_id = ?";
        return template.update(sql, collectionId);
    }


    //END
    public int getCharacterStatsCount(String characterName, int userId) {
        String lowerCaseCharacterName = characterName.toLowerCase(); // Convert the character name to lowercase
        String sql = "SELECT COUNT(*) FROM collections " +
                "WHERE owner_id = ? " +
                "AND LOWER(comics_in_collection::text) LIKE '%' || LOWER(?::text) || '%'";
        return template.queryForObject(sql, Integer.class, userId, lowerCaseCharacterName);
    }
    //should take in character name
    // loop through the collection data to get character name, get comic detail data by character name of each, if character name is already in the array, increase count


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
        Object comics = rowSet.getObject("comics_in_collection");
        try {
            collection.setComicsInCollection(mapComicsToArray((Object[]) ((SerialArray)comics).getArray()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return collection;
    }

    private int[] mapComicsToArray(Object[] objArray) throws SQLException {
        List<Integer> listComics = new ArrayList<>();
        for(Object o : objArray) {
            listComics.add((Integer)o);
        }

        Integer[] integerArray = listComics.toArray(new Integer[listComics.size()]);
        int[] array = new int[integerArray.length];
        for (int i = 0; i < integerArray.length; i++) {
            array[i] = integerArray[i];
        }
        return array;
    }
}
