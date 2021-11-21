package com.application.bton;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Register extends AppCompatActivity implements View.OnClickListener{

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.btnRegister:
                Toast.makeText(this, "Welcome back", Toast.LENGTH_SHORT).show();
                Intent intentToMain = new Intent(Register.this, Homepage.class);
                startActivity(intentToMain);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(this);
    }
}