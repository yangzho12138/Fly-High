package edu.illinois.cs465.myquizapp.pojo;

import java.io.Serializable;
import java.util.List;

public class Filter implements Serializable {
    public String origin;
    public String destination;
    public Integer stops;
    public Integer bags;
    public Integer duration;
    public Integer adult_cnt;
    public Integer children_cnt;
    public Integer infant_cnt;
    public Integer lowPrice;
    public Integer highPrice;
}
