package com.application.bton;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;

import java.util.ArrayList;
import java.util.List;

public class DinningHallMenuSection extends AppCompatActivity {

    //TextView foodlist, dayView;
    TextView breakFaststreetEats, bFsaltSourSpiceUmami, bFsoupAndgrains;
    TextView lunchStreetEats, lunchsaltSourSpiceUmami, lunchsoupAndgrains;
    TextView dinnerStreetEats, dinnersaltSourSpiceUmami, dinnersoupAndgrains;
    TextView notification;
    public final String[] day = {"Monday"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dinning_hall_menu_section);

        // update online status
        readAndUpdateStatus("DhallStatus");

        String tag = "Notice text";
        readData(tag); // read data for the notification and update,// can be used for
        // other purposes as well

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
        bFsaltSourSpiceUmami = findViewById(R.id.bFsaltSourStreetEats);
        bFsoupAndgrains = findViewById(R.id.bFsoupAndgrains);
        lunchStreetEats = findViewById(R.id.lunchStreetEats);
        lunchsaltSourSpiceUmami = findViewById(R.id.lunchsaltSourSpiceUmami);
        lunchsoupAndgrains = findViewById(R.id.lunchsoupAndgrains);
        dinnerStreetEats = findViewById(R.id.dinnerStreetEats);
        dinnersaltSourSpiceUmami = findViewById(R.id.dinnersaltSourSpiceUmami);
        dinnersoupAndgrains = findViewById(R.id.dinnersoupAndgrains);

        //lunch = findViewById(R.id.lunch);
        //dinner = findViewById(R.id.dinner);

        // pre load text value
        breakFaststreetEats.setText("N/A");
        bFsaltSourSpiceUmami.setText("N/A");
        bFsoupAndgrains.setText("N/A");
        lunchStreetEats.setText("N/A");
        lunchsaltSourSpiceUmami.setText("N/A");
        lunchsoupAndgrains.setText("N/A");
        dinnerStreetEats.setText("N/A");
        dinnersaltSourSpiceUmami.setText("N/A");
        dinnersoupAndgrains.setText("N/A");


        //lunch.setText("Sorry, not updated yet :( ");
        //dinner.setText("Sorry, not updated yet :( ");

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

                    //breakfast
                    if (mealOfTheDay.equals("Breakfast")){
                        switch (mealType) {
                            case "Street Eats":
                                breakFaststreetEats.setText(meal);
                                break;
                            case "Salt/Sour/Spice/Umami":
                                bFsaltSourSpiceUmami.setText(meal);
                                break;
                            case "Soup and Grains":
                                bFsoupAndgrains.setText(meal);
                                break;
                        }
                    }

                    // lunch
                    else if (mealOfTheDay.equals("Lunch")){
                        switch (mealType) {
                            case "Street Eats":
                                lunchStreetEats.setText(meal);
                                break;
                            case "Salt/Sour/Spice/Umami":
                                lunchsaltSourSpiceUmami.setText(meal);
                                break;
                            case "Soup and Grains":
                                lunchsoupAndgrains.setText(meal);
                                break;
                        }
                    }

                    //dinner
                    else if (mealOfTheDay.equals("Dinner")){
                        switch (mealType) {
                            case "Street Eats":
                                dinnerStreetEats.setText(meal);
                                break;
                            case "Salt/Sour/Spice/Umami":
                                dinnersaltSourSpiceUmami.setText(meal);
                                break;
                            case "Soup and Grains":
                                dinnersoupAndgrains.setText(meal);
                                break;
                        }
                    }
                }
            }

            @Override
            public void handleFault(BackendlessFault fault) {

                toastMessage("fault");
            }
        });
    }

    public void readData(String tag){
        String whereClause = "day = '" + tag + "'";
        DataQueryBuilder queryBuilder = DataQueryBuilder.create();
        queryBuilder.setWhereClause(whereClause);
        queryBuilder.setPageSize(100).setOffset(0);
        queryBuilder.setSortBy("created DESC"); // latest

        Backendless.Persistence.of(DhallMenu.class).find(queryBuilder, new AsyncCallback<List<DhallMenu>>() {
            @Override
            public void handleResponse(List<DhallMenu> response) {
                notification = findViewById(R.id.notification);
                notification.setText(response.get(0).getMeal());

            }

            @Override
            public void handleFault(BackendlessFault fault) {
                String faultMessage = "Fault occurred while retrieving data :(. " +
                        "Please check your internet connection.";
            }
        });
    }

    public void readAndUpdateStatus(String tag){
        String whereClause = "tag = '" + tag + "'";
        DataQueryBuilder queryBuilder = DataQueryBuilder.create();
        queryBuilder.setWhereClause(whereClause);
        queryBuilder.setPageSize(100).setOffset(0);
        queryBuilder.setSortBy("created DESC"); // latest status

        Backendless.Persistence.of(StatusUpdate.class).find(queryBuilder, new AsyncCallback<List<StatusUpdate>>() {
            @Override
            public void handleResponse(List<StatusUpdate> response) {
                String status = response.get(0).getStatus();
                Button btnStatus = findViewById(R.id.btnStatus);
                if (status.equals("ON")){
                    toastMessage("The dinning hall is currently Open");
                    btnStatus.setText("Open");
                    //btnStatus.setBackgroundColor(btnStatus.getContext().getResources().getColor(R.color.primary));
                    //btnStatus.setBackground();
                }
                else {
                    toastMessage("The dinning hall is currentl closed");
                    btnStatus.setText("Closed");
                    //btnStatus.setBackground(R.color.danger);
                }
                // toggle.setBackgroundResource(R.color.info);
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Log.d("fault readStatus", "fault occured while getting online status" + fault);
            }
        });

}

    public void toastMessage( String msg){
        Toast.makeText(DinningHallMenuSection.this, msg, Toast.LENGTH_SHORT).show();
    }
}