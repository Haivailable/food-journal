package com.example.foodjournalproject;

public class FoodJournalNotes {
    private String date;
    private String bfText;
    private String lText;
    private String dText;
    private String nText;

    public FoodJournalNotes(String date, String bfText, String lText, String dText, String nText) {
        this.date = date;
        this.bfText = bfText;
        this.lText = lText;
        this.dText = dText;
        this.nText = nText;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBfText() {
        return bfText;
    }

    public void setBfText(String bfText) {
        this.bfText = bfText;
    }

    public String getlText() {
        return lText;
    }

    public void setlText(String lText) {
        this.lText = lText;
    }

    public String getdText() {
        return dText;
    }

    public void setdText(String dText) {
        this.dText = dText;
    }

    public String getnText() {
        return nText;
    }

    public void setnText(String nText) {
        this.nText = nText;
    }
}
