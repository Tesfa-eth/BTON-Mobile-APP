package com.application.bton;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;

public class CampusMap extends AppCompatActivity implements View.OnClickListener {
    PDFView pdfCampusMap;
    public final Integer[] rotate = {0}; // rotate counter
    public final Integer[] zoom = {1}; // zoom scale counter
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campus_map);

        pdfCampusMap = findViewById(R.id.pdfCampusMap);
        pdfCampusMap.fromAsset("benn_campus_map_rotated.pdf").load();
        Button rotateButton = findViewById(R.id.rotateButton);
        rotateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //pdfCampusMap.zoomWithAnimation(2);
                int rotateValue = rotate[0]; // get counter current value
                if(rotateValue==0){
                    pdfCampusMap.fromAsset("benn_campus_map_rotated_upsideDown.pdf").load();
                    rotate[0]++;
                    zoom[0]=1; // reset zoom value to 1 after any rotation
                }
                else if(rotateValue==1){
                    pdfCampusMap.fromAsset("benn_campus_map.pdf").load();
                    rotate[0]++;
                    zoom[0]=1; // reset zoom value to 1 after any rotation
                }
                else{
                    pdfCampusMap.fromAsset("benn_campus_map_rotated.pdf").load();
                    rotate[0] = 0;
                    zoom[0]=1; // reset zoom value to 1 after any rotation
                }
            }
        });

        Button btnZoomIN = findViewById(R.id.btnZoomIN);
        Button btnZoomOUT = findViewById(R.id.btnZoomOUT);

        btnZoomIN.setOnClickListener(this);
        btnZoomOUT.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        int zoomValue = zoom[0]; // get zoom counter current value
        switch (v.getId()){
            case R.id.btnZoomIN:
                if (zoomValue < pdfCampusMap.getMaxZoom() && zoomValue > 0){
                    zoom[0]++;
                    zoomValue++;
                    pdfCampusMap.zoomWithAnimation(zoomValue);
                }
                break;
            case R.id.btnZoomOUT:
                if(zoomValue > 0 && zoomValue > pdfCampusMap.getMinZoom()){
                    zoom[0]--;
                    zoomValue--;
                    pdfCampusMap.zoomWithAnimation(zoomValue);
                }
                break;
            default:
                break;
        }
    }
    public void toastMessage( String msg){
        Toast.makeText(CampusMap.this, msg, Toast.LENGTH_SHORT).show();
    }
}