package org.yapp.covey.util;

import org.yapp.covey.model.KaKaoMapSearchModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface KakaoApiService {
    @Headers("Authorization: KakaoAK 44b3f6aa35e897afcd760f6400a1892c")
    @GET("/v2/local/search/keyword.json")
    Call<KaKaoMapSearchModel>
    categoryList(@Query("page") int page, @Query("query") String searchData);
}
