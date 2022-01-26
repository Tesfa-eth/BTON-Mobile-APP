package com.application.bton;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;

import java.util.List;

public class DinningHallMenuSection extends AppCompatActivity {

    Button getMeal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dinning_hall_menu_section);

        getMeal = findViewById(R.id.btnGetmeal);
        getMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String whereClause = "day = 'Monday'";
                DataQueryBuilder queryBuilder = DataQueryBuilder.create();
                queryBuilder.setWhereClause(whereClause);
                queryBuilder.setPageSize(100).setOffset(0);
                queryBuilder.setSortBy("day");

                Backendless.Persistence.of(DhallMenu.class).find(queryBuilder,new AsyncCallback<List<DhallMenu>>() {
                    @Override
                    public void handleResponse(List<DhallMenu> response) {
                        toastMessage("count: " + response.size());
                        for(int i=0; i < response.size(); i++){
                            toastMessage(response.get(i).getDay() + " - " +
                                    response.get(i).getMealOfTheDay()+ " - " + response.get(i).getMeal());
                        }

                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {

                        toastMessage("fault");
                    }
                });
            }
        });
    }
    public void toastMessage( String msg){
        Toast.makeText(DinningHallMenuSection.this, msg, Toast.LENGTH_SHORT).show();
    }
}