package com.example.looper.architecturedemo;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.looper.architecturedemo.databinding.MainActivityBinding;

import java.util.Collections;

public class MainActivity extends AppCompatActivity {


  private MainActivityBinding mBinding;
  private int page = 0;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mBinding = DataBindingUtil.setContentView(this, R.layout.main_activity);
    final CategoryVM categoryVM = ViewModelProviders.of(this).get(CategoryVM.class);
    categoryVM.getData(page);
    final CategoryAdapter categoryAdapter = new CategoryAdapter(Collections.<ResultsBean>emptyList());
    Observer<CategoryResult> observer = new Observer<CategoryResult>() {
      @Override
      public void onChanged(@Nullable CategoryResult categoryResult) {
        Log.i("=========", categoryResult.toString());
        if (page == 0) {
          categoryAdapter.setNewData(categoryResult.results);
        } else {
          categoryAdapter.addData(categoryResult.results);
        }
      }
    };
    categoryVM.getLiveData().observe(this, observer);
    categoryAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
      @Override
      public void onLoadMoreRequested() {
        categoryVM.getData(page++);
      }
    }, mBinding.recyclerView);
    mBinding.recyclerView.setAdapter(categoryAdapter);
    Observer<Boolean> isLoadmore = new Observer<Boolean>() {
      @Override
      public void onChanged(@Nullable Boolean aBoolean) {
        if (aBoolean != null && !aBoolean) {
          categoryAdapter.loadMoreComplete();
        }
      }
    };
    categoryVM.isLoadMore.observe(this, isLoadmore);
  }
}
