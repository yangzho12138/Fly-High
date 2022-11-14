package edu.illinois.cs465.myquizapp;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;


public class PlanDetailActivity extends AppCompatActivity implements View.OnClickListener {
    private String planName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_detail);




    }

    @Override
    public void onClick(View view) {

    }
}
