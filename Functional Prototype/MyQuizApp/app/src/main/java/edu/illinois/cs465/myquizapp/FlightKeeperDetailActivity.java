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

import edu.illinois.cs465.myquizapp.pojo.Flight;

public class FlightKeeperDetailActivity extends AppCompatActivity implements View.OnClickListener {

    public static List<Flight> flights;


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
        int collectionId = detailView.getIntExtra("id", 0);
        flights = new ArrayList<Flight>();
        flights.add(new Flight("1", "JFK", "CMI", "8:30am", "10:30am", "123", "AA", 2));
        flights.add(new Flight("1", "JFK", "CMI", "11:30am", "2:30pm", "250", "AA", 2));
        flights.add(new Flight("1", "JFK", "CMI", "2:30am", "5:30am", "100", "UA", 1));
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
