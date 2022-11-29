package edu.illinois.cs465.myquizapp;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import edu.illinois.cs465.myquizapp.pojo.Flight;

public class FlightKeeperDetailActivity extends AppCompatActivity {

    public static String collectionName;
    public String pageName = "details";
    public List<Flight> flights = new ArrayList<>();
    public List<Integer> mediaList = new ArrayList<>();

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

        Button back = findViewById(R.id.back);
        back.setOnClickListener((v) -> {
            Intent intent = new Intent(this, FlightKeeperMainActivity.class);
            startActivity(intent);
        });

    }

    private void getDetailView() {
        Intent detailView = getIntent();
        String collectionName = detailView.getStringExtra("collectionName");

        this.collectionName = collectionName;
        if (Database.collections.containsKey(collectionName)) {
            for (Flight f : Database.collections.get(collectionName)) {
                flights.add(f);
                int random = getRandomNumber(1, 4);
                switch(random) {
                    case 1:
                        mediaList.add(R.drawable.media1);
                        break;
                    case 2:
                        mediaList.add(R.drawable.media2);
                        break;
                    case 3:
                        mediaList.add(R.drawable.media3);
                        break;
                    case 4:
                        mediaList.add(R.drawable.media4);
                        break;
                }
            }
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

            ImageView media = convertView.findViewById(R.id.media);
            media.setImageResource(mediaList.get(position));

            Button delete = convertView.findViewById(R.id.delete);
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Database.deleteFlightFromCollection(collectionName, flight);
                    showDeleteSuccessDialog();
                }
            });
            return convertView;
        }
    }

    void showDeleteSuccessDialog() {
        final Dialog dialog = new Dialog(FlightKeeperDetailActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.flightkeeper_flight_delete_success);
        dialog.show();

        Button saveButton = dialog.findViewById(R.id.ok);
        saveButton.setOnClickListener((v) -> {
            dialog.dismiss();
            this.recreate();
        });
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
