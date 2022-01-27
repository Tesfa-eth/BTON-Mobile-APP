package com.application.bton;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;

import java.util.List;

public class DinningHallMenuSection extends AppCompatActivity {

    //TextView foodlist, dayView;
    TextView breakFaststreetEats, saltSourStreetEats,soupAndgrains, lunch, dinner;
    public final String[] day = {"Monday"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dinning_hall_menu_section);

        // day of the day of the week spinner
        Spinner spinnerDay = (Spinner) findViewById(R.id.spinnerDay);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterDay = ArrayAdapter.createFromResource(this,
                R.array.dayOfTheWeek, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterDay.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerDay.setAdapter(adapterDay);
        // Day spinner item selected listener
        spinnerDay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                day[0] = parent.getItemAtPosition(position).toString();
                //toastMessage(day[0]);
                readDataAndDisplay();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // error handling needed?
                toastMessage("Nothing selected yet!");
            }
        });

    }
    public void readDataAndDisplay(){
        String whereClause = "day = '" + day[0] + "'";
        DataQueryBuilder queryBuilder = DataQueryBuilder.create();
        queryBuilder.setWhereClause(whereClause);
        queryBuilder.setPageSize(100).setOffset(0);
        queryBuilder.setSortBy("day");

        breakFaststreetEats = findViewById(R.id.breakFastStreetEats);
        saltSourStreetEats = findViewById(R.id.saltSourStreetEats);
        soupAndgrains = findViewById(R.id.soupAndgrains);

        lunch = findViewById(R.id.lunch);
        dinner = findViewById(R.id.dinner);

        // pre load text value
        breakFaststreetEats.setText("Sorry, not updated yet :( ");
        saltSourStreetEats.setText("Sorry, not updated yet :( ");
        soupAndgrains.setText("Sorry, not updated yet :( ");


        lunch.setText("Sorry, not updated yet :( ");
        dinner.setText("Sorry, not updated yet :( ");

        Backendless.Persistence.of(DhallMenu.class).find(queryBuilder,new AsyncCallback<List<DhallMenu>>() {
            @Override
            public void handleResponse(List<DhallMenu> response) {
                //toastMessage("count: " + response.size());
                for(int i=0; i < response.size(); i++){
                    //toastMessage(response.get(i).getDay() + " - " +
                    //        response.get(i).getMealOfTheDay()+ " - " + response.get(i).getMealType() + "-" + response.get(i).getMeal());
                    String meal = response.get(i).getMeal();
                    String mealOfTheDay = response.get(i).getMealOfTheDay();
                    String mealType = response.get(i).getMealType();
                    if (mealOfTheDay.equals("Breakfast")){
                        if (mealType.equals("Street Eats")){
                            breakFaststreetEats.setText(meal);
                        }
                        else if(mealType.equals("Salt/Sour/Spice/Umami")){
                            saltSourStreetEats.setText(meal);
                        }
                        else if(mealType.equals("Soup and Grains")){
                            soupAndgrains.setText(meal);
                        }
                    }
                    else if (mealOfTheDay.equals("Lunch")){
                        lunch.setText(meal);
                    }
                    else if(mealOfTheDay.equals("Dinner")){
                        dinner.setText(meal);
                    }
                }

                /*foodlist = findViewById(R.id.foodlist);
                dayView = findViewById(R.id.dayView);

                dayView.setText(day[0]);
                if (response.size() > 0){
                    foodlist.setText(response.get(0).getMeal());
                    //foodlist.setText(response.get(0).getDay() + " - " + response.get(0).getMealOfTheDay()+ " - " + response.get(0).getMeal());
                }
                else{
                    foodlist.setText("Sorry, menu not updated yet :( ");
                }*/

            }

            @Override
            public void handleFault(BackendlessFault fault) {

                toastMessage("fault");
            }
        });
    }
    public void toastMessage( String msg){
        Toast.makeText(DinningHallMenuSection.this, msg, Toast.LENGTH_SHORT).show();
    }
}