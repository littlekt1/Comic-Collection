package com.techelevator.model; ;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "series",
        "number",
        "issue",
        "cover_date",
        "image",
        "modified"
})
@Generated("jsonschema2pojo")
public class ResultByDate {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("number")
    private String number;
    @JsonProperty("issue")
    private String issue;
    @JsonProperty("cover_date")
    private String coverDate;
    @JsonProperty("image")
    private String image;
    @JsonProperty("modified")
    private String modified;

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }


    @JsonProperty("number")
    public String getNumber() {
        return number;
    }

    @JsonProperty("number")
    public void setNumber(String number) {
        this.number = number;
    }

    @JsonProperty("issue")
    public String getIssue() {
        return issue;
    }

    @JsonProperty("issue")
    public void setIssue(String issue) {
        this.issue = issue;
    }

    @JsonProperty("cover_date")
    public String getCoverDate() {
        return coverDate;
    }

    @JsonProperty("cover_date")
    public void setCoverDate(String coverDate) {
        this.coverDate = coverDate;
    }

    @JsonProperty("image")
    public String getImage() {
        return image;
    }

    @JsonProperty("image")
    public void setImage(String image) {
        this.image = image;
    }

    @JsonProperty("modified")
    public String getModified() {
        return modified;
    }

    @JsonProperty("modified")
    public void setModified(String modified) {
        this.modified = modified;
    }

}