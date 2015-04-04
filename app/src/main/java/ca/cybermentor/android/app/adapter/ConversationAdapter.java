package ca.cybermentor.android.app.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import ca.cybermentor.android.app.R;

public class ConversationAdapter extends ArrayAdapter<String> {

    private List<String> messagesList;
    private Context context;

    public ConversationAdapter(Context ctx, List<String> messagesList) {
        super(ctx, R.layout.conversation_area, messagesList);
        this.messagesList = messagesList;
        this.context = ctx;
    }

    public View getView(int position, View currentView, ViewGroup parent) {
        View conversationArea = currentView;
        String messageBody = "";

        // New view. Inflate layout & fill it.
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        conversationArea = inflater.inflate(R.layout.conversation_area, null);

        TextView messageBox = (TextView) conversationArea.findViewById(R.id.message);

        messageBody = messagesList.get(position);
        messageBox.setText(messageBody);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) messageBox.getLayoutParams();

        if (messageBody.contains("SENT")) {
            messageBox.setBackgroundResource(R.drawable.selector_sent);
            params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            params.setMargins(5, 5, 150, 5);
            messageBox.setGravity(Gravity.LEFT);
        } else {
            messageBox.setBackgroundResource(R.drawable.selector_rcvd);
            params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            params.setMargins(150, 5, 5, 5);
            messageBox.setGravity(Gravity.RIGHT);
        }
        return conversationArea;
    }

    // TODO: Use holder pattern to avoid slower, multiple getViewById calls.
    private static class ViewHolder {
        public TextView heldView;
    }
}