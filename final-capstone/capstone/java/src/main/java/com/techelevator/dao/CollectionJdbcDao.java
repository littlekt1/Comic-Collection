package com.techelevator.dao;

import com.techelevator.model.Collection;
import com.techelevator.model.Comic;
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
    public List<Collection> getCollections() {
        String sql = "SELECT * FROM collections";
        List<Collection> collectionList = new ArrayList<>();
        SqlRowSet results = template.queryForRowSet(sql);

        while(results.next()) {
            Collection collection = mapRowToCollection(results);
            collectionList.add(collection);
        }
        return collectionList;
    }

    @Override
    public Collection getCollection(int id) {
        String sql = "SELECT * FROM collections WHERE id = ?";
        SqlRowSet results = template.queryForRowSet(sql, id);
        if (results.next()) {
            return mapRowToCollection(results);
        }
        return null;
    }

    @Override
    public void addCollection(Collection collectionToAdd) {
        int id = collectionToAdd.getCollectionId();
        String name = collectionToAdd.getCollectionName();
        List<Comic> comics = collectionToAdd.getComicsInCollection();

        String sql = "INSERT INTO collection(collection_name, comics_in_collection) VALUES ?, ?)";
        template.update(sql, name, comics);


    }

    @Override
    public void editCollection(Collection collectionToUpdate) {
        String sql = "UPDATE collections SET collection_name = ?, comics_in_collection = ? WHERE id = ?";
        template.update(sql, collectionToUpdate.getCollectionName(), collectionToUpdate.getComicsInCollection(),
                collectionToUpdate.getCollectionId());

    }

    @Override
    public void deleteCollection(int id) {
        String sql = "DELETE * FROM collections WHERE id = ?";
        template.update(sql, id);

    }

    private Collection mapRowToCollection(SqlRowSet rowSet) {
        Collection collection = new Collection();
        collection.setCollectionId(rowSet.getInt("collection_id"));
        collection.setCollectionName(rowSet.getString("collection_name"));
        collection.setComicsInCollection((List<Comic>) rowSet.getObject("comics_in_collection"));
        return collection;
    }
}
