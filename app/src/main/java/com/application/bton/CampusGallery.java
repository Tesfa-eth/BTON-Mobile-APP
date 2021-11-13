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

    Button minimize;
    CardView toMinimize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campus_gallery);

        minimize = findViewById(R.id.minimize);
        toMinimize = findViewById(R.id.exampleImages);

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
    }
}