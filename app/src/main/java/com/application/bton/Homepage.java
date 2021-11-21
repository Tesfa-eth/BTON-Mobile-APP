package com.application.bton;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Homepage extends AppCompatActivity implements View.OnClickListener {


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.btnGallery:
                Intent intent = new Intent(Homepage.this, CampusGallery.class);
                startActivity(intent);
                break;
            /**case  R.id.btnAboutBTN:
                Intent aboutBtn_intent = new Intent(Mainpage.this, AboutBTONCollege.class);
                startActivity(aboutBtn_intent);
                break;
            case  R.id.btnHeathCenter:
                Intent healthCenter_intent = new Intent(Mainpage.this, HealthCenter.class);
                startActivity(healthCenter_intent);
                break;
            default:
                break;*/
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        Button btnGallery = findViewById(R.id.btnGallery);
        btnGallery.setOnClickListener(this);

        //Button btnAboutBTN = findViewById(R.id.btnAboutBTN);
        //btnAboutBTN.setOnClickListener(this);

        //Button btnHealthCenter = findViewById(R.id.btnHeathCenter);
        //btnHealthCenter.setOnClickListener(this);
    }
}