package com.example.looper.architecturedemo;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.looper.architecturedemo.databinding.MainActivityBinding;

public class MainActivity extends AppCompatActivity {


  private MainActivityBinding mBinding;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mBinding = DataBindingUtil.setContentView(this, R.layout.main_activity);
    CategoryVM categoryVM = ViewModelProviders.of(this).get(CategoryVM.class);
    categoryVM.getData();
    final CategoryAdapter categoryAdapter = new CategoryAdapter();
    Observer<CategoryResult> observer = new Observer<CategoryResult>() {
      @Override
      public void onChanged(@Nullable CategoryResult categoryResult) {
        Log.i("=========", categoryResult.toString());
        categoryAdapter.setData(categoryResult.results);
      }
    };
    categoryVM.getLiveData().observe(this, observer);
    mBinding.recyclerView.setAdapter(categoryAdapter);
  }
}
