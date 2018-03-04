package com.example.looper.architecturedemo.network;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.MainThread;
import android.support.annotation.Nullable;

public abstract class NetworkBoundResource<ResultType, RequestType> {
  private final MediatorLiveData<Resource<ResultType>> result = new MediatorLiveData<>();

  @MainThread
  NetworkBoundResource() {
    result.setValue(Resource.loading((ResultType) null));
    fetchFromNetwork();
  }

  private void fetchFromNetwork() {
    final LiveData<ApiResponse<RequestType>> apiResponse = createCall();
    // we re-attach dbSource as a new source,
    // it will dispatch its latest value quickly
    result.addSource(apiResponse, new Observer<ApiResponse<RequestType>>() {
      @Override
      public void onChanged(@Nullable ApiResponse<RequestType> response) {
        result.removeSource(apiResponse);
        //noinspection ConstantConditions
        if (response.isSuccessful()) {
          result.setValue(Resource.success((ResultType) response.body));
        } else {
          onFetchFailed();
//          result.setValue(Resource.error(response.errorMessage));
        }
      }
    });
  }

  protected abstract LiveData<ApiResponse<RequestType>> createCall();

  protected abstract void onFetchFailed();

  public final LiveData<Resource<ResultType>> asLiveData() {
    return result;
  }
}