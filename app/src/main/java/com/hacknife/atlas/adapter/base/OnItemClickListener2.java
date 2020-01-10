package com.hacknife.atlas.adapter.base;

import android.view.View;

public interface OnItemClickListener2<E> extends OnRecyclerViewListener<E> {
    boolean onItemClick(E t, int last, int current, View view);
}
