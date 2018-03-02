package com.example.looper.architecturedemo;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.looper.architecturedemo.databinding.ItemBinding;

import java.util.List;

/**
 * Created by Looper on 2018/3/2.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

  private List<ResultsBean> mList;

  @Override
  public CategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    ItemBinding binding = DataBindingUtil.inflate(((Activity) parent.getContext()).getLayoutInflater(), R.layout.item, parent, false);
    return new ViewHolder(binding);
  }

  @Override
  public void onBindViewHolder(CategoryAdapter.ViewHolder holder, int position) {
    holder.mBinding.setCategory(mList.get(position));
  }

  @Override
  public int getItemCount() {
    if (mList != null)
      return mList.size();
    return 0;
  }

  public void setData(List<ResultsBean> results) {
    mList = results;
    notifyDataSetChanged();
  }

  static class ViewHolder extends RecyclerView.ViewHolder {

    public ItemBinding mBinding;

    public ViewHolder(ItemBinding binding) {
      super(binding.getRoot());
      mBinding = binding;
    }
  }
}
