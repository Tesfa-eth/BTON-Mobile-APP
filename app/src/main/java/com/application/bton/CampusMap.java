package com.application.bton;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;

public class CampusMap extends AppCompatActivity {
    PDFView pdfCampusMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campus_map);

        pdfCampusMap = findViewById(R.id.pdfCampusMap);
        pdfCampusMap.fromAsset("benn_campus_map.pdf").load();
    }
}