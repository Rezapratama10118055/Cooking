package com.example.cook.Model.pencarian;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Result {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("thumb")
    @Expose
    private String thumb;
    @SerializedName("key")
    @Expose
    private String key;
    @SerializedName("times")
    @Expose
    private String times;
    @SerializedName("serving")
    @Expose
    private String serving;
    @SerializedName("difficulty")
    @Expose
    private String difficulty;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public String getServing() {
        return serving;
    }

    public void setServing(String serving) {
        this.serving = serving;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

}