package ca.cybermentor.android.app.api.service;

import com.squareup.otto.Bus;

import java.io.IOException;
import java.io.InputStream;

import ca.cybermentor.android.app.api.CybermentorApi;
import ca.cybermentor.android.app.event.LoadConversationEvent;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ConversationService {

    final CybermentorApi cybermentorApi;
    final Bus eventBus;

    public ConversationService(CybermentorApi cybermentorApi, Bus eventBus) {
        this.cybermentorApi = cybermentorApi;
        this.eventBus = eventBus;
    }

    public void getMessageHistory(String uid1, String uid2) {

        Callback callback =  new Callback<Response>() {
            @Override
            public void success(Response result, Response response) {
                try {
                    eventBus.post(new LoadConversationEvent(convertStreamToString(response.getBody().in()), response));
                } catch (IOException e) {}
            }

            @Override
            public void failure(RetrofitError error) {}
        };

        cybermentorApi.service.messageHistory(uid1, uid2, callback);
    }

    public String convertStreamToString(InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}