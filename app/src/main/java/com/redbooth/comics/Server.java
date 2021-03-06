package com.redbooth.comics;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface Server {

    /**
     * Get all the comics from a specific character.
     */
    @GET("characters/{characterId}/comics")
    Call<Marvel> amazingspiderman(@Path("characterId") int characterId, @QueryMap Map<String, String> digest);
}
