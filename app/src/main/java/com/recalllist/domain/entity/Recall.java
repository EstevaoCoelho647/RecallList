package com.recalllist.domain.entity;

/**
 * Created by estevao on 18/09/17.
 */

public class Recall {
    private String text;
    private String date;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
