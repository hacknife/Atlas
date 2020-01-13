package com.hacknife.atlas.adapter.base;


import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Map;


public abstract class BaseRecyclerViewHolder<E, B extends ViewDataBinding> extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener  {
    protected B binding;
    protected OnRecyclerListener<E> listener;
    protected int position;
    protected E entity;
    protected int size;


    public BaseRecyclerViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
        binding = DataBindingUtil.bind(itemView);
    }

    public void bindData(E e, int position, int size, OnRecyclerListener<E> listener ) {
        this.listener = listener;
        this.position = position;
        this.size = size;


        if (e == null) return;
        this.entity = e;
        bindData(e);
        if (listener != null) listener.callback(callback(e));

    }

    protected int callback(E e) {
        return -1;
    }

    public abstract void bindData(E t);

    @Override
    public void onClick(View v) {
        if (listener != null) listener.onClick(entity, position, v);
    }

    @Override
    public boolean onLongClick(View v) {
        if (listener != null) return listener.onLongClick(entity, position, v);
        return false;
    }

}
