package ca.cybermentor.android.app.api;


import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.Query;

public interface Cybermentor {
    @GET("/get_messages")
    Response messageHistory(
            @Query("uid1") String uid1,
            @Query("uid2") String uid2
    );
}
