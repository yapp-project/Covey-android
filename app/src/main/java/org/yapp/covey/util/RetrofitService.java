package org.yapp.covey.util;

import org.yapp.covey.etc.ItemPostVO;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitService {
    @GET("api/post/payList/{page}")
    Call<ArrayList<ItemPostVO>>
    payList(@Path("page") Integer page);

    @GET("api/post/addressList/{page}")
    Call<ArrayList<ItemPostVO>>
    addressList(@Path("page") Integer page);
}
