package com.hacknife.atlas.adapter.base;

import android.view.View;

public interface OnItemLongClickListener2<E> extends OnRecyclerViewListener<E> {
    boolean onItemLongClick(E t, int last, int current, View view);
}
