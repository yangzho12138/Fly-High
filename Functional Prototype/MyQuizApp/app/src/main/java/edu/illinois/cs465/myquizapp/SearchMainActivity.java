package edu.illinois.cs465.myquizapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;

import static java.lang.String.valueOf;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import edu.illinois.cs465.myquizapp.pojo.Collection;
import edu.illinois.cs465.myquizapp.pojo.Filter;
import edu.illinois.cs465.myquizapp.pojo.Flight;

public class SearchMainActivity extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener {
    String[] airline = { "Etihad", "American", "Delta"};
    String[] triptype = { "One-way", "Round", "Multi-city"};
    private NumberPicker picker1;

    Button date;
    DatePickerDialog datePickerDialog;

    BottomNavigationView btn_view;
    Button searchButton;
    Button collectionButton;
    private SearchView fromSearch;
    private SearchView toSearch;
    String fromValue="";
    String toValue="";
    String spin1Value="";
    String spin2Value="";
    String numberValue="";
    String dateValue="";



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_main);
        //Getting the instance of Spinner and applying OnItemSelectedListener on it

        // initiate the date picker and a button
        date = (Button) findViewById(R.id.date);
        btn_view = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        // perform click event on edit text
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(SearchMainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                date.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);
                                dateValue=dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year;

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });



//        Spinner spin1 = (Spinner) findViewById(R.id.spinner1);
//        spin1.setOnItemSelectedListener(this);
//
//        //Creating the ArrayAdapter instance having the country list
//        ArrayAdapter aa1 = new ArrayAdapter(this,R.layout.spinner_list,airline);
//        aa1.setDropDownViewResource(R.layout.spinner_list);
//        //Setting the ArrayAdapter data on the Spinner
//        spin1.setAdapter(aa1);
//
//        Spinner spin2 = (Spinner) findViewById(R.id.spinner2);
//        spin2.setOnItemSelectedListener(this);
//
//        //Creating the ArrayAdapter instance having the country list
//        ArrayAdapter aa2 = new ArrayAdapter(this,R.layout.spinner_list,triptype);
//        aa2.setDropDownViewResource(R.layout.spinner_list);
//        //Setting the ArrayAdapter data on the Spinner
//        spin2.setAdapter(aa2);

        picker1 = findViewById(R.id.picker);

        picker1.setMaxValue(9);
        picker1.setMinValue(1);

        fromSearch = (SearchView) findViewById(R.id.fromSearch);
        toSearch = (SearchView) findViewById(R.id.toSearch);

        searchButton = (Button) findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fromValue = fromSearch.getQuery().toString();
                toValue = toSearch.getQuery().toString();
