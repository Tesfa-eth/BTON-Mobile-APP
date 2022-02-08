package com.application.bton;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;

import java.util.List;

public class Homepage extends AppCompatActivity implements View.OnClickListener {


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.btnGallery:
                Intent intent = new Intent(Homepage.this, CampusGallery.class);
                startActivity(intent);
                break;
            case R.id.btnDirectory:
                Intent intent_dir = new Intent(Homepage.this, EmployeeRegistration.class);
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

            case R.id.btnSclCalander:
                Intent sclCalander_intent = new Intent(Homepage.this, SchoolCalander.class);
                startActivity(sclCalander_intent);
                break;

            case R.id.btnDhall:
                startActivity(new Intent(Homepage.this, DinningHallMenuSection.class));
                break;

            case R.id.btnCampusMap:
                startActivity(new Intent(Homepage.this, CampusMap.class));
                break;

            case R.id.btnCampusShuttle:
                startActivity(new Intent(Homepage.this, CampusShuttle.class));
                break;

            case R.id.btnMeyerRecBarn:
                startActivity(new Intent(Homepage.this, MeyerRecBarn.class));
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

        Button btnSchoolCalendar = findViewById(R.id.btnSclCalander);
        btnSchoolCalendar.setOnClickListener(this);

        Button btnDhall = findViewById(R.id.btnDhall);
        btnDhall.setOnClickListener(this);

        Button btnCampusMap = findViewById(R.id.btnCampusMap);
        btnCampusMap.setOnClickListener(this);

        Button btnCampusShuttle = findViewById(R.id.btnCampusShuttle);
        btnCampusShuttle.setOnClickListener(this);

        Button btnMeyerRecBarn = findViewById(R.id.btnMeyerRecBarn);
        btnMeyerRecBarn.setOnClickListener(this);

    }
    public void toastMessage( String msg){
        Toast.makeText(Homepage.this, msg, Toast.LENGTH_SHORT).show();
    }
}