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
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        service = restAdapter.create(Cybermentor.class);
    }

    public String getMessageHistory(String uid1, String uid2) {

        Callback callback =  new Callback<Response>() {
            @Override
            public void success(Response result, Response response) {
                try {
                    body = convertStreamToString(response.getBody().in());
                } catch (IOException e) {}
            }

            @Override
            public void failure(RetrofitError error) {}
        };

        service.messageHistory(uid1, uid2, callback);
        return body;
    }

    public String convertStreamToString(InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}