package com.github.squirrelgrip.deals.service;

import com.github.squirrelgrip.deals.domain.Deal;
import com.github.squirrelgrip.deals.repository.RestaurantRepository;

import java.time.LocalTime;
import java.util.stream.Stream;

public class DealService {
    private final RestaurantRepository restaurantRepository;

    public DealService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public Stream<Deal> streamAllDeals() {
        return restaurantRepository.streamAll().flatMap(restaurant ->
                restaurant.getDeals()
                        .stream()
                        .map(restaurantDeal ->
                                restaurantDeal.getRealDeal(restaurant)
                        )
        );
    }

    public Stream<Deal> streamAllDealsAt(String time) {
        LocalTime localTime = LocalTime.parse(time);
        return streamAllDeals().filter(deal -> deal.isValidAt(localTime));
    }
}
