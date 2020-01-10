package com.hacknife.atlas.adapter;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class RecycleGridDivider extends RecyclerView.ItemDecoration {
    public static final int space = 20;
    public static final int span = 2;

    @Override
    public void getItemOffsets(Rect outRect, View view,
                               RecyclerView parent, RecyclerView.State state) {
        outRect.set(space,space,space,space);
    }
}
