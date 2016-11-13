package com.redbooth.comics.domain;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by david on 13/11/16.
 */

public class RetrofitHelper {
    private final static String API_URL = "http://gateway.marvel.com/v1/public/";

    private Retrofit.Builder INSTANCE = new Retrofit.Builder().baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create());

    public Retrofit.Builder getInstance(){
        return this.INSTANCE;
    }
}
