package edu.illinois.cs465.projectmainpage;

import static edu.illinois.cs465.projectmainpage.Database.addFlightToCollection;
import static edu.illinois.cs465.projectmainpage.Database.collections;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.Calendar;

import edu.illinois.cs465.projectmainpage.databinding.ActivitySearchResultsBinding;
import edu.illinois.cs465.projectmainpage.pojo.Flight;

public class SearchResults extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivitySearchResultsBinding binding;

    private TextView fromText;
    private TextView toText;
    private TextView airlineText;
    private TextView dateText;
    private TextView triptypeText;
    private TextView numberText;

    ImageButton add;

    String value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySearchResultsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        fromText = (TextView) findViewById(R.id.from);
        toText = (TextView) findViewById(R.id.to);
        dateText = (TextView) findViewById(R.id.date);
        triptypeText = (TextView) findViewById(R.id.triptype);
        airlineText = (TextView) findViewById(R.id.airline);
        numberText = (TextView) findViewById(R.id.number);


        Bundle extras = getIntent().getExtras();
        String from = extras.getString("from");
        String to = extras.getString("to");
        String date = extras.getString("date");
        String airline = extras.getString("airline");
        String triptype = extras.getString("triptype");
        String number = extras.getString("number");

        fromText.setText("From: "+from);
        toText.setText("To: "+to);
        dateText.setText("Date: "+date);
        airlineText.setText("Airline: "+airline);
        triptypeText.setText("Trip Type: "+triptype);
        numberText.setText("Number of Passengers: "+number);
    }

    public void addFunc1(View view) {
        Log.d("Message:", "Flight Added.");
        Flight newFlight = new Flight("1", "CMI", "JFK", "2:40pm", "9:30pm", "430", "AA", 2);

        addFlightToCollection("My Trip to Champaign",newFlight);
    }

    public void addFunc2(View view) {
        Log.d("Message:", "Flight Added.");
        Flight newFlight = new Flight("1", "CMI", "JFK", "3:10pm", "10:00pm", "375", "AA", 2);

        addFlightToCollection("My Trip to Champaign",newFlight);
    }

    public void addFunc3(View view) {
        Log.d("Message:", "Flight Added.");
        Flight newFlight = new Flight("1", "CMI", "JFK", "3:40pm", "10:30pm", "510", "AA", 2);

        addFlightToCollection("My Trip to Champaign",newFlight);
    }
}