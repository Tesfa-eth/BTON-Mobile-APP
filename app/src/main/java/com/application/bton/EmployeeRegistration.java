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
    public final List<EmployeeProfile>[] resuLT = new List[]{new ArrayList<>()};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_registration);

        // list view to display profiles
        mListView = findViewById(R.id.regListView);

        // search by
        lastname = findViewById(R.id.edtTxtFirstName);
        getEverything();


        btnSearch = findViewById(R.id.btnSearch);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeToCard();
            }
        });
    }

    public void getEverything(){
        //List<List<Person>> responseListMain = new ArrayList<>();
        DataQueryBuilder queryBuilder = DataQueryBuilder.create();
        //queryBuilder.setWhereClause(whereClause);
        queryBuilder.setPageSize(100).setOffset(0);
        //queryBuilder.setSortBy("name");

        Backendless.Persistence.of(EmployeeProfile.class).find(queryBuilder, new AsyncCallback<List<EmployeeProfile>>() {
            @Override
            public void handleResponse(List<EmployeeProfile> response) {
                resuLT[0] = response;
                toastMessage("employee registration: " + response.size());
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                toastMessage("fault occurred");
            }
        });

        /*Backendless.Persistence.of(Person.class).find(queryBuilder, new AsyncCallback<List<Person>>() {
            @Override
            public void handleResponse(List<Person> response) {
                resuLT[0] = response;
            }

            @Override
            public void handleFault(BackendlessFault fault) {

            }
        });

         */
    }

    public void writeToCard(){
        toastMessage("oh wow! : " + resuLT[0].size());
        MyAdapter myAdapter = new MyAdapter();
        mListView.setAdapter(myAdapter);
    }

    public class MyAdapter extends BaseAdapter{

        List<EmployeeProfile> response = resuLT[0];

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
            ImageView profileImage = convertView.findViewById(R.id.cardRegImageView);
            TextView fullName = convertView.findViewById(R.id.cardRegFullName);
            TextView office = convertView.findViewById(R.id.cardRegOffice);
            TextView email = convertView.findViewById(R.id.cardRegBennEmail);
            TextView phone = convertView.findViewById(R.id.cardRegPhone);

            profileImage.setImageResource(R.drawable.adm2_wesley);
            fullName.setText(response.get(position).getFirstName() + " " + response.get(position).getLastName());
            office.setText(response.get(position).getOffice());
            email.setText(response.get(position).getBennEmail());
            phone.setText(("000-000-000"));

            return convertView;
        }
    }

    public void toastMessage( String msg){
        Toast.makeText(EmployeeRegistration.this, msg, Toast.LENGTH_SHORT).show();
    }
}