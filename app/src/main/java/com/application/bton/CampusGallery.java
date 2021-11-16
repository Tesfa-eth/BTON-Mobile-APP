package com.application.bton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.text.Layout;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.Button;

public class CampusGallery extends AppCompatActivity {

    //declaring variables
    Button btnBarn, btnGeneral, btnCommons;
    CardView barnCardView, generalCardView, commonsCardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campus_gallery);

        //respective buttons to cardViews
        btnBarn = findViewById(R.id.btnBarn);
        btnGeneral = findViewById(R.id.btnGeneral);
        btnCommons = findViewById(R.id.btnCommons);

        //cardViews
        generalCardView = findViewById(R.id.generalCardView);
        barnCardView = findViewById(R.id.barnCardView);
        commonsCardView = findViewById(R.id.commonsCardView);

        //Campus view on click listener
        btnBarn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(barnCardView.getVisibility() == View.GONE){
                    TransitionManager.beginDelayedTransition(barnCardView, new AutoTransition());
                    barnCardView.setVisibility(View.VISIBLE);
                    btnBarn.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
                }
                else{
                    barnCardView.setVisibility(View.GONE);
                    btnBarn.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                }
            }
        });

        //Barn view on click listener
        btnGeneral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(generalCardView.getVisibility() == View.GONE){
                    TransitionManager.beginDelayedTransition(generalCardView, new AutoTransition());
                    generalCardView.setVisibility(View.VISIBLE);
                    btnGeneral.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
                }
                else{
                    generalCardView.setVisibility(View.GONE);
                    btnGeneral.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                }
            }
        });

        //commons on click listener
        btnCommons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(commonsCardView.getVisibility() == View.GONE){
                    TransitionManager.beginDelayedTransition(commonsCardView, new AutoTransition());
                    commonsCardView.setVisibility(View.VISIBLE);
                    btnCommons.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
                }
                else{
                    commonsCardView.setVisibility(View.GONE);
                    btnCommons.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                }
            }
        });


    }
}