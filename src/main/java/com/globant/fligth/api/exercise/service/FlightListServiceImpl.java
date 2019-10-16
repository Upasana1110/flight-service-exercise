package com.globant.fligth.api.exercise.service;


import com.globant.fligth.api.exercise.model.FlightDetails;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service("flightListService")
public class FlightListServiceImpl implements IFlightService{

    private static final String USER_AGENT = "Mozilla/5.0";
    private static final long MILLIS_PER_DAY = 86400000;

    private static FlightDetails[] allFlights;

    @PostConstruct
    private void init() {
        try {

            // Initialize the flight details once in the start of the application.
            URL url = new URL("http://demo3998236.mockable.io/flights");
            String flightDetails =  getHTTPResponse(url);

            Gson gson = new Gson();
            allFlights = gson.fromJson(flightDetails, FlightDetails[].class);

        } catch (MalformedURLException e){
            // Do nothing since this is not an expected situation.
        }
    }

    // Sends a request to the specified url and returns the string http response.
    private String getHTTPResponse(URL url){
        try {
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            // optional default is GET
            con.setRequestMethod("GET");

            //add request header
            con.setRequestProperty("User-Agent", USER_AGENT);

            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            // Read the entire input.
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            return response.toString();
        } catch (Exception ex) {
            // We do not expect to reach here until there is an internal error..
            return "<html><body><H1>Internal Server Error</H1></Body></HTML>";
        }
    }

    public List<FlightDetails> getFlights(Optional<String> airlineCode, Optional<Date> departureDate) {


            List<FlightDetails> filteredResults = new ArrayList<>();

            for(int i=0; i < allFlights.length; i++){
                FlightDetails currentFlight = allFlights[i];

                // If both are not present, there are no filters provided, add all flights.
                if(!airlineCode.isPresent() && !departureDate.isPresent()){
                    filteredResults.add(currentFlight);
                }
                // Else If both are present, then check if both match correctly.
                else if(airlineCode.isPresent() && currentFlight.getAirlineCode().equals(airlineCode.get()) &&
                   departureDate.isPresent() && isSameDay(currentFlight.getDepartureDate(), departureDate.get()))
                {
                   filteredResults.add(currentFlight);
                }
                else if(airlineCode.isPresent() &&
                        currentFlight.getAirlineCode().equals(airlineCode.get()) &&
                        !departureDate.isPresent())
                {
                    filteredResults.add(currentFlight);
                }
                else if(!airlineCode.isPresent() &&
                        departureDate.isPresent() &&
                        isSameDay(currentFlight.getDepartureDate(), departureDate.get()))
                {
                    filteredResults.add(currentFlight);
                }
                // else the flight doesn't obey the filter, don't do anything.

            }

            return filteredResults;
    }

    // Check if both dates are equal, ignoring the time part of the dates.
    private static boolean isSameDay(Date date1, Date date2) {

        // Strip out the time part of each date.
        long julianDayNumber1 = date1.getTime() / MILLIS_PER_DAY;
        long julianDayNumber2 = date2.getTime() / MILLIS_PER_DAY;

        // If they now are equal then it is the same day.
        return julianDayNumber1 == julianDayNumber2;
    }
}
