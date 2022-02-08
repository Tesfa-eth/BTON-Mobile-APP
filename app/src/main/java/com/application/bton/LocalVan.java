package com.application.bton;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;

import java.util.List;

public class LocalVan extends AppCompatActivity {
    TextView noticeText, scheduleText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_van);

        getNoticeAndSchedule("Schedule");
        getNoticeAndSchedule("Notice");
    }

    public void getNoticeAndSchedule(String category){
        String whereClause = "tag = 'localVanTag' and  category = '" + category + "'";
        DataQueryBuilder queryBuilder = DataQueryBuilder.create();
        queryBuilder.setWhereClause(whereClause);
        queryBuilder.setPageSize(100).setOffset(0);
        queryBuilder.setSortBy("created DESC"); // latest status

        Backendless.Persistence.of(ShuttleTable.class).find(queryBuilder, new AsyncCallback<List<ShuttleTable>>() {
            @Override
            public void handleResponse(List<ShuttleTable> response) {
                if(response.size() == 0){
                    toastMessage("We are currently updating the app. Sorry for any conveniences.");
                }
                else if(category.equals("Schedule")){
                    scheduleText = findViewById(R.id.scheduleText);
                    scheduleText.setText(response.get(0).getTextPost());
                }
                else if(category.equals("Notice")){
                    noticeText = findViewById(R.id.noticeText);
                    noticeText.setText(response.get(0).getTextPost());
                }
            }

            @Override
            public void handleFault(BackendlessFault fault) {

            }
        });
    }
    public void toastMessage( String msg){
        Toast.makeText(LocalVan.this, msg, Toast.LENGTH_SHORT).show();
    }
}