package com.application.bton;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;

import java.util.List;

public class CampusShuttle extends AppCompatActivity {
    TextView localVanStatus, paranShuttleStatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campus_shuttle);
        readAndUpdateStatus("localVanStatus");
        readAndUpdateStatus("paranShuttleStatus");
        localVanStatus = findViewById(R.id.localVanStatus);
        paranShuttleStatus = findViewById(R.id.paranShuttleStatus);
    }

    public void readAndUpdateStatus(String tag){
        // gets the latest status and updates the availability.
        String whereClause = "tag = '" + tag + "'";
        DataQueryBuilder queryBuilder = DataQueryBuilder.create();
        queryBuilder.setWhereClause(whereClause);
        queryBuilder.setPageSize(100).setOffset(0);
        queryBuilder.setSortBy("created DESC"); // latest status

        Backendless.Persistence.of(StatusUpdate.class).find(queryBuilder, new AsyncCallback<List<StatusUpdate>>() {
            @Override
            public void handleResponse(List<StatusUpdate> response) {
                //Button btnStatus = findViewById(R.id.btnStatus);
                if(response.size() == 0){
                    //btnStatus.setText("Offline");
                    localVanStatus.setText("Offline");
                    paranShuttleStatus.setText("Offline");
                    toastMessage("The app is currently on an update. We apologize for any" +
                            " inconvenience.");
                    return;
                }
                String status = response.get(0).getStatus();
                if (tag.equals("localVanStatus")){
                    if(status.equals("ON")){
                        localVanStatus.setText("Available");
                        localVanStatus.setBackgroundResource(R.color.primaryBootstrap);
                        toastMessage("The local van is currently running.");
                    }
                    else if(status.equals("OFF")){
                        localVanStatus.setText("N/A");
                    }
                }
                if (tag.equals("paranShuttleStatus")){
                    if(status.equals("ON")){
                        paranShuttleStatus.setText("Available");
                        paranShuttleStatus.setBackgroundResource(R.color.primaryBootstrap);
                        toastMessage("The Paran creek shuttle is currently running.");
                    }
                    else if(status.equals("OFF")){
                        paranShuttleStatus.setText("N/A");
                    }
                }

            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Log.d("fault readStatus", "fault occured while getting online status" + fault);
            }
        });

    }

    public void toastMessage( String msg){
        Toast.makeText(CampusShuttle.this, msg, Toast.LENGTH_SHORT).show();
    }

}