package com.example.cook.realm;

import android.content.Context;

import com.example.cook.Model.Example;
import com.example.cook.Model.Result;

import java.security.Key;
import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmHelper {

    private Context mContext;
    private Realm realm;
    private RealmResults<com.example.cook.Model.Result> modelResep;


    public RealmHelper(Context mContext) {
        this.mContext = mContext;
        Realm.init(mContext);
        realm = Realm.getDefaultInstance();
    }
    public List<Result> showFavoriteResep() {
        List<Result> data = new ArrayList<>();
        modelResep  = realm.where(Result.class).findAll();

        if (modelResep.size() > 0) {
            for (int i = 0; i < modelResep.size(); i++) {
                Result result = new Result();
                result.setKey(modelResep.get(i).getKey());
                result.setTitle(modelResep.get(i).getTitle());
                result.setPortion(modelResep.get(i).getPortion());
                result.setDificulty(modelResep.get(i).getDificulty());
                result.setTimes(modelResep.get(i).getTimes());
                result.setThumb(modelResep.get(i).getThumb());
                data.add(result);
            }
        }
        return data;
    }
    public void addFavoriteMovie(String kay, String Title, String gambar, String waktu,
                                 String tingkat, String Porsi) {
        Result result = new Result();
        result.setKey(kay);
        result.setTitle(Title);
        result.setThumb(gambar);
        result.setTimes(waktu);
        result.setDificulty(tingkat);
        result.setPortion(Porsi);


        realm.beginTransaction();
        realm.copyToRealm(result);
        realm.commitTransaction();

        //Toast.makeText(mContext, "Data berhasil ditambah", Toast.LENGTH_SHORT).show();
    }

    public void deleteFavoriteMovie(String kay) {
        realm.beginTransaction();
        modelResep = realm.where(Result.class).findAll();
        modelResep.deleteAllFromRealm();
        realm.commitTransaction();

        //Toast.makeText(mContext, "Data berhasil dihapus", Toast.LENGTH_SHORT).show();
    }
}