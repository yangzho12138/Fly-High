package edu.illinois.cs465.myquizapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.slider.Slider;

import edu.illinois.cs465.myquizapp.R;


public class AdvancedActivity extends AppCompatActivity implements View.OnClickListener {
    private Button save_button;
    public int stop_val;
    public int bag_val;
    public int duration_val;
    public int adult_count;
    public int children_count;
    public int infant_count;
    public String pageName = "advanced";
    public String collectionName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.advanced_filter);

        Intent previntent = getIntent();
        collectionName = previntent.getStringExtra("collectionName");


        Slider stop_slider = findViewById(R.id.stop_slider);
        Slider bag_slider = findViewById(R.id.bag_slider);
        Slider duration_slider = findViewById(R.id.duration_slider);

        Button adult_dec = findViewById(R.id.minus1);
        Button adult_inc = findViewById(R.id.add1);
        TextView adult_tv = findViewById(R.id.adult_cnt);


        adult_inc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adult_count++;
                adult_tv.setText(String.valueOf(adult_count));
                if (Database.autoFilter.containsKey(collectionName)){
                    Database.autoFilter.get(collectionName).adult_cnt = adult_count;
                }
            }
        });

        adult_dec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adult_count--;
                if (adult_count<=0){
                    adult_count=0;
                }
                adult_tv.setText(String.valueOf(adult_count));
                if (Database.autoFilter.containsKey(collectionName)){
                    Database.autoFilter.get(collectionName).adult_cnt = adult_count;
                }
            }
        });

        Button children_dec = findViewById(R.id.minus2);
        Button children_inc = findViewById(R.id.add2);
        TextView children_tv = findViewById(R.id.children_cnt);
        children_inc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                children_count++;
                children_tv.setText(String.valueOf(children_count));
                if (Database.autoFilter.containsKey(collectionName)){
                    Database.autoFilter.get(collectionName).children_cnt = children_count;
                }
            }
        });

        children_dec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                children_count--;
                if (children_count<=0){
                    children_count=0;
                }
                children_tv.setText(String.valueOf(children_count));
                if (Database.autoFilter.containsKey(collectionName)){
                    Database.autoFilter.get(collectionName).children_cnt = children_count;
                }
            }
        });

        Button infant_dec = findViewById(R.id.minus3);
        Button infant_inc = findViewById(R.id.add3);
        TextView infant_tv = findViewById(R.id.infant_cnt);
        infant_inc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                infant_count++;
                infant_tv.setText(String.valueOf(infant_count));
                if (Database.autoFilter.containsKey(collectionName)){
                    Database.autoFilter.get(collectionName).infant_cnt = infant_count;
                }
            }
        });

        infant_dec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                infant_count--;
                if (infant_count<=0) {
                    infant_count = 0;
                }
                infant_tv.setText(String.valueOf(infant_count));
                if (Database.autoFilter.containsKey(collectionName)){
                    Database.autoFilter.get(collectionName).infant_cnt = infant_count;
                }
            }
        });




        stop_slider.addOnChangeListener(new Slider.OnChangeListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onValueChange(@NonNull Slider slider, float stop_value, boolean fromUser) {
                stop_val = (int)stop_value;
                if (Database.autoFilter.containsKey(collectionName)){
                    Database.autoFilter.get(collectionName).stops = stop_val;
                }
            }
        });

        bag_slider.addOnChangeListener(new Slider.OnChangeListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onValueChange(@NonNull Slider slider, float bag_value, boolean fromUser) {
                bag_val = (int)bag_value;
                if (Database.autoFilter.containsKey(collectionName)){
                    Database.autoFilter.get(collectionName).bags = bag_val;
                }
            }
        });

        duration_slider.addOnChangeListener(new Slider.OnChangeListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onValueChange(@NonNull Slider slider, float duration_value, boolean fromUser) {
                duration_val = (int)duration_value;
                if (Database.autoFilter.containsKey(collectionName)){
                    Database.autoFilter.get(collectionName).duration = duration_val;
                }
            }
        });

        save_button = (Button) findViewById(R.id.save_button);
        save_button.setOnClickListener(this);

        if (Database.autoFilter.containsKey(collectionName)){
            stop_slider.setValue(Database.autoFilter.get(collectionName).stops);
            bag_slider.setValue(Database.autoFilter.get(collectionName).bags);
            duration_slider.setValue(Database.autoFilter.get(collectionName).duration);
            adult_tv.setText(Database.autoFilter.get(collectionName).adult_cnt.toString());
            children_tv.setText(Database.autoFilter.get(collectionName).children_cnt.toString());
            infant_tv.setText(Database.autoFilter.get(collectionName).infant_cnt.toString());
        }

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.save_button) {
            Intent intent = new Intent(AdvancedActivity.this, FlightkprConfigActivity.class);
            intent.putExtra("stops", stop_val);
            intent.putExtra("bags", bag_val);
            intent.putExtra("duration", duration_val);
            intent.putExtra("adult_cnt", adult_count);
            intent.putExtra("children_cnt", children_count);
            intent.putExtra("infant_cnt", infant_count);
            intent.putExtra("lastpage", pageName);
            intent.putExtra("collectionName", collectionName);
            System.out.println("==collectionname"+ collectionName);
            if (Database.autoFilter.containsKey(collectionName)){
                System.out.println("in=============="+ Database.autoFilter.get(collectionName).origin);
                Database.autoFilter.get(collectionName).stops = stop_val;
                Database.autoFilter.get(collectionName).bags = bag_val;
                Database.autoFilter.get(collectionName).duration = duration_val;
                Database.autoFilter.get(collectionName).adult_cnt = adult_count;
                Database.autoFilter.get(collectionName).children_cnt = children_count;
                Database.autoFilter.get(collectionName).infant_cnt = infant_count;
            }
            startActivity(intent);
        }
    }
}
