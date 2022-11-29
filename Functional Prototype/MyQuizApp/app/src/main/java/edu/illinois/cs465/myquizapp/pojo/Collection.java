package edu.illinois.cs465.myquizapp.pojo;

import java.io.Serializable;

public class Collection implements Serializable {

    public String collectionName;
    public Integer numOfPlan;
    public String lowestPrice;

    public Collection(String collectionName, Integer numOfPlan, String lowestPrice) {
        this.collectionName = collectionName;
        this.numOfPlan = numOfPlan;
        this.lowestPrice = lowestPrice;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public Integer getNumOfPlan() {
        return numOfPlan;
    }

    public void setNumOfPlan(Integer numOfPlan) {
        this.numOfPlan = numOfPlan;
    }

    public String getLowestPrice() {
        return lowestPrice;
    }

    public void setLowestPrice(String lowestPrice) {
        this.lowestPrice = lowestPrice;
    }
}
