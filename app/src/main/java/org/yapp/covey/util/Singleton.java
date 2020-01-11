package org.yapp.covey.util;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Singleton {
        public static final RetrofitService retrofit = new Retrofit.Builder()
                .baseUrl(Config.serverUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(RetrofitService.class);
        private Singleton() {
        }
}
