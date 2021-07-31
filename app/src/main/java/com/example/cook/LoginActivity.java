package com.example.cook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Collections;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText NIM, Nama, Jurusan;

    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    private Button Logoutgoogle, Simpan, Logingoogle, ShowData;

    private int RC_SIGN_IN = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressBar = findViewById(R.id.progressLogin);
        progressBar.setVisibility(View.GONE);


        //Inisialisasi ID (Button)


        Logingoogle = findViewById(R.id.logingoogle);
        Logingoogle.setOnClickListener(this);
        Logoutgoogle = findViewById(R.id.logoutgoogle);
        Logoutgoogle.setOnClickListener(this);


        mAuth = FirebaseAuth.getInstance(); //Mendapakan Instance Firebase Autentifikasi



        if(mAuth.getCurrentUser() == null){
            defaultUI();
        }else {
            updateUI();
        }
    }

    private void defaultUI() {
        Logingoogle.setEnabled(true);
        Logoutgoogle.setEnabled(false);

    }
    private void updateUI(){
        Logoutgoogle.setEnabled(true);
        Logingoogle.setEnabled(false);
        progressBar.setVisibility(View.GONE);

    }

    private boolean isEmpty(String s){
        return TextUtils.isEmpty(s);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // RC_SIGN_IN adalah kode permintaan yang Anda berikan ke startActivityForResult, saat memulai masuknya arus.
        if (requestCode == RC_SIGN_IN) {

            //Berhasil masuk
            if (resultCode == RESULT_OK) {
                Toast.makeText(LoginActivity.this, "Login Berhasil", Toast.LENGTH_SHORT).show();
                updateUI();
            } else {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(LoginActivity.this, "Login Dibatalkan", Toast.LENGTH_SHORT).show();

            }
        }
    }




    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.logingoogle:
                // Statement program untuk login/masuk
                startActivityForResult(AuthUI.getInstance()
                                .createSignInIntentBuilder()

                                //Memilih Provider atau Method masuk yang akan kita gunakan
                                .setAvailableProviders(Collections.singletonList(new AuthUI.IdpConfig.GoogleBuilder().build()))
                                .setIsSmartLockEnabled(false)
                                .build(),
                        RC_SIGN_IN);
                progressBar.setVisibility(View.VISIBLE);
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                break;


            case R.id.logoutgoogle:
                // Statement program untuk logout/keluar
                AuthUI.getInstance()
                        .signOut(this)
                        .addOnCompleteListener(new OnCompleteListener() {
                            @Override
                            public void onComplete(@NonNull Task task) {
                                Toast.makeText(LoginActivity.this, "Logout Berhasil", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        });
                break;



        }
    }
}
