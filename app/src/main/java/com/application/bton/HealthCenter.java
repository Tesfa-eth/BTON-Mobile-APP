package com.application.bton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;

public class HealthCenter extends AppCompatActivity {

    ConstraintLayout expinformation, expScheduleApt, expLocation;
    Button btnInformation, btnscheduleAptm, btnlocation, toHealthCenter, toContactHCenter;
    CardView information, scheduleaptm, location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_center);

        //general information
        expinformation = findViewById(R.id.expInformation);
        btnInformation = findViewById(R.id.btnInformation);
        information = findViewById(R.id.information);

        //schedule appointment
        expScheduleApt = findViewById(R.id.expScheduleAptm);
        btnscheduleAptm = findViewById(R.id.btnhealthServices);
        scheduleaptm = findViewById(R.id.card_healthServices);

        //location
        expLocation = findViewById(R.id.expLocation);
        btnlocation = findViewById(R.id.btnlocation);
        location = findViewById(R.id.pyschServices);


        btnInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (expinformation.getVisibility() == View.GONE){
                    TransitionManager.beginDelayedTransition(information, new AutoTransition());
                    TransitionManager.beginDelayedTransition(scheduleaptm, new AutoTransition());
                    expinformation.setVisibility(View.VISIBLE);
                    btnInformation.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
                }
                else{
                    //this code is commented in order to avoid overlapping
                    //TransitionManager.beginDelayedTransition(information, new AutoTransition());
                    TransitionManager.beginDelayedTransition(scheduleaptm, new AutoTransition());
                    expinformation.setVisibility(View.GONE);
                    btnInformation.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                }
            }
        });

        btnscheduleAptm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (expScheduleApt.getVisibility() == View.GONE){
                    TransitionManager.beginDelayedTransition(scheduleaptm, new AutoTransition());
                    //TransitionManager.beginDelayedTransition(information, new AutoTransition()); may be get the next cardView id?
                    expScheduleApt.setVisibility(View.VISIBLE);
                    btnscheduleAptm.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
                }
                else{
                    //this code is commented in order to avoid overlapping
                    //TransitionManager.beginDelayedTransition(information, new AutoTransition());
                    // TransitionManager.beginDelayedTransition(scheduleaptm, new AutoTransition()); new view?
                    expScheduleApt.setVisibility(View.GONE);
                    btnscheduleAptm.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                }
            }
        });

        //location
        //expLocation = findViewById(R.id.expLocation);
        //btnlocation = findViewById(R.id.btnlocation);
        //location = findViewById(R.id.location);

        btnlocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (expLocation.getVisibility() == View.GONE){
                    TransitionManager.beginDelayedTransition(location, new AutoTransition());
                    //TransitionManager.beginDelayedTransition(information, new AutoTransition()); may be get the next cardView id?
                    expLocation.setVisibility(View.VISIBLE);
                    btnlocation.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
                }
                else{
                    //this code is commented in order to avoid overlapping
                    //TransitionManager.beginDelayedTransition(information, new AutoTransition());
                    // TransitionManager.beginDelayedTransition(scheduleaptm, new AutoTransition()); new view?
                    expLocation.setVisibility(View.GONE);
                    btnlocation.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                }
            }
        });

        // to health center map button clicked
        /*toHealthCenter = findViewById(R.id.toMapHealthCenter);
        toHealthCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(this, "Safe Journey!", Toast.LENGTH_SHORT).show();
                Intent intentToMain = new Intent(HealthCenter.this, MapHealthCenter.class);
                startActivity(intentToMain);
            }
        });*/

        // to contact button clicked
        toContactHCenter = findViewById(R.id.toContactHealthCenter);
        toContactHCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToMain = new Intent(HealthCenter.this, ContactHealthCenter.class);
                startActivity(intentToMain);
            }
        });
    }
}