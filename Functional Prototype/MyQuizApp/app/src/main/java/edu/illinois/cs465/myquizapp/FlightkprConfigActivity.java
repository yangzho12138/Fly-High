package edu.illinois.cs465.myquizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import edu.illinois.cs465.myquizapp.FlightKeeperDetailActivity;
import edu.illinois.cs465.myquizapp.R;

import edu.illinois.cs465.myquizapp.pojo.Filter;
import edu.illinois.cs465.myquizapp.Database;


public class FlightkprConfigActivity extends AppCompatActivity implements View.OnClickListener {
    private Button advanced_op;
    private Button confirm;
    private EditText from_location;
    private EditText to_location;
    private EditText lower_bound;
    private EditText upper_bound;

    private Integer bags;
    private Integer stops;
    private Integer duration;
    private Integer adult_cnt;
    private Integer children_cnt;
    private Integer infant_cnt;
    private String collectionName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flightkeeper_config);


        advanced_op = (Button) findViewById(R.id.advanced_op);
        advanced_op.setOnClickListener(this);

        confirm = (Button) findViewById(R.id.confirm);
        confirm.setOnClickListener(this);

        from_location = (EditText)findViewById(R.id.from_location);
        to_location = (EditText)findViewById(R.id.to_location);
        lower_bound = (EditText)findViewById(R.id.lower);
        upper_bound = (EditText)findViewById(R.id.upper);

        Intent intent = getIntent();
        if (intent.getStringExtra("lastpage").equals("details")) {
            collectionName = intent.getStringExtra("collectionName");
        }
        else if (intent.getStringExtra("lastpage").equals("advanced")) {
            bags = intent.getIntExtra("bags", 0);
            stops = intent.getIntExtra("stops", 0);
            duration = intent.getIntExtra("duration", 0);
            adult_cnt = intent.getIntExtra("adult_cnt", 0);
            children_cnt = intent.getIntExtra("children_cnt", 0);
            infant_cnt = intent.getIntExtra("infant_cnt", 0);
            collectionName = intent.getStringExtra("collectionName");
        }

        if (Database.autoFilter.containsKey(collectionName)){
            from_location.setText(Database.autoFilter.get(collectionName).origin);
            to_location.setText(Database.autoFilter.get(collectionName).destination);
            lower_bound.setText(Database.autoFilter.get(collectionName).lowPrice.toString());
            upper_bound.setText(Database.autoFilter.get(collectionName).highPrice.toString());
        }


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.advanced_op) {
            Intent intent = new Intent(FlightkprConfigActivity.this, AdvancedActivity.class);
            if (lower_bound.getText().toString().equals(""))
                lower_bound.setText("0");
            if (upper_bound.getText().toString().equals(""))
                upper_bound.setText("0");
            Integer low = Integer.parseInt(lower_bound.getText().toString());
            Integer upper = Integer.parseInt(upper_bound.getText().toString());
            String from = from_location.getText().toString();
            String to = to_location.getText().toString();
            intent.putExtra("collectionName", collectionName);

            if (!Database.autoFilter.containsKey(collectionName)){

                Filter curr_filter = new Filter();
                curr_filter.origin = from;
                curr_filter.destination = to;
                curr_filter.lowPrice = low;
                curr_filter.highPrice = upper;

                Database.addAutoFilter(collectionName, curr_filter);
            }


            startActivity(intent);
        }
        else if (v.getId() == R.id.confirm){
            if (lower_bound.getText().toString().equals(""))
                lower_bound.setText("0");
            if (upper_bound.getText().toString().equals(""))
                upper_bound.setText("0");
            Integer low = Integer.parseInt(lower_bound.getText().toString());
            Integer upper = Integer.parseInt(upper_bound.getText().toString());
            String from = from_location.getText().toString();
            String to = to_location.getText().toString();


            Filter curr_filter = new Filter();
            curr_filter.origin = from;
            curr_filter.destination = to;
            curr_filter.duration = duration;
            curr_filter.lowPrice = low;
            curr_filter.highPrice = upper;
            curr_filter.infant_cnt = infant_cnt;
            curr_filter.children_cnt = children_cnt;
            curr_filter.adult_cnt = adult_cnt;
            curr_filter.bags = bags;
            curr_filter.stops = stops;

            // TODO: how to get the autoFilter instance in database, then do mydb.addAutoFilter(curr_filter)
            if (Database.autoFilter.containsKey(collectionName)){
                Database.autoFilter.get(collectionName).origin = from;
                Database.autoFilter.get(collectionName).destination = to;
                Database.autoFilter.get(collectionName).stops = stops;
                Database.autoFilter.get(collectionName).bags = bags;
                Database.autoFilter.get(collectionName).lowPrice = low;
                Database.autoFilter.get(collectionName).highPrice = upper;
            }
            else{
                Database.addAutoFilter(collectionName, curr_filter);
            }



            Intent intent = new Intent(FlightkprConfigActivity.this, FlightKeeperDetailActivity.class);
            intent.putExtra("lower_bound", low);
            intent.putExtra("upper_bound", upper);
            intent.putExtra("from_location", from);
            intent.putExtra("to_location", to);
            intent.putExtra("collectionName", collectionName);
            startActivity(intent);

        }
    }
}
