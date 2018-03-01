package com.example.looper.architecturedemo.network.api;

import com.example.looper.architecturedemo.CategoryResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * gank.io 接口
 *
 * @author bakumon
 * @date 16-12-1
 */

public interface GankApi {

    @GET("data/{category}/{number}/{page}")
    Call<CategoryResult> getCategoryDate(@Path("category") String category, @Path("number") int number, @Path("page") int page);

    @GET("random/data/福利/{number}")
    Call<CategoryResult> getRandomBeauties(@Path("number") int number);

}
