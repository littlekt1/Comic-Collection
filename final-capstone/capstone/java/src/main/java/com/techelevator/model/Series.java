package com.techelevator.model;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "name",
        "volume",
        "year_began"
})
@Generated("jsonschema2pojo")
public class Series {

    @JsonProperty("name")
    private String name;
    @JsonProperty("volume")
    private Integer volume;
    @JsonProperty("year_began")
    private Integer yearBegan;

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("volume")
    public Integer getVolume() {
        return volume;
    }

    @JsonProperty("volume")
    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    @JsonProperty("year_began")
    public Integer getYearBegan() {
        return yearBegan;
    }

    @JsonProperty("year_began")
    public void setYearBegan(Integer yearBegan) {
        this.yearBegan = yearBegan;
    }

}
