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

public class ParanCreekShuttle extends AppCompatActivity {
    TextView noticeParanText, scheduleParanText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paran_creek_shuttle);

        getNoticeAndSchedule("Schedule");
        getNoticeAndSchedule("Notice");
    }

    public void getNoticeAndSchedule(String category){
        String whereClause = "tag = 'ParanShuttleTag' and  category = '" + category + "'";
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
                    scheduleParanText = findViewById(R.id.scheduleParanText);
                    scheduleParanText.setText(response.get(0).getTextPost());
                }
                else if(category.equals("Notice")){
                    noticeParanText = findViewById(R.id.noticeParanText);
                    noticeParanText.setText(response.get(0).getTextPost());
                }
            }

            @Override
            public void handleFault(BackendlessFault fault) {

            }
        });
    }
    public void toastMessage( String msg){
        Toast.makeText(ParanCreekShuttle.this, msg, Toast.LENGTH_SHORT).show();
    }
}