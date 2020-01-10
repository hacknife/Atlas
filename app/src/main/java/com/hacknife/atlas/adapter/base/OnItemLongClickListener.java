package com.hacknife.atlas.adapter.base;


public interface OnItemLongClickListener<E> extends OnRecyclerViewListener<E> {
    void onItemLongClick(E t);
}
