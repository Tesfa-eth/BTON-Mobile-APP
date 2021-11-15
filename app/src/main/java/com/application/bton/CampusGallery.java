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

    Button minimize, minimize2;
    CardView toMinimize, toMinimize2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campus_gallery);

        minimize = findViewById(R.id.minimize);
        toMinimize = findViewById(R.id.exampleImages);

        minimize2 = findViewById(R.id.buttonDown);
        toMinimize2 = findViewById(R.id.vapaCardView);

        minimize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(toMinimize.getVisibility() == View.GONE){
                    TransitionManager.beginDelayedTransition(toMinimize, new AutoTransition());
                    toMinimize.setVisibility(View.VISIBLE);
                    minimize.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
                }
                else{
                    toMinimize.setVisibility(View.GONE);
                    minimize.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                }
            }
        });

        minimize2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(toMinimize2.getVisibility() == View.GONE){
                    TransitionManager.beginDelayedTransition(toMinimize2, new AutoTransition());
                    toMinimize2.setVisibility(View.VISIBLE);
                    minimize2.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
                }
                else{
                    toMinimize2.setVisibility(View.GONE);
                    minimize2.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                }
            }
        });
    }
}