package ca.cybermentor.android.app.api;

import retrofit.RestAdapter;

public class CybermentorApi {

    public final Cybermentor cybermentor;

    public CybermentorApi() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://cybermentor.ca")
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        cybermentor = restAdapter.create(Cybermentor.class);
    }
}