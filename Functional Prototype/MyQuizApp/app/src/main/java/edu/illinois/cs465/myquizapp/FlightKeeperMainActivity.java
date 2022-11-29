package edu.illinois.cs465.myquizapp;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import edu.illinois.cs465.myquizapp.pojo.Collection;

public class FlightKeeperMainActivity extends AppCompatActivity {

    BottomNavigationView btn_view;
    public List<Collection> collections = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flightkeeper_main);

        btn_view = (BottomNavigationView) findViewById(R.id.bottom_navigation);


        for (Map.Entry entry : Database.collections.entrySet()) {
            String collectionName = entry.getKey().toString();
            if (Database.status.containsKey(collectionName)) {
                Integer numOfPlans = Database.status.get(collectionName).getPlanNum();
                String lowestPrice = Database.status.get(collectionName).getLowestPrice();
                collections.add(new Collection(collectionName, numOfPlans, lowestPrice));
            }
            else {
                collections.add(new Collection(collectionName, 0, "N/A"));
            }
        }

        ListView listView = (ListView) findViewById(R.id.collections_main);
        CollectionListAdapter adapter = new CollectionListAdapter(this, 0, collections);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Collection c = (Collection) listView.getItemAtPosition(position);
                String collectionName = c.getCollectionName();
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

        btn_view.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id){
                    //check id

                    case R.id.action_search:
                        Intent intent1 = new Intent(FlightKeeperMainActivity.this, SearchMainActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.action_combination:
                        Intent intent2 = new Intent(FlightKeeperMainActivity.this, FlightKeeperMainActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.action_trash:
                        Intent intent3 = new Intent(FlightKeeperMainActivity.this, TrashActivity.class);
                        startActivity(intent3);
                        break;
                }
                return true;
            }
        });

    }

    class CollectionListAdapter extends ArrayAdapter<Collection> {

        CollectionListAdapter (Context context, int resource, List<Collection> collections) {
            super(context, resource, collections);
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Collection collection = getItem(position);
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.flightkeeper_main_card, parent, false);
            }

            TextView collectionName = convertView.findViewById(R.id.collection_name);
            collectionName.setText(String.valueOf(collection.getCollectionName()));
            TextView numOfPlan = convertView.findViewById(R.id.plan_num);
            numOfPlan.setText(String.valueOf(collection.getNumOfPlan()));
            TextView lowestPrice = convertView.findViewById(R.id.lowest_price);
            lowestPrice.setText(collection.getLowestPrice());

            Button delete = convertView.findViewById(R.id.delete);
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String name = collection.getCollectionName();
                    Database.deleteCollection(name);
                    showDeleteSuccessDialog();
                }

            });

            return convertView;
        }
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

    void showDeleteSuccessDialog() {
        final Dialog dialog = new Dialog(FlightKeeperMainActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.flightkeeper_delete_success);
        dialog.show();

        Button saveButton = dialog.findViewById(R.id.ok);
        saveButton.setOnClickListener((v) -> {
            dialog.dismiss();
            this.recreate();
        });
    }

}
