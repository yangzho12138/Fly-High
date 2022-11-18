package edu.illinois.cs465.myquizapp.pojo;

import java.io.Serializable;

public class Flight implements Serializable {
    private String id;
    private String origin;
    private String destination;
    private String departureTime;
    private String arriveTime;
    private String totalPrice;
    private String airline;
    private Integer bags;

    public Flight(String id, String origin, String destination, String departureTime, String arriveTime, String totalPrice, String airline, Integer bags) {
        this.id = id;
        this.origin = origin;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arriveTime = arriveTime;
        this.totalPrice = totalPrice;
        this.airline = airline;
        this.bags = bags;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(String arriveTime) {
        this.arriveTime = arriveTime;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public Integer getBags() {
        return bags;
    }

    public void setBags(Integer bags) {
        this.bags = bags;
    }
}
