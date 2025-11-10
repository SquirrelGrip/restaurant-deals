package com.github.squirrelgrip.deals.service;

import com.github.squirrelgrip.deals.domain.Deal;
import com.github.squirrelgrip.deals.domain.Deals;
import com.github.squirrelgrip.deals.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.stream.Stream;

@Service
public class DealService {
    private final RestaurantRepository restaurantRepository;

    @Autowired
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

    public Deals getDealsAt(String time) {
        LocalTime localTime = LocalTime.parse(time);
        return new Deals(
                streamAllDeals()
                        .filter(deal -> deal.isValidAt(localTime))
                        .toList()
        );
    }
}
