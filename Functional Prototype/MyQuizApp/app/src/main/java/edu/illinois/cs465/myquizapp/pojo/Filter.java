package edu.illinois.cs465.myquizapp.pojo;

import java.io.Serializable;
import java.util.List;

public class Filter {
    public String origin = "";
    public String destination = "";
    public Integer stops = 0;
    public Integer bags = 0;
    public Integer duration = 0;
    public Integer adult_cnt = 0;
    public Integer children_cnt = 0;
    public Integer infant_cnt = 0;
    public Integer lowPrice = 0;
    public Integer highPrice = 0;

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

    public Integer getStops() {
        return stops;
    }

    public void setStops(Integer stops) {
        this.stops = stops;
    }

    public Integer getBags() {
        return bags;
    }

    public void setBags(Integer bags) {
        this.bags = bags;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getAdult_cnt() {
        return adult_cnt;
    }

    public void setAdult_cnt(Integer adult_cnt) {
        this.adult_cnt = adult_cnt;
    }

    public Integer getChildren_cnt() {
        return children_cnt;
    }

    public void setChildren_cnt(Integer children_cnt) {
        this.children_cnt = children_cnt;
    }

    public Integer getInfant_cnt() {
        return infant_cnt;
    }

    public void setInfant_cnt(Integer infant_cnt) {
        this.infant_cnt = infant_cnt;
    }

    public Integer getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(Integer lowPrice) {
        this.lowPrice = lowPrice;
    }

    public Integer getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(Integer highPrice) {
        this.highPrice = highPrice;
    }
}
