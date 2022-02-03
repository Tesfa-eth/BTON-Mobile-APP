package com.application.bton;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;


public class GeneralAnnouncement extends AppCompatActivity {
    public final List<AnnouncementTable>[] crudeResult = new List[]{new ArrayList<>()};
    ListView announcementListView;
    TextView messageFrom, messageContent, dateAndTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_announcement);
        loadData();

        // announcement list view
        announcementListView = findViewById(R.id.announcementListView);

        Button btnDisplay = (Button) findViewById(R.id.btnDisplay);
        btnDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //toastMessage("should be working: " + crudeResult[0].size());
                btnDisplay.setText("Refresh");

                //Adapter here
                MyAdapter adapter = new MyAdapter();
                announcementListView.setAdapter(adapter);
            }
        });
    }

    public void loadData(){
        // search and send the contents
        AnnouncementTable announcementTable = new AnnouncementTable();
        //String whereClause = "tag = '" + tag + "'";
        DataQueryBuilder queryBuilder = DataQueryBuilder.create();
        //queryBuilder.setWhereClause(whereClause);
        queryBuilder.setPageSize(100).setOffset(0);
        queryBuilder.setSortBy("created DESC"); // latest
        Backendless.Persistence.of(AnnouncementTable.class).find(queryBuilder, new AsyncCallback<List<AnnouncementTable>>() {
            @Override
            public void handleResponse(List<AnnouncementTable> response) {
                crudeResult[0] = response;
            }

            @Override
            public void handleFault(BackendlessFault fault) {

            }
        });
    }
    public class MyAdapter extends BaseAdapter {

        List<AnnouncementTable> response = crudeResult[0];

        @Override
        public int getCount() {
            return response.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.card_announcement, parent, false);
            dateAndTime = convertView.findViewById(R.id.msgDateAndTime);
            messageFrom = convertView.findViewById(R.id.messageFrom);
            messageContent = convertView.findViewById(R.id.messageContent);

            dateAndTime.setText(response.get(position).getDateAndTime());
            messageFrom.setText(response.get(position).getMessageFrom());
            messageContent.setText(response.get(position).getMessageContent());

            return convertView;
        }
    }

    public void toastMessage( String msg){
        Toast.makeText(GeneralAnnouncement.this, msg, Toast.LENGTH_SHORT).show();
    }
}