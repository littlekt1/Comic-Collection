package com.techelevator.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import javax.annotation.Generated;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "count",
        "results"
})
@Generated("jsonschema2pojo")
@JsonIgnoreProperties(ignoreUnknown = true)
public class MarvelData {
    @JsonProperty("count")
    private int count;

    @JsonProperty("results")
    private MarvelComicResults results;

    @JsonProperty("count")
    public int getCount() {
        return count;
    }

    @JsonProperty("count")
    public void setCount(int count) {
        this.count = count;
    }

    @JsonProperty("results")
    public MarvelComicResults getResults() {
        return results;
    }

    @JsonProperty("results")
    public void setResults(MarvelComicResults results) {
        this.results = results;
    }
}
