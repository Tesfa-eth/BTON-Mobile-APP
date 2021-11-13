package com.application.bton;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Layout;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.Button;

public class CampusGallery extends AppCompatActivity {

    Button minimize;
    View toMinimize;

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
                    toMinimize.setVisibility(View.VISIBLE);
                }
                else{
                    toMinimize.setVisibility(View.GONE);
                }
            }
        });
    }
}