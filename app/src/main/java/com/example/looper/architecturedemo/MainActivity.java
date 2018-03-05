package com.example.looper.architecturedemo;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.looper.architecturedemo.databinding.MainActivityBinding;
import com.example.looper.architecturedemo.network.Resource;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {


  private MainActivityBinding mBinding;
  private int page = 0;
  private CategoryVM mCategoryVM;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mBinding = DataBindingUtil.setContentView(this, R.layout.main_activity);
//    mBinding = MainActivityBinding.inflate(getLayoutInflater());
    mBinding.swipeLayout.setOnRefreshListener(this);
    mCategoryVM = ViewModelProviders.of(this).get(CategoryVM.class);
    final CategoryAdapter categoryAdapter = new CategoryAdapter();
    Observer<Resource<CategoryResult>> observer = new Observer<Resource<CategoryResult>>() {
      @Override
      public void onChanged(@Nullable Resource<CategoryResult> categoryResult) {
        if (categoryResult.isLoading()) {
          mBinding.swipeLayout.setRefreshing(true);
        } else if (categoryResult.isSuccess()) {
          categoryAdapter.setNewData(categoryResult.data.results);
          mBinding.swipeLayout.setRefreshing(false);
        }
      }
    };
    mCategoryVM.liveData.observe(this, observer);
    mCategoryVM.getData();
    categoryAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
      @Override
      public void onLoadMoreRequested() {
        mCategoryVM.getMoreData(++page);
      }
    }, mBinding.recyclerView);
    mBinding.recyclerView.setAdapter(categoryAdapter);
    mCategoryVM.moreData.observe(this, new Observer<Resource<CategoryResult>>() {
      @Override
      public void onChanged(@Nullable Resource<CategoryResult> resource) {
        if (resource.isSuccess()) {
          categoryAdapter.loadMoreComplete();
          categoryAdapter.addData(resource.data.results);
        }
      }
    });
  }

  @Override
  public void onRefresh() {
    mCategoryVM.getData();
  }
}
