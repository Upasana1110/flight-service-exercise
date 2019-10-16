package com.globant.fligth.api.exercise.service;

import com.globant.fligth.api.exercise.model.FlightDetails;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IFlightService{

    // This method retrieves the flights that obey the given filters, if any.
    List<FlightDetails> getFlights(Optional<String> airlineCode, Optional<Date> departureDate);
}
