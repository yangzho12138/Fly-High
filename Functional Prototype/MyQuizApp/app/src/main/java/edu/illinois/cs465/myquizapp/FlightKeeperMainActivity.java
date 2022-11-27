package edu.illinois.cs465.myquizapp;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import edu.illinois.cs465.myquizapp.pojo.Flight;

public class FlightKeeperMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flightkeeper_main);

        List<String> collectionNames = new ArrayList<>();
        for (Map.Entry entry : Database.collections.entrySet()) {
            collectionNames.add(entry.getKey().toString());
        }

        ListView listView = (ListView) findViewById(R.id.collections);
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.flightkeeper_main_card, R.id.collection_name, collectionNames);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String collectionName = listView.getItemAtPosition(position).toString();
                Intent detailView = new Intent(getApplicationContext(), FlightKeeperDetailActivity.class);
                detailView.putExtra("collectionName", collectionName);
                startActivity(detailView);
            }
        });

        Button openDialog = findViewById(R.id.add_new_keeper);
        openDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCustomDialog();
            }
        });

    }

    void showCustomDialog() {
        final Dialog dialog = new Dialog(FlightKeeperMainActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.flightkeeper_addnew);
        dialog.show();

        TextInputEditText text = dialog.findViewById(R.id.textField);
        Button saveButton = dialog.findViewById(R.id.save);
        saveButton.setOnClickListener((v) -> {
            String name = text.getText().toString();
            Database.addCollection(name);
            dialog.dismiss();
            this.recreate();
        });
    }

}
