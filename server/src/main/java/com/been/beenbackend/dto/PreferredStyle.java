package com.been.beenbackend.dto;

public class PreferredStyle {
    private int userId;
    private String Style;

    public String getStyle() {
        return Style;
    }

    public void setStyle(String style) {
        Style = style;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
