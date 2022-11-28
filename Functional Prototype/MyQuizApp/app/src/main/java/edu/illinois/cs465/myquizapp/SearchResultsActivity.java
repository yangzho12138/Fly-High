package edu.illinois.cs465.myquizapp;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import edu.illinois.cs465.myquizapp.databinding.ActivitySearchResultsBinding;
import static edu.illinois.cs465.myquizapp.Database.addFlightToCollection;
import static edu.illinois.cs465.myquizapp.Database.collections;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.Calendar;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import edu.illinois.cs465.myquizapp.pojo.Flight;

public class SearchResultsActivity extends AppCompatActivity {

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
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_window, null);

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = false;
        //boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            popupWindow.setElevation(100);
        }
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window tolken
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        Button close = (Button) popupView.findViewById(R.id.button);
        close.setOnClickListener(new View.OnClickListener() {
            public void onClick(View popupView) {
                popupWindow.dismiss();
            }
        });

        for(Map.Entry<String, Set<Flight>> entry: collections.entrySet()){
            Log.d("Message:",entry.getKey());
        }

        Flight newFlight = new Flight("1", "CMI", "JFK", "2:40pm", "9:30pm", "430", "AA", 2);

        addFlightToCollection("My Trip to Champaign",newFlight);
    }

    public void addFunc2(View view) {
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_window, null);

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = false;
        //boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            popupWindow.setElevation(100);
        }
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window tolken
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        Button close = (Button) popupView.findViewById(R.id.button);
        close.setOnClickListener(new View.OnClickListener() {
            public void onClick(View popupView) {
                popupWindow.dismiss();
            }
        });

        Flight newFlight = new Flight("1", "CMI", "JFK", "3:10pm", "10:00pm", "375", "AA", 2);

        addFlightToCollection("My Trip to Champaign",newFlight);

    }

    public void addFunc3(View view) {
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_window, null);

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = false;
        //boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            popupWindow.setElevation(100);
        }
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window tolken
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        Button close = (Button) popupView.findViewById(R.id.button);
        close.setOnClickListener(new View.OnClickListener() {
            public void onClick(View popupView) {
                popupWindow.dismiss();
            }
        });

        Flight newFlight = new Flight("1", "CMI", "JFK", "3:40pm", "10:30pm", "510", "AA", 2);

        addFlightToCollection("My Trip to Champaign",newFlight);
    }

    public void addToCollection(View view) {
        Log.d("Message:", "Flight Added.");
        Toast.makeText(getApplicationContext(),"Flight has been added successfully!",Toast.LENGTH_SHORT).show();
        //Flight newFlight = new Flight("1", "CMI", "JFK", "2:40pm", "9:30pm", "430", "AA", 2);
        //addFlightToCollection("My Trip to Champaign",newFlight);
    }
}