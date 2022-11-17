package edu.illinois.cs465.myquizapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import edu.illinois.cs465.myquizapp.pojo.Flight;

public class FlightKeeperDetailActivity extends AppCompatActivity {

    public static String collectionName;
    public String pageName = "details";
    public static List<Flight> flights = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flightkeeper_detail);

        getDetailView();

        TextView title = (TextView) findViewById(R.id.flight_keeper_title);
        title.setText(this.collectionName);

        ListView listView = (ListView) findViewById(R.id.flights);
        FlightListAdapter adapter = new FlightListAdapter(this, 0, flights);
        listView.setAdapter(adapter);

        Button configuration = findViewById(R.id.configuration);
        configuration.setOnClickListener((v) -> {
            Intent intent = new Intent(this, FlightkprConfigActivity.class);
            intent.putExtra("collectionName", collectionName);
            intent.putExtra("lastpage", pageName);
            startActivity(intent);
        });

        Button combination = findViewById(R.id.combination);
        combination.setOnClickListener((v) -> {
            Intent intent = new Intent(this, PlanActivity.class);
            intent.putExtra("collectionName", collectionName);
            startActivity(intent);
        });

    }

    private void getDetailView() {
        Intent detailView = getIntent();
        String collectionName = detailView.getStringExtra("collectionName");

        this.collectionName = collectionName;
        for (Flight f : Database.collections.get(collectionName)) {
            flights.add(f);
        }
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
