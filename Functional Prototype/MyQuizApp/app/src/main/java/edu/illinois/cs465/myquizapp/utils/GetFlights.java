package edu.illinois.cs465.myquizapp.utils;

import com.duffel.DuffelApiClient;
import com.duffel.model.CabinClass;
import com.duffel.model.OfferCollection;
import com.duffel.model.Passenger;
import com.duffel.model.PassengerType;
import com.duffel.model.request.OfferRequest;
import com.duffel.model.response.OfferResponse;

import java.util.List;


public class GetFlights {

    private static DuffelApiClient client = new DuffelApiClient("duffel_test_uezHADT5RpijdGZ68YO7HI6Go-t-K2AtPDkFCwz_0P2");

    public static OfferCollection searchFlights(String origin, String destination, String tripType, String departureDate1, String departureDate2, String passengerType, Integer age, String carbinType){
        OfferRequest.Slice outboundSlice = new OfferRequest.Slice();
        outboundSlice.setDepartureDate(departureDate1);
        outboundSlice.setOrigin(origin);
        outboundSlice.setDestination(destination);

        OfferRequest.Slice inboundSlice = new OfferRequest.Slice();
        if(tripType.equals("round_trip")){
            inboundSlice.setDepartureDate(departureDate2);
            inboundSlice.setOrigin(destination);
            inboundSlice.setDestination(origin);
        }

        Passenger passenger = new Passenger();
        if(passengerType.equals("adult"))
            passenger.setType(PassengerType.adult);
        else
            passenger.setAge(age);
//        passenger.setGivenName("Test");
//        passenger.setFamilyName("User");

        OfferRequest request = new OfferRequest();
        request.setMaxConnections(0);
        if(carbinType.equals("economy"))
            request.setCabinClass(CabinClass.economy);
        else if(carbinType.equals("business"))
            request.setCabinClass(CabinClass.business);
        else if(carbinType.equals("first"))
            request.setCabinClass(CabinClass.first);
        else if(carbinType.equals("premium_economy"))
            request.setCabinClass(CabinClass.premium_economy);

        if(tripType.equals("round_trip"))
            request.setSlices(List.of(outboundSlice, inboundSlice));
        else
            request.setSlices(List.of(outboundSlice));

        request.setPassengers(List.of(passenger));

        OfferResponse offerResponse = client.offerRequestService.post(request);
        OfferCollection flightsCollection = client.offerService.getPage(offerResponse.getId(), null, null, null);
        return flightsCollection;
    }
}
