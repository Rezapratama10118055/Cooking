package com.example.cook.Viewpager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cook.LoginActivity;
import com.example.cook.R;

public class ViewPageActivity extends AppCompatActivity {
    private ViewPager mslideView;
    private LinearLayout mDotlayout;
    private TextView[] mdot;
    private slideAdapter slideAdapter;
    private Button lanjut,kembali;
    private int con;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_page);

        lanjut = (Button) findViewById(R.id.Lanjut);
        kembali = (Button) findViewById(R.id.Kembali);

        mslideView = (ViewPager)findViewById(R.id.page);
        mDotlayout = (LinearLayout)findViewById(R.id.linear);

        slideAdapter = new slideAdapter(this);
        mslideView.setAdapter(slideAdapter);

        adddataindekator(0);
        mslideView.addOnPageChangeListener(viewlistener);

        lanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mslideView.setCurrentItem(con + 1);
            }
        });
        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mslideView.setCurrentItem(con - 1);
            }
        });


    }
    public void adddataindekator(int position){
        mdot = new TextView[3];
        for(int i = 0; i < mdot.length; i++){
            mdot[i] = new TextView(this);
            mdot[i].setText(Html.fromHtml("&#8226;"));
            mdot[i].setTextSize(35);
            mdot[i].setTextColor(getResources().getColor(R.color.design_default_color_secondary));
            mDotlayout.addView(mdot[i]);
        }
        if(mdot.length > 0){
            mdot[position].setTextColor(getResources().getColor(R.color.white));

        }
    }
    ViewPager.OnPageChangeListener viewlistener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            adddataindekator(position);
            con = position;
            if(position == 0){
                lanjut.setEnabled(true);
                kembali.setEnabled(false);
                kembali.setVisibility(View.INVISIBLE);

                lanjut.setText("Lanjut");
                kembali.setText("");
            } else if(position == mdot.length - 1){
                lanjut.setEnabled(true);
                kembali.setEnabled(true);
                kembali.setVisibility(View.INVISIBLE);

                lanjut.setText("Selesai");
                kembali.setText("");
                lanjut.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(ViewPageActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                });
            }else{
                lanjut.setEnabled(true);
                kembali.setEnabled(false);
                kembali.setVisibility(View.INVISIBLE);

                lanjut.setText("Lanjut");
                kembali.setText("Kembali");
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
