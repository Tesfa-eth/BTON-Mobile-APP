package com.application.bton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;

import java.util.ArrayList;

public class HealthCenter extends AppCompatActivity {

    ConstraintLayout expinformation, expScheduleApt;
    Button btnInformation, btnscheduleAptm;
    CardView information, scheduleaptm;

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
        btnscheduleAptm = findViewById(R.id.btnscheduleAptm);
        scheduleaptm = findViewById(R.id.scheduleaptm);


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

    }
}