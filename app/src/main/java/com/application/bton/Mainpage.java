package com.application.bton;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Mainpage extends AppCompatActivity implements View.OnClickListener{

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.btnGallery:
                Intent intent = new Intent(Mainpage.this, Log.class);
                startActivity(intent);
                break;
            case  R.id.btnAboutBTN:
                Intent aboutBtn_intent = new Intent(Mainpage.this, AboutBennington.class);
                startActivity(aboutBtn_intent);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);

        Button btnGallery = findViewById(R.id.btnGallery);
        btnGallery.setOnClickListener(this);

        Button btnAboutBTN = findViewById(R.id.btnAboutBTN);
        btnAboutBTN.setOnClickListener(this);
    }
}