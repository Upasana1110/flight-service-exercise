package com.globant.fligth.api.exercise.service;

import com.globant.fligth.api.exercise.model.FlightDetails;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
public class FlightListServiceTests {

    @TestConfiguration
    static class FlightListServiceTestContextConfiguration {

        @Bean
        public IFlightService flightListService() {
            return new FlightListServiceImpl();
        }
    }

    @Autowired
    public IFlightService flightListService;

    @Test
    public void TestGetAllFlights(){
        List<FlightDetails> flightDetailsList = flightListService.getFlights(Optional.empty(), Optional.empty());

        // Should return all the flights.
        Assert.assertEquals(5, flightDetailsList.size());
    }

    @Test
    public void TestGetFlightsWithAirlineFilter(){
        String airlineCode = "AA";
        List<FlightDetails> flightDetailsList = flightListService.getFlights(Optional.of(airlineCode), Optional.empty());

        // Should return the two AA flights.
        Assert.assertEquals(2, flightDetailsList.size());

        // Validate that each of the results has the correct airline code.
        for(FlightDetails flightDetails : flightDetailsList){
            Assert.assertEquals(airlineCode, flightDetails.getAirlineCode());
        }
    }

    @Test
    public void TestGetFlightsWithDepartureFilter() throws Exception{
        String stringDate = "2019-10-16";
        Date departureDate = new SimpleDateFormat("yyyy-MM-dd").parse(stringDate);
        List<FlightDetails> flightDetailsList = flightListService.getFlights(Optional.empty(), Optional.of(departureDate));

        // Should return the flight with the departure date.
        Assert.assertEquals(1, flightDetailsList.size());

        // Validate that each of the results has the correct departure date
        for(FlightDetails flightDetails : flightDetailsList){

            // Only compare the month, year and date, since time mismatch is acceptable.
            Assert.assertEquals(departureDate.getDate(), flightDetails.getDepartureDate().getDate());
            Assert.assertEquals(departureDate.getMonth(), flightDetails.getDepartureDate().getMonth());
            Assert.assertEquals(departureDate.getYear(), flightDetails.getDepartureDate().getYear());
        }
    }

    @Test
    public void TestGetFlightsWithDepartureAndAirlineFilter() throws Exception{
        String stringDate = "2019-10-17";
        Date departureDate = new SimpleDateFormat("yyyy-MM-dd").parse(stringDate);
        String airlinecode = "AA";
        List<FlightDetails> flightDetailsList = flightListService.getFlights(Optional.of(airlinecode), Optional.of(departureDate));

        // Should return the AA flight with the expected date
        Assert.assertEquals(1, flightDetailsList.size());

        // Validate that each of the results has the correct departure date and airline code.
        for(FlightDetails flightDetails : flightDetailsList){

            // Only compare the month, year and date, since time mismatch is acceptable.
            Assert.assertEquals(departureDate.getDate(), flightDetails.getDepartureDate().getDate());
            Assert.assertEquals(departureDate.getMonth(), flightDetails.getDepartureDate().getMonth());
            Assert.assertEquals(departureDate.getYear(), flightDetails.getDepartureDate().getYear());

            // Check if airline code matches.
            Assert.assertEquals(airlinecode, flightDetails.getAirlineCode());
        }
    }


}
