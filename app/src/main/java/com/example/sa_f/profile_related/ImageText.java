package com.example.sa_f.profile_related;

import android.graphics.drawable.Drawable;

public class ImageText {
    String name;
    Drawable icon;

    public ImageText(String name, Drawable icon) {
        this.name = name;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }
}
