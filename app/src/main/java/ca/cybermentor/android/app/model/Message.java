package ca.cybermentor.android.app.model;

public class Message {

    public String body;
    private Type type;

    public Message(Type type, String body) {
        this.type = type;
        this.body = body;
    }

    public boolean isSent() {
        return (type.equals(Type.SENT));
    }

    public enum Type {SENT, RCVD}
}
