package com.github.squirrelgrip.deals.controllers;

import com.github.squirrelgrip.deals.domain.Deals;
import com.github.squirrelgrip.deals.service.DealService;

public class DealController {
    private final DealService dealService;

    public DealController(DealService dealService) {
        this.dealService = dealService;
    }

    public Deals getDealsAt(String time) {
        return dealService.getDealsAt(time);
    }
}
