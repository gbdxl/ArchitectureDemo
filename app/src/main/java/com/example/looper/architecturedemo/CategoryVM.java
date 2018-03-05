package com.example.looper.architecturedemo;

import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;

import com.example.looper.architecturedemo.network.Resource;

/**
 * Created by looper on 2018/3/1.
 */

public class CategoryVM extends ViewModel {

  public final MediatorLiveData<Resource<CategoryResult>> liveData;
  private final CategoryRepository mRepository;
  public final MediatorLiveData<Resource<CategoryResult>> moreData;

  CategoryVM() {
    mRepository = new CategoryRepository();
    liveData = new MediatorLiveData<>();
    moreData = new MediatorLiveData<>();
  }

  public void getData() {
    liveData.addSource(mRepository.getData(), new Observer<Resource<CategoryResult>>() {
      @Override
      public void onChanged(@Nullable Resource<CategoryResult> resource) {
        liveData.setValue(resource);
      }
    });
  }

  public void getMoreData(final int page) {
    moreData.addSource(mRepository.getMoreData(page), new Observer<Resource<CategoryResult>>() {
      @Override
      public void onChanged(@Nullable Resource<CategoryResult> categoryResultResource) {
        moreData.setValue(categoryResultResource);
      }
    });
  }
}
