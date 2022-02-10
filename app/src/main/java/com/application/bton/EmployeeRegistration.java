package com.application.bton;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import weborb.client.Fault;

public class EmployeeRegistration extends AppCompatActivity {
    EditText lastnameText, firstnameText;
    Button btnSearch;
    ListView mListView;
    Spinner departmentSpinner;
    public final String[] spinnerStorage = {"department"};
    public final List<EmployeeProfile>[] resuLT = new List[]{new ArrayList<>()};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_registration);

        getEverything();

        // list view to display profiles
        mListView = findViewById(R.id.regListView);

        // SEARCH BY:
        // *name
        lastnameText = findViewById(R.id.edtTxtLastName);
        firstnameText = findViewById(R.id.edtTxtFirstName);
        // *spinner
        departmentSpinner = (Spinner) findViewById(R.id.spinnerDepartment);
        ArrayAdapter<CharSequence> adapterDepartment = ArrayAdapter.createFromResource(this,
                R.array.departments, android.R.layout.simple_spinner_item);
        adapterDepartment.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        departmentSpinner.setAdapter(adapterDepartment);
        departmentSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerStorage[0] = parent.getItemAtPosition(position).toString();
                searchBy(spinnerStorage[0], null, null);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                spinnerStorage[0] = "All";
            }
        });


        btnSearch = findViewById(R.id.btnSearch);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeToCard();
            }
        });
    }

    public void searchBy(String department, String firstname, String lastName){
        String whereClause;
        DataQueryBuilder queryBuilder = DataQueryBuilder.create();
        //toastMessage("some" + lastnameText.getText().toString().equals(""));

        // if either first or last name is filled for search
        if(department.equals("All")){
            getEverything();
            return;
        }
        // // Todo: use array [firstname, lastname, department] for search purposes!



//        if(firstname != null || lastName != null){
//            toastMessage("Going here!" + lastName);
//            if(firstname != null & lastName != null){
//                whereClause = "firstName = '" + firstname + "' and lastName = '" + lastName + "'";
//                queryBuilder.setWhereClause(whereClause);
//            }
//            else if(firstname != null){
//                whereClause = "firstName = '" + firstname + "'";
//                queryBuilder.setWhereClause(whereClause);
//            }
//            else if(lastName != null){
//                whereClause = "lastName = '" + lastName + "'";
//                queryBuilder.setWhereClause(whereClause);
//            }
//        }
        // if one of them is provided
        //if(firstname != null && lastName != null && department != null)
        whereClause = "department = '" + department + "'";
        queryBuilder.setWhereClause(whereClause);

        queryBuilder.setPageSize(100).setOffset(0);
        //queryBuilder.setSortBy("name");

        Backendless.Persistence.of(EmployeeProfile.class).find(queryBuilder, new AsyncCallback<List<EmployeeProfile>>() {
            @Override
            public void handleResponse(List<EmployeeProfile> response) {
                resuLT[0] = response;
                //toastMessage("employee registration: " + response.size());
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                toastMessage("fault occurred");
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
                //toastMessage("employee registration: " + response.size());
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                toastMessage("fault occurred");
            }
        });
    }

    public void writeToCard(){
        //toastMessage("oh wow! : " + resuLT[0].size());
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
            TextView departmemt = convertView.findViewById(R.id.cardRegDepartment);
            TextView positionInOffice = convertView.findViewById(R.id.cardRegPosition);
            TextView office = convertView.findViewById(R.id.cardRegOffice);
            TextView email = convertView.findViewById(R.id.cardRegBennEmail);
            TextView phone = convertView.findViewById(R.id.cardRegPhone);

            //profileImage.setImageResource(R.drawable.adm2_wesley);
            //Glide.with(this).load(response.get(position).getImageUrl()).into(profileImage);
            Glide.with(EmployeeRegistration.this).load(response.get(position).getImageUrl()).into(profileImage);
            fullName.setText(response.get(position).getFirstName() + " " + response.get(position).getLastName());
            office.setText(response.get(position).getOffice());
            email.setText(response.get(position).getBennEmail());
            phone.setText((response.get(position).getPhone()));

            // Todo: add this checker for all setTetxts above.
            //toastMessage(response.get(position).getDepartment());
            //toastMessage("position: " + (response.get(position).getPosition() == null));
            if(!(response.get(position).getDepartment() == null)){
                departmemt.setText(response.get(position).getDepartment());
            }
            if(!(response.get(position).getPosition() == null)){
                positionInOffice.setText(response.get(position).getPosition());
            }

            return convertView;
        }
    }

    public void toastMessage( String msg){
        Toast.makeText(EmployeeRegistration.this, msg, Toast.LENGTH_SHORT).show();
    }
}