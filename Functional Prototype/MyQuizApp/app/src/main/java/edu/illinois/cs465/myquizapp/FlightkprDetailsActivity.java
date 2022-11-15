package edu.illinois.cs465.fly_high;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FlightkprDetailsActivity extends AppCompatActivity implements View.OnClickListener {
    private Button config_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flightkeeper_details);


        config_button = (Button) findViewById(R.id.configuration);
        config_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.configuration) {
            Intent intent = new Intent(FlightkprDetailsActivity.this, FlightkprConfigActivity.class);
            startActivity(intent);
        }
    }
}
