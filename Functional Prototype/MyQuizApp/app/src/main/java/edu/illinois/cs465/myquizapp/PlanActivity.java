package edu.illinois.cs465.myquizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PlanActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_main);

        // get the info from the
        Intent intent = new Intent();

        // add plan info dynamically
        LinearLayout.LayoutParams plan = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout view = new LinearLayout(this);
        view.setLayoutParams(plan);
        view.setOrientation(LinearLayout.HORIZONTAL);
        ViewGroup.LayoutParams plans = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        TextView tv1 = new TextView(this);
        tv1.setLayoutParams(plans);
        tv1.setText("12345");
        view.addView(tv1);
    }

    @Override
    public void onClick(View view) {

    }
}
