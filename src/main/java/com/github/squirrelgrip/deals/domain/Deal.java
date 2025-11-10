package com.github.squirrelgrip.deals.domain;

public class Deal {
    private final String restaurantObjectId;
    private final String restaurantName;
    private final String restaurantAddress1;
    private final String restaurantSuburb;
    private final String restaurantOpen;
    private final String restaurantClose;
    private final String dealObjectId;
    private final Integer discount;
    private final Boolean dineIn;
    private final Boolean lightning;

    public Deal(
            String restaurantObjectId,
            String restaurantName,
            String restaurantAddress1,
            String restaurantSuburb,
            String restaurantOpen,
            String restaurantClose,
            String dealObjectId,
            Integer discount,
            Boolean dineIn,
            Boolean lightning
    ) {
        this.restaurantObjectId = restaurantObjectId;
        this.restaurantName = restaurantName;
        this.restaurantAddress1 = restaurantAddress1;
        this.restaurantSuburb = restaurantSuburb;
        this.restaurantOpen = restaurantOpen;
        this.restaurantClose = restaurantClose;
        this.dealObjectId = dealObjectId;
        this.discount = discount;
        this.dineIn = dineIn;
        this.lightning = lightning;
    }

    public String getRestaurantObjectId() {
        return restaurantObjectId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public String getRestaurantAddress1() {
        return restaurantAddress1;
    }

    public String getRestaurantSuburb() {
        return restaurantSuburb;
    }

    public String getRestaurantOpen() {
        return restaurantOpen;
    }

    public String getRestaurantClose() {
        return restaurantClose;
    }

    public String getDealObjectId() {
        return dealObjectId;
    }

    public Integer getDiscount() {
        return discount;
    }

    public Boolean getDineIn() {
        return dineIn;
    }

    public Boolean getLightning() {
        return lightning;
    }
}
