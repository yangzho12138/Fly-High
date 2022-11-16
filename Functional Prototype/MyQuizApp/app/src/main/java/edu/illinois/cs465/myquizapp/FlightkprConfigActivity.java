package edu.illinois.cs465.myquizapp.flightkeeper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import edu.illinois.cs465.myquizapp.R;

public class FlightkprConfigActivity extends AppCompatActivity implements View.OnClickListener {
    private Button advanced_op;
    private Button confirm;
    private EditText from_location;
    private EditText to_location;
    private EditText lower_bound;
    private EditText upper_bound;

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

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.advanced_op) {
            Intent intent = new Intent(FlightkprConfigActivity.this, AdvancedActivity.class);
            startActivity(intent);
        }
        else if (v.getId() == R.id.confirm){
            Number low = Integer.parseInt(lower_bound.getText().toString());
            Number upper = Integer.parseInt(upper_bound.getText().toString());
            String from = from_location.getText().toString();
            String to = to_location.getText().toString();

            Intent intent = new Intent(FlightkprConfigActivity.this, FlightKeeperDetailActivity.class);
            intent.putExtra("lower_bound", low);
            intent.putExtra("upper_bound", upper);
            intent.putExtra("from_location", from);
            intent.putExtra("to_location", to);
            startActivity(intent);

        }
    }
}
