package edu.illinois.cs465.myquizapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Set;

public class PlanDetailActivity extends AppCompatActivity implements View.OnClickListener {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private String planName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);

        // get SharedPreferences
        sharedPreferences = getSharedPreferences("SharedPreferences", 0);
        editor = sharedPreferences.edit();
        Set<String> flightsId = sharedPreferences.getStringSet(planName, null);



    }

    @Override
    public void onClick(View view) {

    }
}
