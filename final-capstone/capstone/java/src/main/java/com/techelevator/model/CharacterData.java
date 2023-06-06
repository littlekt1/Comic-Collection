package com.techelevator.model;

import java.util.List;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "count",
        "next",
        "previous",
        "results"
})
@Generated("jsonschema2pojo")
public class CharacterData {

    @JsonProperty("count")
    private Integer count;
    @JsonProperty("next")
    private String next;
    @JsonProperty("previous")
    private Object previous;
    @JsonProperty("results")
    private List<CharacterComicData> results;

    @JsonProperty("count")
    public Integer getCount() {
        return count;
    }

    @JsonProperty("count")
    public void setCount(Integer count) {
        this.count = count;
    }

    @JsonProperty("next")
    public String getNext() {
        return next;
    }

    @JsonProperty("next")
    public void setNext(String next) {
        this.next = next;
    }

    @JsonProperty("previous")
    public Object getPrevious() {
        return previous;
    }

    @JsonProperty("previous")
    public void setPrevious(Object previous) {
        this.previous = previous;
    }

    @JsonProperty("results")
    public List<CharacterComicData> getResults() {
        return results;
    }

    @JsonProperty("results")
    public void setResults(List<CharacterComicData> results) {
        this.results = results;
    }

}
