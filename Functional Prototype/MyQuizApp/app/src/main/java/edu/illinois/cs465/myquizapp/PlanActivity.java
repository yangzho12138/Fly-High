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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.textfield.TextInputEditText;

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
import edu.illinois.cs465.myquizapp.pojo.CollectionStatus;
import edu.illinois.cs465.myquizapp.pojo.Combination;
import edu.illinois.cs465.myquizapp.pojo.Flight;

public class PlanActivity extends AppCompatActivity{
    String collectionName;
    BottomNavigationView btn_view;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_main);

        btn_view = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        // get the info from the DB
        collectionName = getIntent().getStringExtra("collectionName");
        // add collectionName to the screen
        TextView title = findViewById(R.id.title);
        title.setText(collectionName);

        // get plans
        Map<String, Set<Flight>> combinations = Database.combinationsInCollection.get(collectionName);

        // add recommended combination
        Set<Flight> flightSet = Database.collections.get(collectionName);
        Set<Flight> recommendedFlights = new HashSet<>();
        String origin = "";
        String to = "";
        for(Flight f: flightSet){
            if(!f.getOrigin().equals(origin) && !f.getDestination().equals(to)){
                recommendedFlights.add(f);
                origin = f.getOrigin();
                to = f.getDestination();
            }
        }
        combinations.put("recommended  plan", recommendedFlights);
        Database.combinationsInCollection.put(collectionName, combinations);

        // test case
//        Map<String, Set<Flight>> combinations = new HashMap<>();
//        combinations.put("planA", new HashSet<>());
//        combinations.put("planB", new HashSet<>());
        if(combinations != null){
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
                    detailView.putExtra("collectionName", collectionName);
                    detailView.putExtra("lastPage", "plan");
                    startActivity(detailView);
                }
            });
        }

        Button openDialog = findViewById(R.id.add_new_plan);
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
                        Intent intent1 = new Intent(PlanActivity.this, SearchMainActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.action_combination:
                        Intent intent2 = new Intent(PlanActivity.this, FlightKeeperMainActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.action_trash:
                        Intent intent3 = new Intent(PlanActivity.this, TrashActivity.class);
                        startActivity(intent3);
                        break;
                }
                return true;
            }
        });

        Button back = findViewById(R.id.back_to_keeper);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backToKeeper = new Intent(PlanActivity.this, FlightKeeperDetailActivity.class);
                backToKeeper.putExtra("collectionName", collectionName);
                startActivity(backToKeeper);
            }
        });
    }

    void showCustomDialog() {
        final Dialog dialog = new Dialog(PlanActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.activity_plan_add_new);
        dialog.show();

        TextInputEditText text = dialog.findViewById(R.id.textField);
        Button saveButton = dialog.findViewById(R.id.save);
        saveButton.setOnClickListener((v) -> {
            String name = text.getText().toString();
            Database.addCombination(collectionName,name, null);
            dialog.dismiss();
            PlanActivity.this.recreate();
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

            Set<Flight> flightSet = combination.getFlights();
            System.out.println(flightSet);
            if(flightSet != null && flightSet.size() != 0){
                TextView totalPrice = convertView.findViewById(R.id.plan_total_price);
                Double sumPrice = 0.0;
                for(Flight flight: flightSet){
                    sumPrice += Double.parseDouble(flight.getTotalPrice().substring(1));
                }
                totalPrice.setText("Price in total: " + sumPrice);
                CollectionStatus cs = Database.status.get(collectionName);
                if(cs == null){
                    cs = new CollectionStatus();
                }
                if(cs.getLowestPrice()== null || Double.parseDouble(cs.getLowestPrice()) > sumPrice){
                    cs.setLowestPrice(sumPrice.toString());
                }

            }

            Button deleteButton = convertView.findViewById(R.id.delete_button);
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Database.deleteCombination(collectionName, combination.getCombinationName());
                }
            });

            return convertView;
        }
    }
}
