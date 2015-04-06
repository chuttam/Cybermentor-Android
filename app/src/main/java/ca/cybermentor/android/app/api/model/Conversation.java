package ca.cybermentor.android.app.api.model;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

import ca.cybermentor.android.app.model.Message;
import ca.cybermentor.android.app.model.Message.Type;

public class Conversation {
    public ArrayList<Message> setupInitialConversation(String in) {
        ArrayList<Message> out = new ArrayList<>();
        Document doc = Jsoup.parse(in);
        Elements allMessages = doc.select(".bubble");
        String body;

        for (Element rawMessage : allMessages) {
            body = rawMessage.select(".message-content").first().text();
            if (rawMessage.hasClass("sent-message"))
                out.add(new Message(Type.SENT, body));
            else
                out.add(new Message(Type.RCVD, body));
        }
        return out;
    }
}