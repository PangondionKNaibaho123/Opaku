package com.dionkn.opaku;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dionkn.opaku.User.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    private FirebaseAnalytics Regfirebaseanalytics;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;
    private Button backButton;
    private Button registerButton;

    private EditText et_email_reg;
    private EditText et_pass_reg;
    private EditText et_nl_reg;
    private EditText et_np_reg;

    private String TAG = "REGISTER";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        getSupportActionBar().hide();

        //firebase analytics
        Regfirebaseanalytics = FirebaseAnalytics.getInstance(this);

        et_email_reg = (EditText) findViewById(R.id.et_email_register);
        et_pass_reg = (EditText) findViewById(R.id.et_pass_register);
        et_nl_reg = (EditText) findViewById(R.id.et_nl_register);
        et_np_reg = (EditText) findViewById(R.id.et_np_register);
        backButton = (Button) findViewById(R.id.butback_register);
        registerButton = (Button) findViewById(R.id.butreg_register);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        backButton.setOnClickListener(this);
        registerButton.setOnClickListener(this);

    }

    private boolean validateForm(){
        boolean result = true;
        if(TextUtils.isEmpty(et_email_reg.getText().toString())){
            et_email_reg.setError("email tidak boleh kosong");
        }else{
            et_email_reg.setError(null);
        }

        if(TextUtils.isEmpty(et_pass_reg.getText().toString())){
            et_pass_reg.setError("password tidak boleh kosong");
        }else{
            et_pass_reg.setError(null);
        }

        if(TextUtils.isEmpty(et_nl_reg.getText().toString())){
            et_nl_reg.setError("Nama lengkap tidak boleh kosong");
        }else{
            et_nl_reg.setError(null);
        }

        if(TextUtils.isEmpty(et_np_reg.getText().toString())){
            et_np_reg.setError("nomor telepon tidak boleh kosong");
        }else{
            et_np_reg.setError(null);
        }

        return result;
    }

    private void registerUser(){
        String acemail = et_email_reg.getText().toString().trim();
        String acpass = et_pass_reg.getText().toString().trim();

        if(!validateForm()){
            return;
        }else{
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Akun sedang didaftarkan....");
            progressDialog.show();

            firebaseAuth.createUserWithEmailAndPassword(acemail, acpass)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                progressDialog.dismiss();
                                Log.d(TAG, "create User success");
                                onAuthSuccess(FirebaseAuth.getInstance().getCurrentUser());
                            }else{
                                progressDialog.dismiss();
                                Log.w(TAG, "Create user account fail", task.getException());
                                Toast.makeText(RegisterActivity.this, "Mohon maaf, pendaftaran gagal, email sudah digunakan, atau password kurang dari 6 karakter"
                                , Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }
    }

    private void onAuthSuccess(FirebaseUser user){
        String acpass = et_pass_reg.getText().toString().trim();
        String acnl = et_nl_reg.getText().toString().trim();
        String acpn = et_np_reg.getText().toString().trim();
        int jenis_user = 0;
        String userId = user.getUid().toString();
        String alamat = "";

        User neo_user = new User(user.getEmail().replace('.', '_'), acpass, acnl, acpn, jenis_user, userId, alamat);
        FirebaseDatabase.getInstance().getReference("Users").child(user.getUid()).setValue(neo_user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(RegisterActivity.this, "Register berhasil", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(RegisterActivity.this, "Register gagal", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    @Override
    public void onClick(View view) {
        if(view == registerButton){
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

            if(inputMethodManager.isAcceptingText()){
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }

            validateForm();
            registerUser();

        }else if(view == backButton){
            Intent backIntent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(backIntent);
        }

    }
}