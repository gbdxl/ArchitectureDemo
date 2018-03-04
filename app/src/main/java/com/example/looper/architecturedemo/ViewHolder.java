package com.example.looper.architecturedemo;

import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;
import com.example.looper.architecturedemo.databinding.ItemBinding;

public class ViewHolder extends BaseViewHolder {

    public ItemBinding mBinding;

    public ViewHolder(View view) {
      super(view);
      mBinding = (ItemBinding) view.getTag(R.id.BaseQuickAdapter_databinding_support);
    }
  }