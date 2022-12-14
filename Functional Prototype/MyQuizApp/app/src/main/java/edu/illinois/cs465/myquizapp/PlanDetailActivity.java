package edu.illinois.cs465.myquizapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import edu.illinois.cs465.myquizapp.pojo.Combination;
import edu.illinois.cs465.myquizapp.pojo.Flight;


public class PlanDetailActivity extends AppCompatActivity implements View.OnClickListener{
    String collectionName;
    String combinationName;

    public List<Integer> mediaList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_detail);

        // get the info from the DB
        Combination selectCombination = (Combination) getIntent().getSerializableExtra("selectCombination");

        collectionName = getIntent().getStringExtra("collectionName");
        combinationName = selectCombination.getCombinationName();
        // add combinationName to the screen
        TextView title = findViewById(R.id.plan_title);
        title.setText(selectCombination.getCombinationName());

        Set<Flight> flightSet = selectCombination.getFlights();
        if(flightSet != null){
            ListView listView = (ListView) findViewById(R.id.flights);
            List<Flight> flights = new ArrayList<>();
            for(Flight f: flightSet){
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
            FlightListAdapter adapter = new FlightListAdapter(this, 0, flights);
            listView.setAdapter(adapter);
        }

        Button addNewFlight = findViewById(R.id.addPlanButton);
        addNewFlight.setOnClickListener(this);

        Button back = findViewById(R.id.back_btn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PlanDetailActivity.this, PlanActivity.class);
                intent.putExtra("collectionName", collectionName);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View view) {
        Intent addNewView = new Intent(this, PlanAddNewActivity.class);
        addNewView.putExtra("collectionName", collectionName);
        addNewView.putExtra("combinationName", combinationName);
        startActivity(addNewView);
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

            Button deleteButton = convertView.findViewById(R.id.delete);
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Database.deleteFlightInCombination(collectionName, combinationName, flight);
                }
            });

            if(flight != null){
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
            }else{
                convertView.setVisibility(View.GONE);
            }
            return convertView;
        }
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
