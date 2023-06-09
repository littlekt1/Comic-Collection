package com.techelevator.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "path",
        "extension"
})
@Generated("jsonschema2pojo")
@JsonIgnoreProperties(ignoreUnknown = true)
public class MarvelThumbnail {
    @JsonProperty("path")
    private String path;
    @JsonProperty("extension")
    private String extension;

    @JsonProperty("path")
    public String getPath() {
        return path;
    }
    @JsonProperty("path")
    public void setPath(String path) {
        this.path = path;
    }
    @JsonProperty("extension")
    public String getExtension() {
        return extension;
    }
    @JsonProperty("extension")
    public void setExtension(String extension) {
        this.extension = extension;
    }
}
