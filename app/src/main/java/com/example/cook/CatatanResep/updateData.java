package com.example.cook.CatatanResep;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cook.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class updateData extends AppCompatActivity {

    private EditText ResepBaru, bahanResep, LangkahResep;
    private Button update;
    private DatabaseReference database;
    private FirebaseAuth auth;
    private String cekresep, cekbahan, ceklangkah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);
        getSupportActionBar().setTitle("Update Data");
        ResepBaru = findViewById(R.id.new_resep);
        bahanResep = findViewById(R.id.new_bahanresep);
        LangkahResep = findViewById(R.id.new_langkahresep);
        update = findViewById(R.id.update);

        //Mendapatkan Instance autentikasi dan Referensi dari Database
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance().getReference();
        getData();
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Mendapatkan Data Mahasiswa yang akan dicek
                cekresep = ResepBaru.getText().toString();
                cekbahan = bahanResep.getText().toString();
                ceklangkah = LangkahResep.getText().toString();

                //Mengecek agar tidak ada data yang kosong, saat proses update
                if(isEmpty(cekresep) || isEmpty(cekbahan) || isEmpty(ceklangkah)){
                    Toast.makeText(updateData.this, "Data tidak boleh ada yang kosong", Toast.LENGTH_SHORT).show();
                }else {
                    /*
                      Menjalankan proses update data.
                      Method Setter digunakan untuk mendapakan data baru yang diinputkan User.
                    */
                    data_resep setresep = new data_resep();
                    setresep.setJudulresep(ResepBaru.getText().toString());
                    setresep.setBahanbahannya(bahanResep.getText().toString());
                    setresep.setLangkahlangkah(LangkahResep.getText().toString());
                    updateresep(setresep);
                }
            }
        });
    }

    // Mengecek apakah ada data yang kosong, sebelum diupdate
    private boolean isEmpty(String s){
        return TextUtils.isEmpty(s);
    }

    //Menampilkan data yang akan di update
    private void getData(){
        //Menampilkan data dari item yang dipilih sebelumnya
        final String getresep = getIntent().getExtras().getString("dataresep");
        final String getbahan = getIntent().getExtras().getString("databahan");
        final String getlangkah = getIntent().getExtras().getString("datalangkah");
        ResepBaru.setText(getresep);
        bahanResep.setText(getbahan);
        LangkahResep.setText(getlangkah);
    }

    //Proses Update data yang sudah ditentukan
    private void updateresep(data_resep resep){
        String userID = auth.getUid();
        String getKey = getIntent().getExtras().getString("getPrimaryKey");
        database.child("Admin")
                .child(userID)
                .child("Resep")
                .child(getKey)
                .setValue(resep)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        ResepBaru.setText("");
                        bahanResep.setText("");
                        LangkahResep.setText("");
                        Toast.makeText(updateData.this, "Data Berhasil diubah", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
    }
}

