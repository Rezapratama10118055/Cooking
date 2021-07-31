package com.example.cook.CatatanResep;

public class data_resep {

    //Deklarasi Variable
    private String judulresep;
    private String bahanbahannya;
    private String langkahlangkah;
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getJudulresep() {
        return judulresep;
    }

    public void setJudulresep(String judulresep) {
        this.judulresep = judulresep;
    }

    public String getBahanbahannya() {
        return bahanbahannya;
    }

    public void setBahanbahannya(String bahanbahannya) {
        this.bahanbahannya = bahanbahannya;
    }

    public String getLangkahlangkah() {
        return langkahlangkah;
    }

    public void setLangkahlangkah(String langkahlangkah) {
        this.langkahlangkah = langkahlangkah;
    }

    //Membuat Konstuktor kosong untuk membaca data snapshot
    public data_resep(){
    }

    //Konstruktor dengan beberapa parameter, untuk mendapatkan Input Data dari User
    public data_resep(String judulresep, String bahanbahannya, String langkahlangkah) {
        this.judulresep = judulresep;
        this.bahanbahannya = bahanbahannya;
        this.langkahlangkah = langkahlangkah;
    }
}