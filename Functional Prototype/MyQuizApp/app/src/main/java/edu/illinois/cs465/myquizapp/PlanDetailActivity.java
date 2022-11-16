package edu.illinois.cs465.myquizapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import edu.illinois.cs465.myquizapp.pojo.Combination;
import edu.illinois.cs465.myquizapp.pojo.Flight;


public class PlanDetailActivity extends AppCompatActivity implements View.OnClickListener{
    private String planName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_detail);

        // get the info from the DB
        Combination selectCombination = (Combination) getIntent().getSerializableExtra("selectCombination");
        System.out.println(selectCombination);
        // add combinationName to the screen
        TextView title = findViewById(R.id.plan_title);
        title.setText(selectCombination.getCombinationName());

        ListView listView = (ListView) findViewById(R.id.flights);
        Set<Flight> flightSet = selectCombination.getFlights();
        List<Flight> flights = new ArrayList<>();
        for(Flight f: flightSet){
            flights.add(f);
        }
        FlightListAdapter adapter = new FlightListAdapter(this, 0, flights);
        listView.setAdapter(adapter);

    }

    @Override
    public void onClick(View view) {

    }

    class FlightListAdapter extends ArrayAdapter<Flight> {

        FlightListAdapter (Context context, int resource, List<Flight> flights) {
            super(context, resource, flights);
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Flight flight = getItem(position);
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.flightkeeper_detail_card, parent, false);
            }

            TextView departureTime = convertView.findViewById(R.id.departure_time);
            departureTime.setText(flight.getDepartureTime());
            TextView arrivalTime = convertView.findViewById(R.id.arrive_time);
            arrivalTime.setText(flight.getArriveTime());
            TextView origin = convertView.findViewById(R.id.origin);
            origin.setText(flight.getOrigin());
            TextView destination = convertView.findViewById(R.id.destination);
            destination.setText(flight.getDestination());
            TextView airline = convertView.findViewById(R.id.airline);
            airline.setText(flight.getAirline());
            TextView totalPrice = convertView.findViewById(R.id.total_price);
            totalPrice.setText(flight.getTotalPrice());
            return convertView;
        }
    }
}
