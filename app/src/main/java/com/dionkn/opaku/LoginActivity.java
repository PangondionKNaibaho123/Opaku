package com.dionkn.opaku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.analytics.FirebaseAnalytics;

public class LoginActivity extends AppCompatActivity {
    private Button buttonRegister;
    private FirebaseAnalytics Loginfirebaseanalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        getSupportActionBar().hide();

        Loginfirebaseanalytics = FirebaseAnalytics.getInstance(this);

        buttonRegister = (Button) findViewById(R.id.button_register);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(registerIntent);
            }
        });


    }

}