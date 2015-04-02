package ca.cybermentor.android.app.api.model;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class Conversation {
    public ArrayList<String> setupInitialConversation(String in){
        ArrayList<String> out = new ArrayList<String>();
        Document doc = Jsoup.parse(in);
        Elements allMessages = doc.select(".bubble");
        String body;

        for (Element message : allMessages) {
            body = message.select(".message-content").first().text();
            if (message.hasClass("sent-message"))
                out.add("SENT: " +  body);
            else
                out.add("RCVD: " +  body);
        }
        return out;
    }
}