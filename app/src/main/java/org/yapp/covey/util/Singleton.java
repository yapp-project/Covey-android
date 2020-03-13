package org.yapp.covey.util;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Singleton {
        public static final CoveyApiService retrofit = new Retrofit.Builder()
                .baseUrl(Config.serverUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(CoveyApiService.class);

        public static final KakaoApiService KakaoMaoApi = new Retrofit.Builder()
                .baseUrl("https://dApi.kakao.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(KakaoApiService.class);
}