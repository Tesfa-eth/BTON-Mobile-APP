package com.application.bton;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.getStarted:
                Toast.makeText(this, "Welcome to BTON mobile app", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, LogIn.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnHello = findViewById(R.id.getStarted);
        btnHello.setOnClickListener(this);
    }
}