package edu.illinois.cs465.projectmainpage.pojo;

import java.util.ArrayList;
import java.util.List;

public class Collection {

    public int id;
    public String collectionName;
    public List<Flight> flights;

    public Collection(int id, String collectionName) {
        this.id = id;
        this.collectionName = collectionName;
        this.flights = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }


}
