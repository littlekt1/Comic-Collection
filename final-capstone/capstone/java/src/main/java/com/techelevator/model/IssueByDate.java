package com.techelevator.model; ;

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
public class IssueByDate {

    @JsonProperty("count")
    private Integer count;
    @JsonProperty("next")
    private Object next;
    @JsonProperty("previous")
    private Object previous;
    @JsonProperty("results")
    private List<ResultByDate> results;

    @JsonProperty("count")
    public Integer getCount() {
        return count;
    }

    @JsonProperty("count")
    public void setCount(Integer count) {
        this.count = count;
    }

    @JsonProperty("next")
    public Object getNext() {
        return next;
    }

    @JsonProperty("next")
    public void setNext(Object next) {
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
    public List<ResultByDate> getResultsByDate() {
        return results;
    }

    @JsonProperty("results")
    public void setResultsResultsByDate(List<ResultByDate> results) {
        this.results = results;
    }

}