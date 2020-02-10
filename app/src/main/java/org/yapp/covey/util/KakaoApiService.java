package org.yapp.covey.util;

import org.yapp.covey.model.KaKaoMapSearchModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface KakaoApiService {
    @GET("/v2/local/search/keyword.{format}")
    Call<KaKaoMapSearchModel>
    categoryList(@Header("KakaoAK") String appKey,@Path("format") Integer page, @Query("query") String searchData);
}
