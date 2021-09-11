package com.dionkn.opaku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.analytics.FirebaseAnalytics;

public class RegisterActivity extends AppCompatActivity {
    private FirebaseAnalytics Regfirebaseanalytics;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        getSupportActionBar().hide();

        Regfirebaseanalytics = FirebaseAnalytics.getInstance(this);

        backButton = (Button) findViewById(R.id.butback_register);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(backIntent);
            }
        });

    }
}