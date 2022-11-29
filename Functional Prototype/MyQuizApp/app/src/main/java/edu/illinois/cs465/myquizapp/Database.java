package edu.illinois.cs465.myquizapp;

import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import edu.illinois.cs465.myquizapp.pojo.CollectionStatus;
import edu.illinois.cs465.myquizapp.pojo.Filter;
import edu.illinois.cs465.myquizapp.pojo.Flight;

public class Database {
    public static Map<String, Set<Flight>> collections = new HashMap<>(); // collectionName, Flights in this collection
    public static Map<String, Map<String, Set<Flight>>> combinationsInCollection = new HashMap<>(); // collectionName, <combinationName, Flights in this combination>
    public static Map<String, Filter> autoFilter = new HashMap<>(); // collectionName, filter
    public static Map<String, CollectionStatus> status = new HashMap<>(); // collectionName, collectionStatus

    static {
        Set<Flight> set = new HashSet<>();
//        if (collections.containsKey("My Trip to Champaign")) {
//            System.out.println("number of item in the collection " + collections.get("My Trip to Champaign").size());
//        }
        System.out.println("implement once");
        Flight f1 = new Flight("1", "JFK", "CMI", "8:30am", "10:30am", "$123", "AA", 2);
        Flight f2 = new Flight("1", "JFK", "CMI", "11:30am", "2:30pm", "$250", "AA", 2);
        Flight f3 = new Flight("1", "JFK", "CMI", "2:30am", "5:30am", "$100", "UA", 1);
        set.add(f1);
        set.add(f2);
        set.add(f3);
        collections.put("My Trip to Champaign", set);

    }

    public static void addCollection(String collectionName) {
        collections.put(collectionName, new HashSet<Flight>());
    }

    public static void addFlightToCollection(String collectionName, @NonNull Flight flight){
        if(collections.containsKey(collectionName)) {
            Set<Flight> flights = collections.get(collectionName);
            flights.add(flight);
            collections.put(collectionName, flights);
        }
    }

    public static void addCombination(String collectionName, String combinationName, Flight flight){
        Map<String, Set<Flight>> combinations = combinationsInCollection.get(collectionName);
        if(combinations != null){
            if(combinations.containsKey(combinationName)){
                Set<Flight> flights = combinations.get(combinationName);
                flights.add(flight);
                combinations.put(combinationName, flights);
            }else{
                Set<Flight> flights = new HashSet<>();
                flights.add(flight);
                combinations.put(combinationName, flights);
            }
        }else{
            combinations = new HashMap<>();
            Set<Flight> flights = new HashSet<>();
            flights.add(flight);
            combinations.put(combinationName, flights);
        }
        CollectionStatus cs = status.get(collectionName);
        if(cs == null) {
            cs = new CollectionStatus();
            if(flight != null){
                cs.setLowestPrice(flight.getTotalPrice());
            }
            cs.setPlanNum(0);
        }else if(Double.parseDouble(cs.getLowestPrice()) > Double.parseDouble(flight.getTotalPrice())){
            if(flight != null){
                cs.setLowestPrice(flight.getTotalPrice());
            }
        }
        cs.setPlanNum(cs.getPlanNum() + 1);
        status.put(collectionName, cs);
        combinationsInCollection.put(collectionName, combinations);
    }

    public static void deleteCollection(String collectionName){
        if(collections.containsKey(collectionName))
            collections.remove(collectionName);
    }

    public static void deleteFlightFromCollection(String collectionName, Flight flight) {
        if (collections.containsKey(collectionName)) {
            collections.get(collectionName).remove(flight);
        }
    }

    public static void deleteCombination(String collectionName, String combinationName){
        Map<String, Set<Flight>> combinations = combinationsInCollection.get(collectionName);
        if(combinations.containsKey(combinationName))
            combinations.remove(combinationName);
    }

    public static void addAutoFilter(String collectionName, Filter filter){
        autoFilter.put(collectionName, filter);
    }


}
