package com.redbooth.comics.domain;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by david on 13/11/16.
 */

public class RetrofitHelper {

    private static Retrofit.Builder INSTANCE;
    private final static String API_URL = "http://gateway.marvel.com/v1/public/";

    public RetrofitHelper() {
        INSTANCE = new Retrofit.Builder().baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create());
    }

    public static Retrofit.Builder getInstance(){
        return INSTANCE;
    }
}
