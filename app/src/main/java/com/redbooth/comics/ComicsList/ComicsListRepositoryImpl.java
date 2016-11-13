package com.redbooth.comics.ComicsList;

import com.redbooth.comics.api_interfaces.character_api;
import com.redbooth.comics.domain.RetrofitHelper;
import com.redbooth.comics.entities.Marvel;
import com.redbooth.comics.lib.EventBus;
import com.redbooth.comics.lib.GreenRobotEventBus;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by david on 13/11/16.
 */

public class ComicsListRepositoryImpl implements ComicsListRepository {

    private Map<String, String> keysMap;
    private static Retrofit.Builder builder;
    private Retrofit retrofit;
    private RetrofitHelper helper;
    private character_api character_api;
    private Call<Marvel> character_call;
    private EventBus eventBus;

    public ComicsListRepositoryImpl() {
        this.eventBus = GreenRobotEventBus.getInstance();
        helper = new RetrofitHelper();
        builder = helper.getInstance();
    }

    @Override
    public void createKeysMap() {
        String timestamp = "ts"; // replace here with correct values
        String privateKey = "private_key"; // replace here with correct values
        String publicKey = "public_key"; // replace here with correct values
        String hash = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            hash = new BigInteger(1,md.digest(String.format("%s%s%s", timestamp, privateKey, publicKey).getBytes())).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        // Create hashmap
        keysMap = new HashMap<>();
        keysMap.put("ts", timestamp);
        keysMap.put("apikey", publicKey);
        keysMap.put("hash", hash);
    }

    @Override
    public void marvelUpdating() {

        retrofit = builder.build();

        // create s
        character_api = retrofit.create(character_api.class);

        // retrieve amazingcomics
        character_call = character_api.amazingspiderman(1010733, keysMap);

        // enqueue amazingcomics call
        character_call.enqueue(new Callback<Marvel>() {
            @Override
            public void onResponse(Call<Marvel> c, Response<Marvel> r) {
                // Everything is ok
                if (r.code() == 200) {

                    ComicsEvent event = new ComicsEvent();

                    event.setComics(r.body().data.results);

                    eventBus.post(event);
                }

            }

            @Override
            public void onFailure(Call<Marvel> call, Throwable t) {
                //TODO do something here
            }
        });
    }
}
