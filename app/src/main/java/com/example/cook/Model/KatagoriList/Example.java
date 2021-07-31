package com.example.cook.Model.KatagoriList;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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
    private List<Result> results = null;

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

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

}
