package edu.illinois.cs465.myquizapp;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.textfield.TextInputEditText;

import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import edu.illinois.cs465.myquizapp.pojo.Flight;

public class SearchResultsActivity extends AppCompatActivity {
    private Button d1;
    private Button d2;
    private Button d3;
    private Button d4;
    private Button d5;
    private Button d6;
    private Button d7;
    private Button d8;
    private Button back_btn;
    Activity activity = this;

    List<Flight> flights = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        String from = getIntent().getStringExtra("from");
        String to = getIntent().getStringExtra("to");

        TextView fromTo = findViewById(R.id.from_to);
        fromTo.setText("From " + from + " To " + to);

        ListView listView = (ListView) findViewById(R.id.search_flights);

        if(from.equals("Chicago") && to.equals("Boston")){
            flights.add(new Flight("1", "Chicago", "Boston", "8:30am", "10:30am", "$123", "AA", 2));
            flights.add(new Flight("2", "Chicago", "Boston", "11:30am", "2:30pm", "$250", "AA", 2));
            flights.add(new Flight("3", "Chicago", "Boston", "2:30am", "5:30am", "$100", "UA", 1));
        }else if(from.equals("Chicago") && to.equals("Houston")){
            flights.add(new Flight("4", "Chicago", "Houston", "6:30am", "9:30am", "$199", "AA", 2));
            flights.add(new Flight("5", "Chicago", "Houston", "1:30am", "3:30am", "$220", "AA", 2));
            flights.add(new Flight("6", "Chicago", "Houston", "10:30am", "12:30pm", "$170", "UA", 1));
        }else if(from.equals("Houston") && to.equals("Boston")){
            flights.add(new Flight("7", "Houston", "Boston", "4:32am", "7:30am", "$273", "AA", 2));
            flights.add(new Flight("8", "Houston", "Boston", "7:46pm", "9:30pm", "$190", "AA", 2));
            flights.add(new Flight("9", "Houston", "Boston", "5:40am", "7:50am", "$322", "UA", 1));
        }


        FlightListAdapter adapter = new FlightListAdapter(this, 0, flights);
        listView.setAdapter(adapter);

        d1 = (Button) findViewById(R.id.button1);
        d2 = (Button) findViewById(R.id.button2);
        d3 = (Button) findViewById(R.id.button3);
        d4 = (Button) findViewById(R.id.button4);
        d5 = (Button) findViewById(R.id.button5);
        d6 = (Button) findViewById(R.id.button6);
        d7 = (Button) findViewById(R.id.button7);
        d8 = (Button) findViewById(R.id.button8);

