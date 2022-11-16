package edu.illinois.cs465.myquizapp.flightkeeper;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import edu.illinois.cs465.myquizapp.R;
import edu.illinois.cs465.myquizapp.pojo.Collection;

public class FlightKeeperMainActivity extends AppCompatActivity {

    public static List<Collection> collections = new ArrayList<>();

    static {
        collections.add(new Collection(1, "My Trip to New York"));
        collections.add(new Collection(2, "Christmas Trip"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flightkeeper_main);

//        ListView listView = (ListView) findViewById(R.id.collections);
//        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.flightkeeper_main_card, R.id.collection, collectionNames);
//        listView.setAdapter(adapter);

        ListView listView = (ListView) findViewById(R.id.collections);
        CollectionListAdapter adapter = new CollectionListAdapter(this, 0, collections);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Collection selectCollection = (Collection) listView.getItemAtPosition(position);
                Intent detailView = new Intent(getApplicationContext(), FlightKeeperDetailActivity.class);
                detailView.putExtra("id", selectCollection.getId());
                startActivity(detailView);
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
            TextView textView = convertView.findViewById(R.id.collection_name);
            textView.setText(collection.getCollectionName());
            return convertView;
        }
    }
}
