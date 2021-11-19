package com.application.bton;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ContactHealthCenter extends AppCompatActivity {

    EditText contactHCsubject, contactHCbody;
    Button btnSendEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_health_center);

        //find elements
        contactHCsubject = findViewById(R.id.contactHCsubject);
        contactHCbody = findViewById(R.id.contactHCbody);
        btnSendEmail = findViewById(R.id.btnSendEmail);

        btnSendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("mailto:stesfatsionmulugeta@gmail.com"));
                intent.putExtra(Intent.EXTRA_SUBJECT, contactHCsubject.getText().toString());
                intent.putExtra(Intent.EXTRA_SUBJECT, contactHCbody.getText().toString());
                startActivity(intent);
            }
        });
    }
}