package com.github.squirrelgrip.deals.controllers;

import com.github.squirrelgrip.deals.domain.Deals;
import com.github.squirrelgrip.deals.service.DealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DealController {
    private final DealService dealService;

    @Autowired
    public DealController(DealService dealService) {
        this.dealService = dealService;
    }

    @GetMapping(path="/api/dealsAt")
    public Deals getDealsAt(@RequestParam(name = "timeOfDay") String timeOfDay) {
        return dealService.getDealsAt(timeOfDay);
    }
}
