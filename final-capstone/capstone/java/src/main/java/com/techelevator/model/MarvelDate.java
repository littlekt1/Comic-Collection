package com.techelevator.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.processing.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "type",
        "date"
})
@Generated("jsonschema2pojo")
@JsonIgnoreProperties(ignoreUnknown = true)
public class MarvelDate {
    @JsonProperty("type")
    private String dateType;
    @JsonProperty("date")
    private String date;

    @JsonProperty("type")
    public String getDateType() {
        return dateType;
    }
    @JsonProperty("type")
    public void setDateType(String dateType) {
        this.dateType = dateType;
    }

    @JsonProperty("date")
    public String getDate() {
        return date;
    }
    @JsonProperty("date")
    public void setDate(String date) {
        this.date = date;
    }
}
