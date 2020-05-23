package com.cheng.channel.adapter;

import com.cheng.channel.ViewHolder;

public abstract class BaseStyleAdapter<VH extends ViewHolder> implements StyleAdapter<VH> {
    @Override
    public void setNormalStyle(VH viewHolder,int position) {

    }

    @Override
    public void setFixedStyle(VH viewHolder) {

    }

    @Override
    public void setEditStyle(VH viewHolder,int position) {

    }

    @Override
    public void setFocusedStyle(VH viewHolder) {

    }
}
