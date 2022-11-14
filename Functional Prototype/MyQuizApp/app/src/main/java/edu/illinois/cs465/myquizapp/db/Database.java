package edu.illinois.cs465.myquizapp.db;

import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import edu.illinois.cs465.myquizapp.pojo.Filter;
import edu.illinois.cs465.myquizapp.pojo.Flight;

public class Database {
    public static Map<String, Set<Flight>> collections = new HashMap<>(); // collectionName, Flights in this collection
    public static Map<String, Map<String, Set<Flight>>> combinationsInCollection = new HashMap<>(); // collectionName, <combinationName, Flights in this combination>
    public static Map<String, Filter> autoFilter = new HashMap<>(); // collectionName, filter

    public static void addCollection(String collectionName, @NonNull Flight flight){
        if(collections.containsKey(collectionName)){
            Set<Flight> flights = collections.get(collectionName);
            flights.add(flight);
            collections.put(collectionName, flights);
        }else{
            Set<Flight> flights = new HashSet<>();
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

        combinationsInCollection.put(collectionName, combinations);
    }

    public static void deleteCollection(String collectionName){
        if(collections.containsKey(collectionName))
            collections.remove(collectionName);
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
