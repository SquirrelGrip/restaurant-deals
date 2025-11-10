package com.github.squirrelgrip.deals.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalTime;

public class RestaurantDeal {
    private String objectId;
    private Integer discount;
    private Boolean dineIn;
    private Boolean lightning;
    @JsonFormat(pattern = "h:mma")
    private LocalTime open;
    @JsonFormat(pattern = "h:mma")
    private LocalTime close;
    @JsonFormat(pattern = "h:mma")
    private LocalTime start;
    @JsonFormat(pattern = "h:mma")
    private LocalTime end;
    private Integer qtyLeft;

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Boolean getDineIn() {
        return dineIn;
    }

    public void setDineIn(Boolean dineIn) {
        this.dineIn = dineIn;
    }

    public Boolean getLightning() {
        return lightning;
    }

    public void setLightning(Boolean lightning) {
        this.lightning = lightning;
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

    public LocalTime getStart() {
        return start;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public LocalTime getEnd() {
        return end;
    }

    public void setEnd(LocalTime end) {
        this.end = end;
    }

    public Integer getQtyLeft() {
        return qtyLeft;
    }

    public void setQtyLeft(Integer qtyLeft) {
        this.qtyLeft = qtyLeft;
    }

    public Deal getRealDeal(Restaurant restaurant) {
        return new Deal(
                restaurant,
                this
        );
    }
}
