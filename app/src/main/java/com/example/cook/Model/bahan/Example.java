package com.example.cook.Model.bahan;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@Generated("jsonschema2pojo")
public class Example {

    @SerializedName("method")
    @Expose
    private String method;
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("results")
    @Expose
    private Results results;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Results getResults() {
        return results;
    }

    public void setResults(Results results) {
        this.results = results;
    }

}