//                spin1Value = spin1.getSelectedItem().toString();
//                spin2Value = spin2.getSelectedItem().toString();
                numberValue = valueOf(picker1.getValue());
                openNewActivity();
            }
        });

        List<Collection> collections = new ArrayList<>();
        int recentlyViewed = 0;
        for (Map.Entry entry : Database.collections.entrySet()) {
            recentlyViewed ++;
            String collectionName = entry.getKey().toString();
            if (Database.status.containsKey(collectionName)) {
                Integer numOfPlans = Database.status.get(collectionName).getPlanNum();
                String lowestPrice = Database.status.get(collectionName).getLowestPrice();
                collections.add(new Collection(collectionName, numOfPlans, lowestPrice));
            }
            else {
                collections.add(new Collection(collectionName, 0, "N/A"));
            }
            if(recentlyViewed == 3){
                break;
            }
        }
        ListView listView = (ListView) findViewById(R.id.prev_collection);
        CollectionListAdapter adapter = new CollectionListAdapter(this, 0, collections);
        listView.setAdapter(adapter);

        collectionButton = findViewById(R.id.collectionButton);
        collectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SearchMainActivity.this, FlightKeeperMainActivity.class));
            }
        });

        btn_view.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id){
                    //check id

                    case R.id.action_search:
                        Intent intent1 = new Intent(SearchMainActivity.this, SearchMainActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.action_combination:
                        Intent intent2 = new Intent(SearchMainActivity.this, FlightKeeperMainActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.action_trash:
                        Intent intent3 = new Intent(SearchMainActivity.this, TrashActivity.class);
                        startActivity(intent3);
                        break;
                }
                return true;
            }
        });

        ImageButton help = findViewById(R.id.help);
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchMainActivity.this, HelpActivity.class);
                startActivity(intent);
            }
        });

        ImageButton map = findViewById(R.id.map);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // run Google Map API
            }
        });
    }

    //Performing action onItemSelected and onNothing selected
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        //Toast.makeText(getApplicationContext(),airline[position] , Toast.LENGTH_LONG).show();
        //Toast.makeText(getApplicationContext(),triptype[position] , Toast.LENGTH_LONG).show();
    }
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
    public void openNewActivity(){
        Intent intent = new Intent(this, SearchResultsActivity.class);
        intent.putExtra("from", fromValue);
        intent.putExtra("to", toValue);
        intent.putExtra("airline", spin1Value);
        intent.putExtra("date", dateValue);
        intent.putExtra("triptype", spin2Value);
        intent.putExtra("number", numberValue);
        startActivity(intent);
    }

    class CollectionListAdapter extends ArrayAdapter<Collection> {

        CollectionListAdapter (Context context, int resource, List<Collection> collections) {
            super(context, resource, collections);
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Collection collection = getItem(position);
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.flightkeeper_search_card, parent, false);
            }

            TextView collectionName = convertView.findViewById(R.id.collection_name);
            collectionName.setText(String.valueOf(collection.getCollectionName()));
            TextView numOfPlan = convertView.findViewById(R.id.plan_num);
            numOfPlan.setText(String.valueOf(collection.getNumOfPlan()));
            TextView lowestPrice = convertView.findViewById(R.id.lowest_price);
            lowestPrice.setText(collection.getLowestPrice());

            String selectedCollection = collection.getCollectionName();

            Button autofill = convertView.findViewById(R.id.autofill);
            autofill.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Filter filter = Database.autoFilter.get(selectedCollection);
                    if(filter != null){
                        fromSearch.onActionViewExpanded();
                        fromSearch.setQuery(filter.getOrigin(),false);
                        fromSearch.clearFocus();

                        toSearch.onActionViewExpanded();
                        toSearch.setQuery(filter.getDestination(),false);
                        toSearch.clearFocus();

                        showCustomDialog(collection.getCollectionName());
                    }
                }
            });

            Button detail = convertView.findViewById(R.id.detail);
            detail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent detailPage = new Intent(SearchMainActivity.this, FlightKeeperDetailActivity.class);
                    detailPage.putExtra("collectionName", selectedCollection);
                    startActivity(detailPage);
                }
            });

            return convertView;
        }
    }

    void showCustomDialog(String collectionName) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.autofill_reminder);
        dialog.show();

        Filter filter = Database.autoFilter.get(collectionName);

        if(!filter.getOrigin().equals("")){
            TextView text = dialog.findViewById(R.id.origin);
            text.setText("origin: " + filter.getOrigin());
        }
        if(!filter.getDestination().equals("")){
            TextView text = dialog.findViewById(R.id.destination);
            text.setText("destination: " + filter.getDestination());
        }
        if(filter.getStops() != null){
            TextView text = dialog.findViewById(R.id.stops);
            text.setText("stops: " + filter.getStops());
        }
        if(filter.getBags() != null){
            TextView text = dialog.findViewById(R.id.bags);
            text.setText("bags: " + filter.getBags());
        }
        if(filter.getDuration() != null){
            TextView text = dialog.findViewById(R.id.duration);
            text.setText("duration: " + filter.getDuration());
        }
        if(filter.getAdult_cnt() != null){
            TextView text = dialog.findViewById(R.id.adult);
            text.setText("adult number: " + filter.getAdult_cnt());
        }
        if(filter.getChildren_cnt() != null){
            TextView text = dialog.findViewById(R.id.children);
            text.setText("children number: " + filter.getChildren_cnt());
        }
        if(filter.getInfant_cnt() != null){
            TextView text = dialog.findViewById(R.id.infant);
            text.setText("infant number: " + filter.getInfant_cnt());
        }
        if(filter.getLowPrice() != 0 && filter.getHighPrice() != 0){
            TextView text = dialog.findViewById(R.id.price);
            text.setText("price scope: " + filter.getLowPrice() +" - " + filter.getHighPrice());
        }

        Button success = dialog.findViewById(R.id.begin_search);
        success.setOnClickListener((v) -> {
            dialog.dismiss();
        });
    }
}
