package com.application.bton;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LogIn extends AppCompatActivity implements View.OnClickListener {
    EditText edtTxtname, edtTxtPassword;


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.btnsign_in:
                edtTxtname = (EditText) findViewById(R.id.edtTxtName);
                edtTxtPassword = (EditText) findViewById(R.id.edtTxtPassword);

                String username = edtTxtname.getText().toString();
                String password = edtTxtPassword.getText().toString();
                if (username.equals("tesfaAdmin") && password.equals("123456")){
                    Toast.makeText(this, "Welcome back " + username, Toast.LENGTH_SHORT).show();
                    Intent intentToMain = new Intent(LogIn.this, Homepage.class);
                    startActivity(intentToMain);
                    break;
                }
                else{
                    Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                    break;
                }
            case R.id.btnToRegister:
                Intent intent = new Intent(LogIn.this, Homepage.class);
                startActivity(intent);
                break;
            case R.id.btnVisitor:
                // sends to dhall menu section for now
                // Intent visitor_intent = new Intent(LogIn.this, Homepage.class);
                Intent visitor_intent = new Intent(LogIn.this, DhallMenu.class);
                startActivity(visitor_intent);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        Button btnSignIn = findViewById(R.id.btnsign_in);
        btnSignIn.setOnClickListener(this);

        Button btnToRegister = findViewById(R.id.btnToRegister);
        btnToRegister.setOnClickListener(this);

        Button btnVisitor = findViewById(R.id.btnVisitor);
        btnVisitor.setOnClickListener(this);
    }
}