        d1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                d1.setBackgroundTintList(getResources().getColorStateList(R.color.click_color));
                d2.setBackgroundTintList(getResources().getColorStateList(R.color.unclick_color));
                d3.setBackgroundTintList(getResources().getColorStateList(R.color.unclick_color));
                d4.setBackgroundTintList(getResources().getColorStateList(R.color.unclick_color));
                d5.setBackgroundTintList(getResources().getColorStateList(R.color.unclick_color));
                d6.setBackgroundTintList(getResources().getColorStateList(R.color.unclick_color));
                d7.setBackgroundTintList(getResources().getColorStateList(R.color.unclick_color));
                d8.setBackgroundTintList(getResources().getColorStateList(R.color.unclick_color));
            }
        });

        d2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                d2.setBackgroundTintList(getResources().getColorStateList(R.color.click_color));
                d1.setBackgroundTintList(getResources().getColorStateList(R.color.unclick_color));
                d3.setBackgroundTintList(getResources().getColorStateList(R.color.unclick_color));
                d4.setBackgroundTintList(getResources().getColorStateList(R.color.unclick_color));
                d5.setBackgroundTintList(getResources().getColorStateList(R.color.unclick_color));
                d6.setBackgroundTintList(getResources().getColorStateList(R.color.unclick_color));
                d7.setBackgroundTintList(getResources().getColorStateList(R.color.unclick_color));
                d8.setBackgroundTintList(getResources().getColorStateList(R.color.unclick_color));
            }
        });

        d3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                d3.setBackgroundTintList(getResources().getColorStateList(R.color.click_color));
                d1.setBackgroundTintList(getResources().getColorStateList(R.color.unclick_color));
                d2.setBackgroundTintList(getResources().getColorStateList(R.color.unclick_color));
                d4.setBackgroundTintList(getResources().getColorStateList(R.color.unclick_color));
                d5.setBackgroundTintList(getResources().getColorStateList(R.color.unclick_color));
                d6.setBackgroundTintList(getResources().getColorStateList(R.color.unclick_color));
                d7.setBackgroundTintList(getResources().getColorStateList(R.color.unclick_color));
                d8.setBackgroundTintList(getResources().getColorStateList(R.color.unclick_color));
            }
        });

        d4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                d4.setBackgroundTintList(getResources().getColorStateList(R.color.click_color));
                d1.setBackgroundTintList(getResources().getColorStateList(R.color.unclick_color));
                d2.setBackgroundTintList(getResources().getColorStateList(R.color.unclick_color));
                d3.setBackgroundTintList(getResources().getColorStateList(R.color.unclick_color));
                d5.setBackgroundTintList(getResources().getColorStateList(R.color.unclick_color));
                d6.setBackgroundTintList(getResources().getColorStateList(R.color.unclick_color));
                d7.setBackgroundTintList(getResources().getColorStateList(R.color.unclick_color));
                d8.setBackgroundTintList(getResources().getColorStateList(R.color.unclick_color));
            }
        });

        d5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                d5.setBackgroundTintList(getResources().getColorStateList(R.color.click_color));
                d1.setBackgroundTintList(getResources().getColorStateList(R.color.unclick_color));
                d2.setBackgroundTintList(getResources().getColorStateList(R.color.unclick_color));
                d3.setBackgroundTintList(getResources().getColorStateList(R.color.unclick_color));
                d4.setBackgroundTintList(getResources().getColorStateList(R.color.unclick_color));
                d6.setBackgroundTintList(getResources().getColorStateList(R.color.unclick_color));
                d7.setBackgroundTintList(getResources().getColorStateList(R.color.unclick_color));
                d8.setBackgroundTintList(getResources().getColorStateList(R.color.unclick_color));
            }
        });

        d6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                d6.setBackgroundTintList(getResources().getColorStateList(R.color.click_color));
                d1.setBackgroundTintList(getResources().getColorStateList(R.color.unclick_color));
                d2.setBackgroundTintList(getResources().getColorStateList(R.color.unclick_color));
                d3.setBackgroundTintList(getResources().getColorStateList(R.color.unclick_color));
                d4.setBackgroundTintList(getResources().getColorStateList(R.color.unclick_color));
                d5.setBackgroundTintList(getResources().getColorStateList(R.color.unclick_color));
                d7.setBackgroundTintList(getResources().getColorStateList(R.color.unclick_color));
                d8.setBackgroundTintList(getResources().getColorStateList(R.color.unclick_color));
            }
        });

        d7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                d7.setBackgroundTintList(getResources().getColorStateList(R.color.click_color));
                d1.setBackgroundTintList(getResources().getColorStateList(R.color.unclick_color));
                d2.setBackgroundTintList(getResources().getColorStateList(R.color.unclick_color));
                d3.setBackgroundTintList(getResources().getColorStateList(R.color.unclick_color));
                d4.setBackgroundTintList(getResources().getColorStateList(R.color.unclick_color));
                d5.setBackgroundTintList(getResources().getColorStateList(R.color.unclick_color));
                d6.setBackgroundTintList(getResources().getColorStateList(R.color.unclick_color));
                d8.setBackgroundTintList(getResources().getColorStateList(R.color.unclick_color));
            }
        });

        d8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                d8.setBackgroundTintList(getResources().getColorStateList(R.color.click_color));
                d1.setBackgroundTintList(getResources().getColorStateList(R.color.unclick_color));
                d2.setBackgroundTintList(getResources().getColorStateList(R.color.unclick_color));
                d3.setBackgroundTintList(getResources().getColorStateList(R.color.unclick_color));
                d4.setBackgroundTintList(getResources().getColorStateList(R.color.unclick_color));
                d5.setBackgroundTintList(getResources().getColorStateList(R.color.unclick_color));
                d6.setBackgroundTintList(getResources().getColorStateList(R.color.unclick_color));
                d7.setBackgroundTintList(getResources().getColorStateList(R.color.unclick_color));

            }
        });

        back_btn = (Button) findViewById(R.id.back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchResultsActivity.this, SearchMainActivity.class);
                startActivity(intent);
            }
        });


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
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.search_result_flights_card, parent, false);
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

            ImageButton btn1 = convertView.findViewById(R.id.button1);
            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // purchase
                }
            });
            ImageButton btn2 = convertView.findViewById(R.id.button2);
            btn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // save to collection
                    showAddToCollectionDialog(flight);
                }
            });

            return convertView;
        }
    }

    void showAddToCollectionDialog(Flight flight) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.popup_window);

        List<String> collectionNames = new ArrayList<>();
        for (Map.Entry entry : Database.collections.entrySet()) {
            collectionNames.add(entry.getKey().toString());
        }

        TextView emptyMessage = dialog.findViewById(R.id.message);
        if (Database.collections.size() != 0) {
            emptyMessage.setVisibility(View.GONE);
        }

        ListView listView = dialog.findViewById(R.id.select_collections);
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.search_result_add_to_collection_card, R.id.collection_name, collectionNames);
        listView.setAdapter(adapter);

        dialog.show();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // add flight to a specific collection
                String collectionName = listView.getItemAtPosition(position).toString();
                Database.addFlightToCollection(collectionName, flight);
                showCustomDialog();
            }
        });


        Button addButton = dialog.findViewById(R.id.add_new_collection);
        addButton.setOnClickListener((v) -> {
            // add a new collection
            showAddNewCollectionDialog(flight);
            dialog.dismiss();
        });

        Button closeButton = dialog.findViewById(R.id.button_delete);
        closeButton.setOnClickListener((v) -> {
            dialog.dismiss();
        });
    }

    void showCustomDialog() {
        final Dialog dialog = new Dialog(this);
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

    void showAddNewCollectionDialog(Flight flight) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.flightkeeper_addnew);
        dialog.show();

        TextInputEditText text = dialog.findViewById(R.id.textField);
        Button saveButton = dialog.findViewById(R.id.save);
        saveButton.setOnClickListener((v) -> {
            String name = text.getText().toString();
            Database.addCollection(name);
            dialog.dismiss();
            showAddToCollectionDialog(flight);
        });
    }


}