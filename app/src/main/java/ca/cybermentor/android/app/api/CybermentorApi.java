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

    public static String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

}