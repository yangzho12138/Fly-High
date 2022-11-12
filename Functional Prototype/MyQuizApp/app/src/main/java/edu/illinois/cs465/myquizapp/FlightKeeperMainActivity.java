package edu.illinois.cs465.myquizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class FlightKeeperMainActivity extends AppCompatActivity implements View.OnClickListener  {

    String flightKeeperName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flightkeeper_main);
    }

    @Override
    public void onClick(View view) {
        flightKeeperName = "My Trip to New York";
        Intent intent = new Intent(this, FlightKeeperDetailActivity.class);
        intent.putExtra("flightKeeperName", flightKeeperName);
        startActivity(intent);
    }
}
