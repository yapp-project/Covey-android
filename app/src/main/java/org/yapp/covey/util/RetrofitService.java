package org.yapp.covey.util;

import com.google.gson.JsonObject;

import org.yapp.covey.etc.ItemPostVO;
import org.yapp.covey.etc.phoneNumClass;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitService {

    // post
    @GET("api/post/categoryList/{page}")
    Call<ArrayList<ItemPostVO>>
    categoryList(@Path("page") Integer page, @Query("category") String category);

    @GET("api/post/payList/{page}")
    Call<ArrayList<ItemPostVO>>
    payList(@Path("page") Integer page);

    @GET("api/post/addressList/{page}")
    Call<ArrayList<ItemPostVO>>
    addressList(@Path("page") Integer page);

    @GET("api/post/{postId}")
    Call<ItemPostVO>
    postDetail(@Path("postId") Integer postId);

    @GET("api/post/registerList")
    Call<ArrayList<ItemPostVO>>
    registerList();

    // auth
    @POST("api/auth/phone")
    Call<phoneNumClass>
    phoneVerify(@Body phoneNumClass body);

    @POST("api/auth/verify")
    Call<phoneNumClass>
    phoneVerifyCheck(@Body phoneNumClass body);
}
