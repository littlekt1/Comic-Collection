package com.techelevator.model;

public class Comic {

    // TODO NEED TO DEFINE WHAT WE WANT FROM THE API WHEN WE PULL. IDEAS BELOW

    private int comicId; //store in DB for direct call to API
    private String characterId; //will likely need this to pull comics just for the character based on the API doc
    private String comicTitle; //under issue
    private String comicDescription; //under issue
    private String comicImage; //under issue
    private String charactersInIssue; //think Thwin asked for this. it is an object inside the JSON returned from a single issue pull so it is easy to get

    //TODO CONSTRUCTOR ONCE ALL VARIABLES ARE DETERMINED

    public Comic(String characterId) {
        this.characterId = characterId;
    }


    //TODO GETTERS/SETTERS ONCE VARIABLES DETERMINED


}
