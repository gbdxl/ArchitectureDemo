package com.example.looper.architecturedemo.network;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.MainThread;
import android.support.annotation.Nullable;

public abstract class NetworkBoundResource<T> {
  private final MediatorLiveData<Resource<T>> result = new MediatorLiveData<>();

  @MainThread
  public NetworkBoundResource() {
    result.setValue(Resource.<T>loading(null));
    fetchFromNetwork();
  }

  private void fetchFromNetwork() {
    final LiveData<ApiResponse<T>> apiResponse = createCall();
    // we re-attach dbSource as a new source,
    // it will dispatch its latest value quickly
    result.addSource(apiResponse, new Observer<ApiResponse<T>>() {
      @Override
      public void onChanged(@Nullable ApiResponse<T> response) {
        if (response.isSuccessful()) {
          result.setValue(Resource.success(response.body));
        } else {
          result.setValue(Resource.<T>error(response.errorMessage));
        }
      }
    });
  }

  protected abstract LiveData<ApiResponse<T>> createCall();

  protected abstract void onFetchFailed();

  public final LiveData<Resource<T>> asLiveData() {
    return result;
  }
}