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

        for (Element message : allMessages) {
            out.add(message.select(".message-content").first().text());
        }
        return out;
    }
}