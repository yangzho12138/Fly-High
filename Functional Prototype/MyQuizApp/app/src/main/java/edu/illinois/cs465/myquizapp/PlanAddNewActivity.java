package edu.illinois.cs465.myquizapp;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import edu.illinois.cs465.myquizapp.pojo.Combination;
import edu.illinois.cs465.myquizapp.pojo.Flight;

public class PlanAddNewActivity extends AppCompatActivity implements View.OnClickListener{
    String collectionName;
    String combinationName;
    Combination selectCombination = new Combination(null, null);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_add_new_detail);

        collectionName = getIntent().getStringExtra("collectionName");
        combinationName = getIntent().getStringExtra("combinationName");

        TextView title = findViewById(R.id.add_flight_in_plan_title);
        title.setText("Flights in " + collectionName);

        Set<Flight> flightSet = Database.collections.get(collectionName);
        if(flightSet != null){
            ListView listView = (ListView) findViewById(R.id.flights);
            List<Flight> flights = new ArrayList<>();
            for(Flight f: flightSet){
                flights.add(f);
            }
            FlightListAdapter adapter = new FlightListAdapter(this, 0, flights);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    Flight selectFlight = (Flight) listView.getItemAtPosition(position);
                    System.out.println(selectFlight);
                    Map<String, Set<Flight>> selectCombination = Database.combinationsInCollection.get(collectionName);
                    Set<Flight> f = selectCombination.get(combinationName);
                    f.add(selectFlight);
                    selectCombination.put(combinationName, f);
                    PlanAddNewActivity.this.selectCombination.setCombinationName(combinationName);
                    PlanAddNewActivity.this.selectCombination.setFlights(f);
                    Database.combinationsInCollection.put(combinationName, selectCombination);
                    showCustomDialog();
                }
            });
        }

        Button backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(this);
    }

    void showCustomDialog() {
        final Dialog dialog = new Dialog(PlanAddNewActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.plan_add_success);
        dialog.show();

        TextInputEditText text = dialog.findViewById(R.id.textField);
        Button saveButton = dialog.findViewById(R.id.save);
        saveButton.setOnClickListener((v) -> {
            dialog.dismiss();
        });
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, PlanDetailActivity.class);
        intent.putExtra("collectionName", collectionName);
        intent.putExtra("selectCombination", this.selectCombination);
        startActivity(intent);
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

            ImageButton btn1 = convertView.findViewById(R.id.button1);
            btn1.setVisibility(View.GONE);
            ImageButton btn2 = convertView.findViewById(R.id.button2);
            btn2.setVisibility(View.GONE);

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
            }
            return convertView;
        }
    }
}
