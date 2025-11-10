package com.github.squirrelgrip.deals.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalTime;
import java.util.List;

public class Restaurant {
    String objectId;
    String name;
    String address1;
    String suburb;
    List<String> cuisines;
    String imageLink;
    @JsonFormat(pattern = "h:mma")
    LocalTime open;
    @JsonFormat(pattern = "h:mma")
    LocalTime close;
    List<RestaurantDeal> deals;

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getSuburb() {
        return suburb;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public List<String> getCuisines() {
        return cuisines;
    }

    public void setCuisines(List<String> cuisines) {
        this.cuisines = cuisines;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public LocalTime getOpen() {
        return open;
    }

    public void setOpen(LocalTime open) {
        this.open = open;
    }

    public LocalTime getClose() {
        return close;
    }

    public void setClose(LocalTime close) {
        this.close = close;
    }

    public List<RestaurantDeal> getDeals() {
        return deals;
    }

    public void setDeals(List<RestaurantDeal> deals) {
        this.deals = deals;
    }
}
