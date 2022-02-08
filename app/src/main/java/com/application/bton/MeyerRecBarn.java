package com.application.bton;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;

import java.util.List;

public class MeyerRecBarn extends AppCompatActivity {
    TextView meyerRecStatus, recBarnImptInfoText, recBarnFallHoursText, recBarnSpecialHoursText, recBarnDanceStudioText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meyer_rec_barn);

        meyerRecStatus = findViewById(R.id.meyerRecStatus);
        readAndUpdateStatus("MeyerBarnStatus");

        recBarnImptInfoText = findViewById(R.id.recBarnImptInfoText);
        recBarnFallHoursText = findViewById(R.id.recBarnFallHoursText);
        recBarnSpecialHoursText = findViewById(R.id.recBarnSpecialHoursText);
        recBarnDanceStudioText = findViewById(R.id.recBarnDanceStudioText);

        getCategoryAndUpdateTexts("Important updates");
        getCategoryAndUpdateTexts("Fall term working hours");
        getCategoryAndUpdateTexts("Special hours");
        getCategoryAndUpdateTexts("Faculty dance station");
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
                    meyerRecStatus.setText("Offline");
                    toastMessage("The app is currently on update. We apologize for any" +
                            " inconvenience.");
                    return;
                }
                else if(response.get(0).getStatus().equals("ON")){
                    meyerRecStatus.setText("Open");
                    meyerRecStatus.setBackgroundResource(R.color.primaryBootstrap);
                    toastMessage("The barn is currently Open.");
                }
                else {
                    toastMessage("The barn is currently Closed.");
                }


            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Log.d("fault readStatus", "fault occured while getting online status" + fault);
            }
        });

    }

    public void getCategoryAndUpdateTexts(String category){
        String whereClause = "tag = 'MeyerRecBarnPostTag' and  category = '" + category + "'";
        DataQueryBuilder queryBuilder = DataQueryBuilder.create();
        queryBuilder.setWhereClause(whereClause);
        queryBuilder.setPageSize(100).setOffset(0);
        queryBuilder.setSortBy("created DESC"); // latest status

//        getCategoryAndUpdateTexts("Important updates");
//        getCategoryAndUpdateTexts("Fall term working hours");
//        getCategoryAndUpdateTexts("Special hours");
//        getCategoryAndUpdateTexts("Faculty dance station");

        Backendless.Persistence.of(ShuttleTable.class).find(queryBuilder, new AsyncCallback<List<ShuttleTable>>() {
            @Override
            public void handleResponse(List<ShuttleTable> response) {
                if(response.size() == 0){
                    String updateApology = "We are currently updating the app. Sorry for any conveniences.";
                    toastMessage(updateApology);
                    recBarnImptInfoText.setText(updateApology);
                }
                else if(category.equals("Important updates")){
                    recBarnImptInfoText.setText(response.get(0).getTextPost());
                }
                else if(category.equals("Fall term working hours")){
                    recBarnFallHoursText.setText(response.get(0).getTextPost());
                }
                else if(category.equals("Special hours")){
                    recBarnSpecialHoursText.setText(response.get(0).getTextPost());
                }
                else if(category.equals("Faculty dance station")){
                    recBarnDanceStudioText.setText(response.get(0).getTextPost());
                }
            }

            @Override
            public void handleFault(BackendlessFault fault) {

            }
        });
    }

    public void toastMessage( String msg){
        Toast.makeText(MeyerRecBarn.this, msg, Toast.LENGTH_SHORT).show();
    }
}