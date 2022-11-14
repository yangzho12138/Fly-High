package edu.illinois.cs465.myquizapp;

import static android.content.Context.MODE_PRIVATE;

import org.junit.Test;

import static org.junit.Assert.*;

import com.duffel.model.OfferCollection;
import com.duffel.model.response.Offer;
import com.duffel.model.response.OfferResponse;

import java.util.List;

import edu.illinois.cs465.myquizapp.utils.GetFlights;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testUtil(){
        OfferCollection res = GetFlights.searchFlights("NYC","ATL","round_trip","2022-12-01","2022-12-05", "adult", null, "economy");
        List data = res.getData();
        for(Object d: data){
            Offer offer = (Offer) d;
            System.out.println();
        }
    }
}