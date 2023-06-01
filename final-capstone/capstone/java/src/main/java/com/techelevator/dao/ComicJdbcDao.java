package com.techelevator.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class ComicJdbcDao implements ComicDao {

    private final JdbcTemplate template;

    public ComicJdbcDao(DataSource dataSource) {
        template = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Comic> getAllComics() {
        String sql = "SELECT * FROM comics";
        List<Comic> comics = new ArrayList<>();
        SqlRowSet results = template.queryForRowSet(sql);

        while (results.next()) {
            Comic comic = mapRowToComic(results);
            comics.add(comic);
        }
        return comics;
    }

    @Override
    public Comic getComic(int id) {
        String sql = "SELECT * FROM comics WHERE id = ?";
        SqlRowSet results = template.queryForRowSet(sql, id);
        if (results.next()) {
            return mapRowToComic(results);
        }
        return null;
    }

    @Override
    public void addComic(Comic comicToAdd) {
        int id = comicToAdd.getComicId();
        String title = comicToAdd.getTitle();
        int issue = comicToAdd.getIssueNumber();
        String[] characters = comicToAdd.getCharacters();
        int volume = comicToAdd.getVolume();

        String sql = "INSERT INTO comics(title, issue, characters, volume) VALUES ?, ?, ?, ?)";
        template.update(sql, title, issue, characters, volume);
    }


    @Override
    public void editComic(Comic comicToUpdate) {
        String sql = "UPDATE comics SET title = ?, issue = ?, characters = ?, volume = ? WHERE id = ?";
        template.update(sql, comicToUpdate.getTitle(), comicToUpdate.getIssueNumber(), comicToUpdate.getCharacters(),
                comicToUpdate.getVolume(), comicToUpdate.getComicId());
    }


    @Override
    public void deleteComic(int id) {
        String sql = "DELETE * FROM comics WHERE id = ?";
        template.update(sql, id);
    }

    private Comic mapRowToComic(SqlRowSet rowSet) {
        Comic comics = new Comic();
        comics.setComicId(rowSet.getInt("comic_id"));
        comics.setTitle(rowSet.getString("title"));
        comics.setIssueNumber(rowSet.getInt("issue"));
        comics.setCharacters((String[]) rowSet.getObject("characters"));
        comics.setVolume(rowSet.getInt("volume"));
        return comics;
    }
}