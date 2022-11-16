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
import java.util.Map;
import java.util.Set;

import edu.illinois.cs465.myquizapp.pojo.Flight;

public class FlightKeeperDetailActivity extends AppCompatActivity implements View.OnClickListener {

    public static List<Flight> flights = new ArrayList<>();

    public static Map<String, Set<Flight>> collections = Database.collections;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flightkeeper_detail);

        getDetailView();

        ListView listView = (ListView) findViewById(R.id.flights);
        FlightListAdapter adapter = new FlightListAdapter(this, 0, flights);
        listView.setAdapter(adapter);

    }

    private void getDetailView() {
        Intent detailView = getIntent();
        String collectionName = detailView.getStringExtra("collectionName");
        for (Flight f : collections.get(collectionName)) {
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

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.back) {
            Intent mainView = new Intent(this, FlightKeeperMainActivity.class);
            startActivity(mainView);
        }
    }
    
}
