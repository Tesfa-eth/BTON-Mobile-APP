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
            case R.id.btnDirectory:
                Intent intent_dir = new Intent(Homepage.this, CampusDirectory.class);
                startActivity(intent_dir);
                break;
            case  R.id.btnAboutBTN:
                Intent aboutBtn_intent = new Intent(Homepage.this, AboutBTONCollege.class);
                startActivity(aboutBtn_intent);
                break;
            case  R.id.btnHeathCenter:
                Intent healthCenter_intent = new Intent(Homepage.this, HealthCenter.class);
                startActivity(healthCenter_intent);
                break;

            case R.id.btnAnnouncement:
                Intent announcement_intent = new Intent(Homepage.this, GeneralAnnouncement.class);
                startActivity(announcement_intent);
                break;

            default:
                break;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        Button btnGallery = findViewById(R.id.btnGallery);
        btnGallery.setOnClickListener(this);

        Button btnDirectory = findViewById(R.id.btnDirectory);
        btnDirectory.setOnClickListener(this);

        Button btnAboutBTN = findViewById(R.id.btnAboutBTN);
        btnAboutBTN.setOnClickListener(this);

        Button btnHealthCenter = findViewById(R.id.btnHeathCenter);
        btnHealthCenter.setOnClickListener(this);

        Button btnAnnouncement = findViewById(R.id.btnAnnouncement);
        btnAnnouncement.setOnClickListener(this);

    }
}