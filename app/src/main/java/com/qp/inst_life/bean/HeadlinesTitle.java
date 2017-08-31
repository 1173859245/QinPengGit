package com.qp.inst_life.bean;

/**
 * Created by Administrator on 2017/6/21 0021.
 */

public class HeadlinesTitle {
    private String text;
    private String type;

    @Override
    public String toString() {
        return "HeadlinesTitle{" +
                "text='" + text + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    public HeadlinesTitle(String text, String type) {
        this.text = text;
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
