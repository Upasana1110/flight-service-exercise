package com.globant.fligth.api.exercise.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

// The details associated with each flight object.
public class FlightDetails {
    private String departureCity;
    private String destinationCity;
    private String airline;
    private String airlineCode;
    private String gate;

    @SerializedName("departureDateTime")
    private Date departureDate;

    @SerializedName("flight_number")
    private int flightNumber;

    public String getDepartureCity(){
        return this.departureCity;
    }

    public void setDepartureCity(String departureCity){
        this.departureCity = departureCity;
    }

    public String getDestinationCity(){
        return this.destinationCity;
    }

    public void setDestinationCity(String destinationCity){
        this.destinationCity = destinationCity;
    }

    public Date getDepartureDate(){
        return this.departureDate;
    }

    public void setDepartureDate(Date departureDate){
        this.departureDate = departureDate;
    }

    public int getFlightNumber(){
        return this.flightNumber;
    }

    public void setFlightNumber(int flightNumber){
        this.flightNumber = flightNumber;
    }

    public String getAirline(){
        return this.airline;
    }

    public void setAirline(String airline){
        this.airline = airline;
    }

    public String getAirlineCode(){
        return this.airlineCode;
    }

    public void setAirlineCode(String airlineCode){
        this.airlineCode = airlineCode;
    }

    public String getGate(){
        if(gate == null){
            return "N/A";
        }
        return this.gate;
    }

    public void setGate(String gate){
        this.gate = gate;
    }

}
