package com.example.cook.CatatanResep;

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

import com.example.cook.LoginActivity;
import com.example.cook.R;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Collections;

public class CatatanResep extends AppCompatActivity implements View.OnClickListener {

    private EditText TXT_Judul, TXT_Bahan, TXT_Langkah;

    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    private Button Logout, Simpan, Login, ShowData;

    private int RC_SIGN_IN = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catatan_resep);

        progressBar = findViewById(R.id.progress);
        progressBar.setVisibility(View.GONE);


        //Inisialisasi ID (Button)

        Simpan = findViewById(R.id.save);
        Simpan.setOnClickListener(this);
        ShowData = findViewById(R.id.showdata);
        ShowData.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance(); //Mendapakan Instance Firebase Autentifikasi

        //Inisialisasi ID (EditText)
        TXT_Judul = findViewById(R.id.jdlResep);
        TXT_Bahan = findViewById(R.id.BhnResep);
        TXT_Langkah = findViewById(R.id.LkhResep);

        if(mAuth.getCurrentUser() == null){
            defaultUI();

        }else {
            updateUI();
        }
    }

    private void defaultUI() {

        Simpan.setEnabled(true);
        ShowData.setEnabled(true);
        TXT_Judul.setEnabled(true);
        TXT_Bahan.setEnabled(true);
        TXT_Langkah.setEnabled(true);

    }
    private void updateUI(){

        Simpan.setEnabled(true);
        ShowData.setEnabled(true);
        TXT_Judul.setEnabled(true);
        TXT_Bahan.setEnabled(true);
        TXT_Langkah.setEnabled(true);

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
                Toast.makeText(CatatanResep.this, "Login Berhasil", Toast.LENGTH_SHORT).show();
                updateUI();
            } else {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(CatatanResep.this, "Login Dibatalkan", Toast.LENGTH_SHORT).show();

            }
        }
    }




    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.save:


                mAuth = FirebaseAuth.getInstance();
                mAuth.getCurrentUser();



                String getUserID = mAuth.getCurrentUser().getUid();

                //Mendapatkan Instance dari Database
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference getReference;

                //Menyimpan Data yang diinputkan User kedalam Variable
                String getJudul = TXT_Judul.getText().toString();
                String getBahan = TXT_Bahan.getText().toString();
                String getLangkah = TXT_Langkah.getText().toString();

                getReference = database.getReference(); // Mendapatkan Referensi dari Database

                // Mengecek apakah ada data yang kosong
                if(isEmpty(getJudul) && isEmpty(getBahan) && isEmpty(getLangkah)){
                    //Jika Ada, maka akan menampilkan pesan singkan seperti berikut ini.
                    Toast.makeText(CatatanResep.this, "Data tidak boleh ada yang kosong", Toast.LENGTH_SHORT).show();
                }else {
        /*
        Jika Tidak, maka data dapat diproses dan meyimpannya pada Database
        Menyimpan data referensi pada Database berdasarkan User ID dari masing-masing Akun
        */
                    getReference.child("Admin").child(getUserID).child("Resep").push()
                            .setValue(new data_resep(getJudul, getBahan, getLangkah))
                            .addOnSuccessListener(this, new OnSuccessListener() {
                                @Override
                                public void onSuccess(Object o) {
                                    //Peristiwa ini terjadi saat user berhasil menyimpan datanya kedalam Database
                                    TXT_Judul.setText("");
                                    TXT_Bahan.setText("");
                                    TXT_Langkah.setText("");
                                    Toast.makeText(CatatanResep.this, "Data Tersimpan", Toast.LENGTH_SHORT).show();
                                }
                            });
                }
                break;

//            case R.id.logout:
//                // Statement program untuk logout/keluar
//                AuthUI.getInstance()
//                        .signOut(this)
//                        .addOnCompleteListener(new OnCompleteListener() {
//                            @Override
//                            public void onComplete(@NonNull Task task) {
//                                Toast.makeText(CatatanResep.this, "Logout Berhasil", Toast.LENGTH_SHORT).show();
//                                finish();
//                            }
//                        });
//                break;
            case R.id.showdata:
                startActivity(new Intent(CatatanResep.this, mylist.class));
                break;



        }
    }
}
