package com.hacknife.atlas.bean;

public class Theme {
    int resId;
    int isChecked;

    public Theme(int resId, int isChecked) {
        this.resId = resId;
        this.isChecked = isChecked;
    }

    public int getResId() {
        return resId;
    }


    public void setIsChecked(int isChecked) {
        this.isChecked = isChecked;
    }

    public int getIsChecked() {
        return isChecked;
    }
}
