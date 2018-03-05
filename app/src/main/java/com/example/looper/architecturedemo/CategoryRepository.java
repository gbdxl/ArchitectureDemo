package com.example.looper.architecturedemo;

import android.arch.lifecycle.LiveData;

import com.example.looper.architecturedemo.network.ApiResponse;
import com.example.looper.architecturedemo.network.NetWork;
import com.example.looper.architecturedemo.network.NetworkBoundResource;
import com.example.looper.architecturedemo.network.Resource;

/**
 * Created by looper on 2018/3/4.
 */

public class CategoryRepository {

  public LiveData<Resource<CategoryResult>> getData() {
    return new NetworkBoundResource<CategoryResult>() {
      @Override
      protected LiveData<ApiResponse<CategoryResult>> createCall() {
        return NetWork.getGankApi().getCategoryDate("Android", 10, 0);
      }

      @Override
      protected void onFetchFailed() {

      }
    }.asLiveData();
  }

  public LiveData<Resource<CategoryResult>> getMoreData(final int page) {
    return new NetworkBoundResource<CategoryResult>() {
      @Override
      protected LiveData<ApiResponse<CategoryResult>> createCall() {
        return NetWork.getGankApi().getCategoryDate("Android", 10, page);
      }

      @Override
      protected void onFetchFailed() {

      }
    }.asLiveData();
  }
}
