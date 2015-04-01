package ca.cybermentor.android.app.event;

import retrofit.client.Response;

public class LoadConversationEvent {
    public final String body;
    public final Response response;

    public LoadConversationEvent(String body, Response response) {
        this.body = body;
        this.response = response;
    }
}
