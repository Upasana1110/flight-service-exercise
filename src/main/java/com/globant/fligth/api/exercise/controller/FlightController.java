package com.globant.fligth.api.exercise.controller;

import com.globant.fligth.api.exercise.model.FlightDetails;
import com.globant.fligth.api.exercise.service.IFlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api")
public class FlightController {
    @Autowired
    public IFlightService flightListService;

    @RequestMapping(value = "/flights", method = RequestMethod.GET)
    public List<FlightDetails> getFlights(@RequestParam Optional<String> airlineCode,
                                          @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Optional<Date> departureDate){
        return flightListService.getFlights(airlineCode, departureDate);
    }
}
