package ca.cybermentor.android.app.api;

import java.io.IOException;
import java.io.InputStream;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class CybermentorApi {

    public final Cybermentor service;
    public String body;

    public CybermentorApi() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://cybermentor.ca")
//                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        service = restAdapter.create(Cybermentor.class);
    }
}