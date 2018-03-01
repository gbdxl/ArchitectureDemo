package com.example.looper.architecturedemo;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    DataBindingUtil.setContentView(this, R.layout.activity_main);
    CategoryVM categoryVM = ViewModelProviders.of(this).get(CategoryVM.class);
    categoryVM.getData();
    Observer<CategoryResult> observer = new Observer<CategoryResult>() {
      @Override
      public void onChanged(@Nullable CategoryResult categoryResult) {
        Log.i("=========", categoryResult.toString());
      }
    };
    categoryVM.getLiveData().observe(this, observer);
  }
}
