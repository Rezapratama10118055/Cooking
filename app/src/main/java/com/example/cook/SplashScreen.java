package com.example.cook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.cook.Viewpager.ViewPageActivity;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splesh_screen);

        Thread thread = new Thread(){
            public void run(){
                try{
                    sleep(5000);
                }catch(InterruptedException ex){
                    ex.printStackTrace();
                }finally {
                    startActivity(new Intent(SplashScreen.this, ViewPageActivity.class));
                    finish();
                }
            }
        };
        thread.start();
    }


    }
