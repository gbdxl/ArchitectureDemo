package com.example.looper.architecturedemo.network;

import com.example.looper.architecturedemo.network.api.GankApi;

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
    private static OkHttpClient okHttpClient =  new OkHttpClient.Builder().build();
    private static Converter.Factory gsonConverterFactory = GsonConverterFactory.create();

    public static GankApi getGankApi() {
        if (gankApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl("http://gank.io/api/")
                    .addConverterFactory(gsonConverterFactory)
                    .build();
            gankApi = retrofit.create(GankApi.class);
        }
        return gankApi;
    }

}
