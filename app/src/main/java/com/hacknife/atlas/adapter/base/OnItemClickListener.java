package com.hacknife.atlas.adapter.base;

public interface OnItemClickListener<E> extends OnRecyclerViewListener<E> {
    void onItemClick(E t);
}
