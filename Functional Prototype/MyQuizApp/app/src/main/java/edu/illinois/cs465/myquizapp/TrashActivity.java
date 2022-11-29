package edu.illinois.cs465.myquizapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import edu.illinois.cs465.myquizapp.pojo.Collection;

public class TrashActivity extends AppCompatActivity {

    Activity trashActivity = null;
    BottomNavigationView btn_view;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trash);


        btn_view = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        trashActivity = this;

        List<Collection> collections = new ArrayList<>();
        for (Map.Entry entry : Database.trash.entrySet()) {
            Collection c = new Collection(entry.getKey().toString(), null, null);
            collections.add(c);
        }

        ListView listView = (ListView) findViewById(R.id.trash_list);
        CollectionListAdapter adapter = new CollectionListAdapter(this, 0, collections);
        listView.setAdapter(adapter);

        btn_view.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id){
                    //check id

                    case R.id.action_search:
                        Intent intent1 = new Intent(TrashActivity.this, SearchMainActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.action_combination:
                        Intent intent2 = new Intent(TrashActivity.this, FlightKeeperMainActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.action_trash:
                        Intent intent3 = new Intent(TrashActivity.this, TrashActivity.class);
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
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.trash_card, parent, false);
            }

            TextView collectionName = convertView.findViewById(R.id.collection_name);
            collectionName.setText(String.valueOf(collection.getCollectionName()));

            Button restoreBtn = convertView.findViewById(R.id.restore_button);
            restoreBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Database.restoreCollection(collection.getCollectionName());
                    trashActivity.recreate();
                }
            });

            return convertView;
        }
    }
}
