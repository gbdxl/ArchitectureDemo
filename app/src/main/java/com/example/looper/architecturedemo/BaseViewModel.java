package com.example.looper.architecturedemo;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

/**
 * Created by looper on 2018/3/4.
 */

public class BaseViewModel extends ViewModel {

  public MutableLiveData<Boolean> isRefresh = new MutableLiveData<>();
  public MutableLiveData<Boolean> isLoadMore = new MutableLiveData<>();
  public MutableLiveData<Boolean> isRefreshError = new MutableLiveData<>();
  public MutableLiveData<Boolean> isLoadMoreError = new MutableLiveData<>();
  public MutableLiveData<String> error = new MutableLiveData<>();
}
