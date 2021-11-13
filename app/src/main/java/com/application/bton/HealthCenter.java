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

    ConstraintLayout expandableview;
    Button downarrow;
    CardView information;
    CardView location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_center);

        expandableview = findViewById(R.id.expandableView);
        downarrow = findViewById(R.id.downarrow);
        information = findViewById(R.id.information);
        location = findViewById(R.id.location);

        downarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (expandableview.getVisibility() == View.GONE){
                    TransitionManager.beginDelayedTransition(information, new AutoTransition());
                    TransitionManager.beginDelayedTransition(location, new AutoTransition());
                    expandableview.setVisibility(View.VISIBLE);
                    downarrow.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
                }
                else{
                    //this code is commented in order to avoid overlapping
                    //TransitionManager.beginDelayedTransition(information, new AutoTransition());
                    TransitionManager.beginDelayedTransition(location, new AutoTransition());
                    expandableview.setVisibility(View.GONE);
                    downarrow.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                }
            }
        });

    }
}