package com.example.looper.architecturedemo;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.looper.architecturedemo.databinding.ItemBinding;

/**
 * Created by Looper on 2018/3/2.
 */

public class CategoryAdapter extends BaseQuickAdapter<ResultsBean, ViewHolder> {

  public CategoryAdapter() {
    super(R.layout.item);
  }

  @Override
  protected void convert(ViewHolder helper, ResultsBean item) {
    helper.mBinding.setCategory(item);
  }

  @Override
  protected View getItemView(int layoutResId, ViewGroup parent) {
    ItemBinding binding = DataBindingUtil.inflate(((Activity) parent.getContext()).getLayoutInflater(), layoutResId, parent, false);
    if (binding == null) {
      return super.getItemView(layoutResId, parent);
    }
    binding.getRoot().setTag(R.id.BaseQuickAdapter_databinding_support, binding);
    return binding.getRoot();
  }


}
