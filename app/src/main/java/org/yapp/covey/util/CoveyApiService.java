package org.yapp.covey.util;

import org.yapp.covey.etc.careerClass;
import org.yapp.covey.etc.phoneNumClass;
import org.yapp.covey.etc.userClass;
import org.yapp.covey.etc.userResponseClass;
import org.yapp.covey.model.ItemDataModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CoveyApiService {
    // post
    @GET("api/post/categoryList/{page}")
    Call<ArrayList<ItemDataModel>>
    categoryList(@Path("page") Integer page, @Query("category") String category);

    @GET("api/post/payList/{page}")
    Call<ArrayList<ItemDataModel>>
    payList(@Path("page") Integer page);

    @GET("api/post/addressList/{page}")
    Call<ArrayList<ItemDataModel>>
    addressList(@Path("page") Integer page);

    @GET("api/post/{postId}")
    Call<ItemDataModel>
    postDetail(@Path("postId") String postId);

    @GET("api/post/registerList")
    Call<ArrayList<ItemDataModel>>
    registerList();

    @GET("api/auth/kakao")
    Call<Void>
    kakaoLogin();

    @GET("api/auth/facebook")
    Call<Void>
    facebookLogin();

    @PUT("api/user")
    Call<userClass>
    userInfo(@Body userClass body);

    // auth
    @POST("api/auth/phone")
    Call<phoneNumClass>
    phoneVerify(@Body phoneNumClass body);

    @POST("api/auth/verify")
    Call<phoneNumClass>
    phoneVerifyCheck(@Body phoneNumClass body);

    @GET("api/apply/appliedList")
    Call<ArrayList<ItemDataModel>>
    applyList();

    @GET("api/user")
    Call<userResponseClass>
    getUser();

    @PUT("api/user")
    Call<userResponseClass>
    editUser(@Body userResponseClass body);

    @GET("api/career/list")
    Call<ArrayList<careerClass>>
    getCareer();

    @POST("api/career")
    Call<Void>
    addCareer(@Body careerClass body);

    @GET("api/career/{careerId}")
    Call<careerClass>
    getCareerDetail(@Path("careerId") String careerId);

    @PUT("api/career/{careerId}")
    Call<Void>
    editCareer(@Path("careerId") String careerId, @Body careerClass body);

    @DELETE("api/career/{careerId}")
    Call<Void>
    deleteCareer(@Path("careerId") String careerId);

    // apply
//    @POST("api/apply/{postId}")
//    Call<JSONArray>
//    apply(@Path("postId") Integer postId);
}
