package edu.illinois.cs465.projectmainpage.pojo;

import java.io.Serializable;
import java.util.Set;

public class Combination implements Serializable {
    public String combinationName;
    public Set<Flight> flights;

    public Combination(String combinationName, Set<Flight> flights){
        this.combinationName = combinationName;
        this.flights = flights;
    }

    public String getCombinationName() {
        return combinationName;
    }

    public void setCombinationName(String combinationName) {
        this.combinationName = combinationName;
    }

    public Set<Flight> getFlights() {
        return flights;
    }

    public void setFlights(Set<Flight> flights) {
        this.flights = flights;
    }
}
