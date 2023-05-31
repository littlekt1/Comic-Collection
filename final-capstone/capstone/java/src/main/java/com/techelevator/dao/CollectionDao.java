package com.techelevator.dao;

import com.techelevator.model.Collection;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public interface CollectionDao {

    public List<Collection> getCollections();

    public Collection getCollection(int id);

    public void addCollection(Collection collectionToAdd);

    public void editCollection(Collection collectionToUpdate);

    public void deleteCollection(int id);

}
