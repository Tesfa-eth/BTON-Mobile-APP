package com.application.bton;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import weborb.client.Fault;

public class EmployeeRegistration extends AppCompatActivity {
    EditText lastname;
    Button btnSearch;
    ListView mListView;
    public final List<Person>[] resuLT = new List[]{new ArrayList<>()};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_registration);

        //Log.d("BROKEHERE", "p-0 check response" + response.size());

        // list view that contains all the card views.
        mListView = findViewById(R.id.regListView);

        lastname = findViewById(R.id.edtTxtFirstName);
        btnSearch = findViewById(R.id.btnSearch);

        //Log.d("brokeHere", "p-1 check response" + response.size());

        getEverything();
        //toastMessage("check responseList size........." + responseList.size());
        toastMessage("One more time: " + resuLT[0].size());


        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeToCard();
                //backendless stuff.... searching for stuff here
                //String whereClause = "surname is not null";
                String whereClause = "surname = '" + lastname.getText().toString() + "'";
                Log.d("whereClause", whereClause);

                DataQueryBuilder queryBuilder = DataQueryBuilder.create();
                queryBuilder.setWhereClause(whereClause);
                queryBuilder.setPageSize(100).setOffset(0);
                queryBuilder.setSortBy("name");

                Backendless.Persistence.of(Person.class).find(queryBuilder, new AsyncCallback<List<Person>>() {
                    @Override
                    public void handleResponse(List<Person> response1) {
                        //responseList.add(response1);
                        //response = response1;
                        //writeToCard(response1);
                        for(int i=0; i < response1.size(); i++){
                            toastMessage(response1.get(i).getName());
                        }
                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {
                        toastMessage("Some fault found" + fault);
                    }
                });

                // end of backendless stuff


                lastname.setText("");
            }
        });
        //toastMessage("check responseList size........." + responseList.size());

        //Log.d("beforeAdapter", "check response" + response.size());
    }

    public void getEverything(){
        //List<List<Person>> responseListMain = new ArrayList<>();
        DataQueryBuilder queryBuilder = DataQueryBuilder.create();
        //queryBuilder.setWhereClause(whereClause);
        queryBuilder.setPageSize(100).setOffset(0);
        queryBuilder.setSortBy("name");

        Backendless.Persistence.of(Person.class).find(queryBuilder, new AsyncCallback<List<Person>>() {
            @Override
            public void handleResponse(List<Person> response) {
                resuLT[0] = response;
                //rESULT[0] = response;
                toastMessage("size one: " + resuLT[0].size());
                //responseList.add(response);
                //toastMessage("Count: " + responseList.size());
            }

            @Override
            public void handleFault(BackendlessFault fault) {

            }
        });
    }

    public void writeToCard(){
        toastMessage("One more time: " + resuLT[0].size());
        MyAdapter myAdapter = new MyAdapter();
        mListView.setAdapter(myAdapter);
    }

    public class MyAdapter extends BaseAdapter{

        List<Person> response = resuLT[0];

        @Override
        public int getCount() {
            //toastMessage("size: " + rESULT[0].size());
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
            convertView = getLayoutInflater().inflate(R.layout.card_emp_reg, parent, false);
            ImageView mImageView = convertView.findViewById(R.id.cardRegImageView);
            TextView mTextView = convertView.findViewById(R.id.cardRegText);

            mImageView.setImageResource(R.drawable.adm2_wesley);
            mTextView.setText(response.get(position).getName());

            return convertView;
        }
    }

    public void toastMessage( String msg){
        Toast.makeText(EmployeeRegistration.this, msg, Toast.LENGTH_SHORT).show();
    }
}