package edu.illinois.cs465.myquizapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import edu.illinois.cs465.myquizapp.Database;
import edu.illinois.cs465.myquizapp.pojo.Combination;
import edu.illinois.cs465.myquizapp.pojo.Flight;

public class PlanActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_main);

        // get the info from the DB
        String collectionName = getIntent().getStringExtra("collectionName");
        // add collectionName to the screen
        TextView title = findViewById(R.id.title);
        title.setText(collectionName);

        // get plans
        // Map<String, Set<Flight>> combinations = Database.combinationsInCollection.get(collectionName);
        // test case
        Map<String, Set<Flight>> combinations = new HashMap<>();
//        combinations.put("planA", new HashSet<>());
//        combinations.put("planB", new HashSet<>());

        // show the combination info in the screen
        List<Combination> plans = new ArrayList<>();
        Set<String> planNames = combinations.keySet();
        Iterator<String> it = planNames.iterator();
        while(it.hasNext()){
            String name = it.next();
            plans.add(new Combination(name, combinations.get(name)));
        }

        ListView listView = (ListView) findViewById(R.id.combinations);
        CombinationListAdapter adapter = new CombinationListAdapter(this, 0, plans);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Combination selectCombination = (Combination) listView.getItemAtPosition(position);
                System.out.println(selectCombination);
                Intent detailView = new Intent(getApplicationContext(), PlanDetailActivity.class);
                detailView.putExtra("selectCombination", selectCombination);
                startActivity(detailView);
            }
        });

    }

    class CombinationListAdapter extends ArrayAdapter<Combination> {

        CombinationListAdapter (Context context, int resource, List<Combination> combinations) {
            super(context, resource, combinations);
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Combination combination = getItem(position);
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.plan_main_card, parent, false);
            }
            TextView textView = convertView.findViewById(R.id.combination_name);
            textView.setText(combination.getCombinationName());
            return convertView;
        }
    }
}
