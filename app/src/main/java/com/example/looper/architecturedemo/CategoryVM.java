package com.example.looper.architecturedemo;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.example.looper.architecturedemo.network.NetWork;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by looper on 2018/3/1.
 */

public class CategoryVM extends BaseViewModel {

  private final MutableLiveData<CategoryResult> liveData;

  CategoryVM(){
    liveData = new MutableLiveData<>();
  }

  public LiveData<CategoryResult> getLiveData() {
    return liveData;
  }

  public void getData(final int page){
    NetWork.getGankApi().getCategoryDate("Android",10,page).enqueue(new Callback<CategoryResult>() {
      @Override
      public void onResponse(Call<CategoryResult> call, Response<CategoryResult> response) {
        Log.i("============", response.toString());
        if (page==0){
          isRefresh.setValue(false);
        }else{
          isLoadMore.setValue(false);
        }
        liveData.setValue(response.body());
      }

      @Override
      public void onFailure(Call<CategoryResult> call, Throwable t) {
        if (page==0){
          isRefreshError.setValue(false);
        }else{
          isLoadMoreError.setValue(false);
        }
      }
    });
  }

}
