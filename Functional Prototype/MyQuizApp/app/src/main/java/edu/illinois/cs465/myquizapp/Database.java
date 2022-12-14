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
    public static Map<String, Set<Flight>> trash = new HashMap<>(); // collection trash

    public static void addCollection(String collectionName) {
        collections.put(collectionName, new HashSet<Flight>());
        combinationsInCollection.put(collectionName, new HashMap<>());
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
                System.out.println("flight " + flight);
                if(flight != null)
                    flights.add(flight);
                combinations.put(combinationName, flights);
            }
        }else{
            combinations = new HashMap<>();
            Set<Flight> flights = new HashSet<>();
            if(flight != null)
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
        }else if(flight != null && Double.parseDouble(cs.getLowestPrice()) > Double.parseDouble(flight.getTotalPrice())){
            cs.setLowestPrice(flight.getTotalPrice());
        }
        cs.setPlanNum(cs.getPlanNum() + 1);
        status.put(collectionName, cs);
        combinationsInCollection.put(collectionName, combinations);
    }

    public static void deleteCollection(String collectionName){
        if(collections.containsKey(collectionName)) {
            Set<Flight> deleteFlights = collections.get(collectionName);
            collections.remove(collectionName);
            trash.put(collectionName, deleteFlights);
        }
    }

    public static void deleteFlightFromCollection(String collectionName, Flight flight) {
        if (collections.containsKey(collectionName)) {
            collections.get(collectionName).remove(flight);
        }
    }

    public static void restoreCollection(String collectionName){
        if(trash.containsKey(collectionName)){
            Set<Flight> restoreFlights = trash.get(collectionName);
            trash.remove(collectionName);
            collections.put(collectionName, restoreFlights);
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

    public static void deleteFlightInCombination(String collectionName, String combinationName, Flight flight){
        Map<String, Set<Flight>> combinations = Database.combinationsInCollection.get(collectionName);
        Set<Flight> flightSet = combinations.get(combinationName);
        if(flightSet.contains(flight)){
            flightSet.remove(flight);
        }
    }


}
