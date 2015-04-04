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
import ca.cybermentor.android.app.model.Message;

public class ConversationAdapter extends ArrayAdapter<Message> {

    public ConversationAdapter(Context ctx, List<Message> messagesList) {
        super(ctx, R.layout.conversation_area, messagesList);
    }

    public View getView(int position, View currentView, ViewGroup parent) {
        View conversationArea = currentView;

        // New view. Inflate layout & fill it.
        conversationArea = LayoutInflater.from(this.getContext()).inflate(R.layout.conversation_area, parent, false);

        TextView messageBox = (TextView) conversationArea.findViewById(R.id.message);

        Message message = getItem(position);
        messageBox.setText(message.body);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) messageBox.getLayoutParams();

        if (message.isSent()) {
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