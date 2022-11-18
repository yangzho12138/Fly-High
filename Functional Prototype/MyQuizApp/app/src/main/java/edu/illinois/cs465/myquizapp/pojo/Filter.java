package edu.illinois.cs465.myquizapp.pojo;

import android.os.Parcel;
import android.os.Parcelable;

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
}
