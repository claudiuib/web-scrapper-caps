package org.example;

/**
 * This class represents a Product object with corresponding fields and methods to get and set the fields.
 */

public class ProductWebScrap {

    private String title;

    private String colour;
    private String styleKeyId;

    public String getStyleKeyId() {
        return styleKeyId;
    }

    public void setStyleKeyId(String styleKeyId) {
        this.styleKeyId = styleKeyId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getUrl() {
        return url;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getUrl(String urlNike) {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageUrl(String imgUrlNike) {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    private double price;

    @Override
    public String toString() {
        return "ProductWebScrap{" +
                "title='" + title + '\'' +
                ", colour='" + colour + '\'' +
                ", styleKeyId='" + styleKeyId + '\'' +
                ", price=" + price +
                ", url='" + url + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }

    private String url;
    private String imageUrl;

}
