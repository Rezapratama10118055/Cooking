package com.example.cook.Viewpager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.cook.R;


public class slideAdapter extends PagerAdapter {
  Context context;
  LayoutInflater layoutInflater;

  public slideAdapter(Context context){
      this.context=context;
  }
  public int[] slide_gambar = {
          R.drawable.harvest,
          R.drawable.salad,
          R.drawable.man
  };
  public String[] slide_judul = {
     "ResepBook Apps",
     "FITUR",
     "PENJELASAN"
  };
  public String[] slide_doc = {
    "Adalah suatu aplikasi buatan saya sendiri yang terdiri dari beberapa fitur yang memudahkan orang-orang dalam memasak.",
          " Di aplikasi ResepBook Apps terdapat fitur Home, Tampil Makanan, Detail Makanan, Tentang Aplikasi",
          "Aplikasi ResepBook adalah suatu aplikasi yang dirancang untuk memenuhi tugas UAS akb saya - REZA PRATAMA  "
  };

    @Override
    public int getCount() {
        return slide_judul.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (RelativeLayout) object;
    }

  @NonNull
  @Override
  public Object instantiateItem(@NonNull ViewGroup container, int position) {
     layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
     View view = layoutInflater.inflate(R.layout.card_pageview,container,false);

    ImageView slidegambar = (ImageView) view.findViewById(R.id.imagewr);
    TextView slidejudul = (TextView) view.findViewById(R.id.heding);
    TextView slidedoc = (TextView) view.findViewById(R.id.silde_doc);

    slidegambar.setImageResource(slide_gambar[position]);
    slidejudul.setText(slide_judul[position]);
    slidedoc.setText(slide_doc[position]);

    container.addView(view);
    return view;

  }

  @Override
  public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
    container.removeView((RelativeLayout)object);
  }
}
