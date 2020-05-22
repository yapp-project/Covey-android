package org.yapp.covey.util;

import com.google.gson.JsonObject;

import org.yapp.covey.etc.careerClass;
import org.yapp.covey.etc.phoneNumClass;
import org.yapp.covey.etc.profileEditClass;
import org.yapp.covey.etc.userClass;
import org.yapp.covey.etc.userResponseClass;
import org.yapp.covey.model.ItemDataModel;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
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

    @GET("api/post/list/{page}")
    Call<ArrayList<ItemDataModel>>
    filterList(@Path("page") Integer page
            , @Query("pay") Integer pay
            ,@Query("category") String category
            ,@Query("address1") String address1
            ,@Query("address2") String address2
            ,@Query("startDate") String startDate
            ,@Query("endDate") String endDate);

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
    Call<Void>
    editUser(@Body profileEditClass body);

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

    //upload
    @Multipart
    @POST("api/post")
    Call<ResponseBody>
    upload(@Part("title") RequestBody title
            , @Part("startDate") RequestBody startDate
            , @Part("endDate") RequestBody endDate
            , @Part("dueDate") RequestBody dueDate
            , @Part("isDue") Boolean isDue
            , @Part("workingTime") RequestBody workingTime
            , @Part("address1") RequestBody address1
            , @Part("address2") RequestBody address2
            , @Part("address3") RequestBody address3
            , @Part("pay") Integer pay
            , @Part("description") RequestBody description
            , @Part("category") RequestBody category
            , @Part MultipartBody.Part img1
            , @Part MultipartBody.Part img2
            , @Part MultipartBody.Part img3
    );
}
