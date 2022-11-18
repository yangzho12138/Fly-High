package edu.illinois.cs465.projectmainpage;

import static java.lang.String.valueOf;

import static edu.illinois.cs465.projectmainpage.Database.addFlightToCollection;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.NumberPicker;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;


import java.util.Calendar;

import edu.illinois.cs465.projectmainpage.pojo.Flight;

public class MainActivity extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener {
    String[] airline = { "Etihad", "American", "Delta"};
    String[] triptype = { "One-way", "Round", "Multi-city"};
    private NumberPicker picker1;

    Button date;
    DatePickerDialog datePickerDialog;

    Button searchButton;
    private SearchView fromSearch;
    private SearchView toSearch;
    String fromValue="";
    String toValue="";
    String spin1Value="";
    String spin2Value="";
    String numberValue="";
    String dateValue="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Getting the instance of Spinner and applying OnItemSelectedListener on it

        // initiate the date picker and a button
        date = (Button) findViewById(R.id.date);
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
                datePickerDialog = new DatePickerDialog(MainActivity.this,
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


        Spinner spin1 = (Spinner) findViewById(R.id.spinner1);
        spin1.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa1 = new ArrayAdapter(this,R.layout.spinner_list,airline);
        aa1.setDropDownViewResource(R.layout.spinner_list);
        //Setting the ArrayAdapter data on the Spinner
        spin1.setAdapter(aa1);

        Spinner spin2 = (Spinner) findViewById(R.id.spinner2);
        spin2.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa2 = new ArrayAdapter(this,R.layout.spinner_list,triptype);
        aa2.setDropDownViewResource(R.layout.spinner_list);
        //Setting the ArrayAdapter data on the Spinner
        spin2.setAdapter(aa2);

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
                spin1Value = spin1.getSelectedItem().toString();
                spin2Value = spin2.getSelectedItem().toString();
                numberValue = valueOf(picker1.getValue());
                openNewActivity();
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
        Intent intent = new Intent(this, SearchResults.class);
        intent.putExtra("from", fromValue);
        intent.putExtra("to", toValue);
        intent.putExtra("airline", spin1Value);
        intent.putExtra("date", dateValue);
        intent.putExtra("triptype", spin2Value);
        intent.putExtra("number", numberValue);
        startActivity(intent);
    }

    public void newYorkAutofill(View view) {
        Log.d("Message:", "Autofilled.");
        fromSearch.onActionViewExpanded();
        fromSearch.setQuery("CMI",false);
        fromSearch.clearFocus();

        toSearch.onActionViewExpanded();
        toSearch.setQuery("JFK",false);
        toSearch.clearFocus();

        Spinner spin1 = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter aa1 = new ArrayAdapter(this,R.layout.spinner_list,airline);
        aa1.setDropDownViewResource(R.layout.spinner_list);
        spin1.setAdapter(aa1);
        spin1.setSelection(aa1.getPosition("American"));
        spin1Value = spin1.getSelectedItem().toString();

        Spinner spin2 = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter aa2 = new ArrayAdapter(this,R.layout.spinner_list,triptype);
        aa2.setDropDownViewResource(R.layout.spinner_list);
        spin2.setAdapter(aa2);
        spin2.setSelection(aa2.getPosition("One-way"));
        spin2Value = spin2.getSelectedItem().toString();
    }
}

