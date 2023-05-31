package com.techelevator.model;

public class Comic {

    private int comicId;
    private String title;
    private int issueNumber;
    private String[] characters;
    private int volume;

    public Comic() {};

    public Comic(int comicId, String title, int issueNumber, String[] characters, int volume) {
        this.comicId = comicId;
        this.title = title;
        this.issueNumber = issueNumber;
        this.characters = characters;
        this.volume = volume;
    }

    public int getComicId() {
        return comicId;
    }

    public void setComicId(int comicId) {
        this.comicId = comicId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(int issueNumber) {
        this.issueNumber = issueNumber;
    }

    public String[] getCharacters() {
        return characters;
    }

    public void setCharacters(String[] characters) {
        this.characters = characters;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }
}
