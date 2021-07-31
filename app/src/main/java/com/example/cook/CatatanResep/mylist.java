package com.example.cook.CatatanResep;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.cook.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class mylist extends AppCompatActivity implements RecyclerViewAdapter.dataListener {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private DatabaseReference reference;
    private ArrayList<data_resep> data_reseps;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_list);
        recyclerView = findViewById(R.id.datalist);
        getSupportActionBar().setTitle("Data Resep");
        mAuth = FirebaseAuth.getInstance();
        MyRecyclerView();
        GetData();
    }
    private void MyRecyclerView() {

        //Menggunakan Layout Manager, Dan Membuat List Secara Vertical
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        //Membuat Underline pada Setiap Item Didalam List
        DividerItemDecoration itemDecoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.line));
        recyclerView.addItemDecoration(itemDecoration);
    }

    private void GetData() {
        Toast.makeText(getApplicationContext(), "Mohon Tunggu Sebentar...", Toast.LENGTH_LONG).show();
        reference = FirebaseDatabase.getInstance().getReference();
        reference.child("Admin").child(mAuth.getUid()).child("Resep").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                data_reseps = new ArrayList<>();
                for (DataSnapshot snapshot : datasnapshot.getChildren()) {
                    //Mapping data pada DataSnapshot ke dalam objek mahasiswa
                    data_resep dataResep = snapshot.getValue(data_resep.class);

                    //Mengambil Primary Key, digunakan untuk proses Update dan Delete
                    dataResep.setKey(snapshot.getKey());
                    data_reseps.add(dataResep);
                }

                //Inisialisasi Adapter dan data Mahasiswa dalam bentuk Array
                adapter = new RecyclerViewAdapter(data_reseps, mylist.this);

                //Memasang Adapter pada RecyclerView
                recyclerView.setAdapter(adapter);

                Toast.makeText(getApplicationContext(), "Data Berhasil Dimuat", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "Data Gagal Dimuat", Toast.LENGTH_LONG).show();
                Log.e("MyListActivity", error.getDetails() + " " + error.getMessage());
            }
        });

    }

        @Override
        public void onDeleteData(data_resep data, int position) {
            /*
             * Kode ini akan dipanggil ketika method onDeleteData
             * dipanggil dari adapter pada RecyclerView melalui interface.
             * kemudian akan menghapus data berdasarkan primary key dari data tersebut
             * Jika berhasil, maka akan memunculkan Toast
             */
            String userID = mAuth.getUid();
            if (reference != null) {
                reference.child("Admin")
                        .child(userID)
                        .child("Resep")
                        .child(data.getKey())
                        .removeValue()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(mylist.this, "Data Berhasil Dihapus", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        }
    }
