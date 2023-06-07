package com.techelevator.model; ;

import java.util.List;
import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.techelevator.model.Series;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "series",
        "number",
        "title",
        "name",
        "cover_date",
        "store_date",
        "price",
        "rating",
        "sku",
        "isbn",
        "upc",
        "page",
        "desc",
        "image",
        "arcs",
        "characters",
        "teams",
        "reprints",
        "cv_id",
        "resource_url",
        "modified"
})
@Generated("jsonschema2pojo")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ComicSpecificIssue {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("series")
    private Series series;
    @JsonProperty("number")
    private String number;
    @JsonProperty("title")
    private String title;
    @JsonProperty("name")
    private List<String> name;
    @JsonProperty("cover_date")
    private String coverDate;
    @JsonProperty("store_date")
    private String storeDate;
    @JsonProperty("price")
    private String price;
    @JsonProperty("sku")
    private String sku;
    @JsonProperty("isbn")
    private String isbn;
    @JsonProperty("upc")
    private String upc;
    @JsonProperty("page")
    private Integer page;
    @JsonProperty("desc")
    private String desc;
    @JsonProperty("image")
    private String image;
    @JsonProperty("arcs")
    private List<Object> arcs;
    @JsonProperty("characters")
    private List<ComicCharacter> characters;
    @JsonProperty("teams")
    private List<Object> reprints;
    @JsonProperty("resource_url")
    private String resourceUrl;
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



    @JsonProperty("series")
    public Series getSeries() {
        return series;
    }

    @JsonProperty("series")
    public void setSeries(Series series) {
        this.series = series;
    }

    @JsonProperty("number")
    public String getNumber() {
        return number;
    }

    @JsonProperty("number")
    public void setNumber(String number) {
        this.number = number;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("name")
    public List<String> getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(List<String> name) {
        this.name = name;
    }

    @JsonProperty("cover_date")
    public String getCoverDate() {
        return coverDate;
    }

    @JsonProperty("cover_date")
    public void setCoverDate(String coverDate) {
        this.coverDate = coverDate;
    }

    @JsonProperty("store_date")
    public String getStoreDate() {
        return storeDate;
    }

    @JsonProperty("store_date")
    public void setStoreDate(String storeDate) {
        this.storeDate = storeDate;
    }

    @JsonProperty("price")
    public String getPrice() {
        return price;
    }

    @JsonProperty("price")
    public void setPrice(String price) {
        this.price = price;
    }

    @JsonProperty("sku")
    public String getSku() {
        return sku;
    }

    @JsonProperty("sku")
    public void setSku(String sku) {
        this.sku = sku;
    }

    @JsonProperty("isbn")
    public String getIsbn() {
        return isbn;
    }

    @JsonProperty("isbn")
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @JsonProperty("upc")
    public String getUpc() {
        return upc;
    }

    @JsonProperty("upc")
    public void setUpc(String upc) {
        this.upc = upc;
    }

    @JsonProperty("page")
    public Integer getPage() {
        return page;
    }

    @JsonProperty("page")
    public void setPage(Integer page) {
        this.page = page;
    }

    @JsonProperty("desc")
    public String getDesc() {
        return desc;
    }

    @JsonProperty("desc")
    public void setDesc(String desc) {
        this.desc = desc;
    }

    @JsonProperty("image")
    public String getImage() {
        return image;
    }

    @JsonProperty("image")
    public void setImage(String image) {
        this.image = image;
    }

    @JsonProperty("arcs")
    public List<Object> getArcs() {
        return arcs;
    }

    @JsonProperty("arcs")
    public void setArcs(List<Object> arcs) {
        this.arcs = arcs;
    }

    @JsonProperty("characters")
    public List<ComicCharacter> getCharacters() {
        return characters;
    }

    @JsonProperty("characters")
    public void setCharacters(List<ComicCharacter> characters) {
        this.characters = characters;
    }

    @JsonProperty("reprints")
    public List<Object> getReprints() {
        return reprints;
    }

    @JsonProperty("reprints")
    public void setReprints(List<Object> reprints) {
        this.reprints = reprints;
    }

    @JsonProperty("resource_url")
    public String getResourceUrl() {
        return resourceUrl;
    }

    @JsonProperty("resource_url")
    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
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