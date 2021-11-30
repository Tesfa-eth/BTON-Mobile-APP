package com.application.bton;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class LogIn extends AppCompatActivity implements View.OnClickListener {

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.btnsign_in:
                Toast.makeText(this, "Welcome back", Toast.LENGTH_SHORT).show();
                Intent intentToMain = new Intent(LogIn.this, Homepage.class);
                startActivity(intentToMain);
                break;
            case R.id.btnToRegister:
                Intent intent = new Intent(LogIn.this, Register.class);
                startActivity(intent);
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
    }
}