package com.application.bton;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;

import java.util.List;

public class EmployeeRegistration extends AppCompatActivity {
    EditText lastname;
    Button btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_registration);

        lastname = findViewById(R.id.edtTxtFirstName);
        btnSearch = findViewById(R.id.btnSearch);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
                    public void handleResponse(List<Person> response) {
                        for(int i=0; i < response.size(); i++){
                            toastMessage(response.get(i).getName());
                        }
                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {
                        toastMessage("Some fault found" + fault);
                    }
                });

                // end of ackendless stuff

                lastname.setText("");
            }
        });
    }

    public void toastMessage( String msg){
        Toast.makeText(EmployeeRegistration.this, msg, Toast.LENGTH_SHORT).show();
    }
}