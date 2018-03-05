package com.example.looper.architecturedemo.network;

import com.example.looper.architecturedemo.network.api.GankApi;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 网络层
 *
 * @author bakumon
 * @date 16-12-1
 */

public class NetWork {
  private static GankApi gankApi;
  private static OkHttpClient okHttpClient;
  private static Converter.Factory gsonConverterFactory = GsonConverterFactory.create();

  public static GankApi getGankApi() {
    if (okHttpClient == null) {
      okHttpClient = new OkHttpClient.Builder()
          .addNetworkInterceptor(new StethoInterceptor())
          .build();
    }
    if (gankApi == null) {
      Retrofit retrofit = new Retrofit.Builder()
          .client(okHttpClient)
          .baseUrl("http://gank.io/api/")
          .addConverterFactory(gsonConverterFactory)
          .addCallAdapterFactory(new LiveDataCallAdapterFactory())
          .build();
      gankApi = retrofit.create(GankApi.class);
    }
    return gankApi;
  }

}
