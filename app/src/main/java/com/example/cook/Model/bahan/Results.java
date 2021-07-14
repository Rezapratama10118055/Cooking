package com.example.cook.Model.bahan;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Results {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("thumb")
    @Expose
    private Object thumb;
    @SerializedName("servings")
    @Expose
    private String servings;
    @SerializedName("times")
    @Expose
    private String times;
    @SerializedName("dificulty")
    @Expose
    private String dificulty;
    @SerializedName("author")
    @Expose
    private Author author;
    @SerializedName("desc")
    @Expose
    private String desc;
    @SerializedName("needItem")
    @Expose
    private List<NeedItem> needItem = null;
    @SerializedName("ingredient")
    @Expose
    private List<String> ingredient = null;
    @SerializedName("step")
    @Expose
    private List<String> step = null;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Object getThumb() {
        return thumb;
    }

    public void setThumb(Object thumb) {
        this.thumb = thumb;
    }

    public String getServings() {
        return servings;
    }

    public void setServings(String servings) {
        this.servings = servings;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public String getDificulty() {
        return dificulty;
    }

    public void setDificulty(String dificulty) {
        this.dificulty = dificulty;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<NeedItem> getNeedItem() {
        return needItem;
    }

    public void setNeedItem(List<NeedItem> needItem) {
        this.needItem = needItem;
    }

    public List<String> getIngredient() {
        return ingredient;
    }

    public void setIngredient(List<String> ingredient) {
        this.ingredient = ingredient;
    }

    public List<String> getStep() {
        return step;
    }

    public void setStep(List<String> step) {
        this.step = step;
    }


}