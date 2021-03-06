package com.redbooth.comics;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// Class created by someone learning chinese? I'm not sure
// Updated by Unknown
//
// WARNING!!! Be careful about building the Retrofit Object. It requires to be executed in the same order it has been developed. Otherwise, weird things can happen or the app can crash

/**
 * 耶穌巴列斯特羅
 *
 * 這是應用程序的主要活動。它顯示在主屏幕和AppCompatActivity繼承
 */
public class MainActivity extends AppCompatActivity
{
    // Main Activity
    // Principal Activity of this app

    // Private fields
    private RecyclerView mList;
    private Map<String, String> mMap;
    private Retrofit.Builder b;

    // Public fields
    public Retrofit r;
    public Server s;

    // Nothing fields
    private Call<Marvel> c;

    /**
     * 在拉曼恰，名字我不記得了，時間不長，因為住在離那些槍和盾古代，精益黑客和竊喜靈獅的貴族村
     *
     * @param savedInstanceState 堂吉訶德
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // on create

        // call super always
        super.onCreate(savedInstanceState);

        // setting the view
        setContentView(com.redbooth.comics.R.layout.activity_main);

        // set recyclerview
        mList = (RecyclerView) findViewById(com.redbooth.comics.R.id.comic_list);

        // Create comic adapter
        final ComicAdapter a = new ComicAdapter();


        // set adapter
        mList.setAdapter(a);



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
        mMap = new HashMap<>();
        mMap.put("ts", timestamp);
        mMap.put("apikey", publicKey);
        mMap.put("hash", hash);


        // update marvel


        // Create builder
        b = new Retrofit.Builder().baseUrl("http://gateway.marvel.com/v1/public/").addConverterFactory(GsonConverterFactory.create());

        // call marvel updating. Don't forget to call it
        marvel_updating(a, mMap, b);
    }

    /**
     * Method marvel_updating
     * Class MainActivity
     *
     * author Unknown
     * modified by Unknown
     *
     * This method receives a ComicAdapter, a Map and a Builder. Returns nothing.
     * This method updates marvel
     * This method generates a retrofit object and calls amazingcomics. It then calls
     * enqueue. It then notifies data set changed
     *
     * @param a ComicAdapter a
     * @param m Map mMap
     * @param b Builder b
     */
    private void marvel_updating(final ComicAdapter a, Map<String, String> m, Retrofit.Builder b)
    {
        // update

        // build r
        r = b.build();

        // create s
        s = r.create(Server.class);

        // retrieve amazingcomics
        c = s.amazingspiderman(1010733, m);

        // enqueue amazingcomics call
        c.enqueue(new Callback<Marvel>() {
            @Override
            public void onResponse(Call<Marvel> c, Response<Marvel> r) {
                // Everything is ok
                if (r.code() == 200) {

                    // set
                    a.setC(r.body().data.results);

                    // notify data set changed
                    a.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<Marvel> call, Throwable t) {
                //TODO do something here
            }
        });


    }

}
